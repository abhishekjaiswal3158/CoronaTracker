package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class District extends AppCompatActivity {
    private static final String LOG_TAG = District.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL1 =
            "https://api.covid19india.org/state_district_wise.json";

    private DistrictAdapter mAdapter;
    public static String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);




        Intent intent = getIntent();
        state = intent.getStringExtra("STATE");

        ListView earthquakeListView = (ListView) findViewById(R.id.districtList);
        final ArrayList<DistrictCorona> arrayList=new ArrayList<>();
        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new DistrictAdapter(this, arrayList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);




        DistrictAsyncTask task = new DistrictAsyncTask();
        task.execute(USGS_REQUEST_URL1);


        ListView lstview=(ListView)findViewById(R.id.districtList);
        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(District.this, ShowActivity.class);
                int i=(int)id;
                DistrictCorona corona=  arrayList.get(i);
                String dist=corona.getDistrict();
                String active=corona.getActive();
                String death=corona.getDeath();
                String recover=corona.getRecover();
                String total=corona.getTotal();
                intent.putExtra("PLACE",dist);
                intent.putExtra("ACTIVE",active);
                intent.putExtra("TOTAL",total);
                intent.putExtra("RECOVER",recover);
                intent.putExtra("DEATH",death);
                startActivity(intent);
            }
        });

    }
    private class DistrictAsyncTask extends AsyncTask<String, Void, ArrayList<DistrictCorona>> {


        @Override
        protected ArrayList<DistrictCorona> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<DistrictCorona> result = DistrictUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * which will trigger the ListView to re-populate its list items.
         */
        @Override
        protected void onPostExecute(ArrayList<DistrictCorona> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }

}
