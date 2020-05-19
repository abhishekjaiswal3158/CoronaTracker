package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Global_Activity extends AppCompatActivity {

    private static final String LOG_TAG =Global_Activity.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL1 =
            "https://coronavirus-19-api.herokuapp.com/countries";

    private GlobalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        ListView earthquakeListView = (ListView) findViewById(R.id.countrylistView);
final ArrayList<Global> globals=new ArrayList<>();
        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new GlobalAdapter(this,globals);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);



        GlobalAsyncTask task = new GlobalAsyncTask();
        task.execute(USGS_REQUEST_URL1);


        ListView lstview=(ListView)findViewById(R.id.countrylistView);
        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(Global_Activity.this, global_show_Activity.class);
                int i=(int)id;
                Global corona=  globals.get(i);
                String country=corona.getCountry();
                String active=corona.getActive();
                String death=corona.getDeath();
                String recover=corona.getRecover();
                String total=corona.getTotal();
                intent.putExtra("PLACE",country);
                intent.putExtra("ACTIVE",active);
                intent.putExtra("TOTAL",total);
                intent.putExtra("RECOVER",recover);
                intent.putExtra("DEATH",death);
                startActivity(intent);
            }
        });
    }

    private class GlobalAsyncTask extends AsyncTask<String, Void, ArrayList<Global>> {


        @Override
        protected ArrayList<Global> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Global> result = GlobalUtils.fetchEarthquakeData(urls[0]);
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
        protected void onPostExecute(ArrayList<Global> data) {
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
