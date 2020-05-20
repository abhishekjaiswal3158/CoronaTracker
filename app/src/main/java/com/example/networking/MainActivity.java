package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String json;

    private static final String LOG_TAG = MainActivity.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://covid-19india-api.herokuapp.com/v2.0/state_data";
    private static final String url2 =
            "https://coronavirus-19-api.herokuapp.com/countries";


   private CoronaAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView earthquakeListView = (ListView) findViewById(R.id.listView);
        final ArrayList<Corona> arrayList=new ArrayList<Corona>();
        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new CoronaAdapter(this, arrayList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        CoronaAsyncTask task = new CoronaAsyncTask();
        task.execute(USGS_REQUEST_URL);

        IndiaAsyncTask task2 = new IndiaAsyncTask();
        task2.execute(url2);





        ListView lstview=(ListView)findViewById(R.id.listView);
        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, District.class);
                int i=(int)id;
                Corona corona=  arrayList.get(i);
                String state=corona.getState();
                String total=corona.getTotal();
                String active=corona.getActive();
                String recover=corona.getRecover();
                String death=corona.getDeath();
                intent.putExtra("STATE",state);
                intent.putExtra("TOTAL",total);
                intent.putExtra("ACTIVE",active);
                intent.putExtra("DEATH",death);
                intent.putExtra("RECOVER",recover);
                startActivity(intent);
            }
        });








    }





    private class CoronaAsyncTask extends android.os.AsyncTask<String, Void, ArrayList<Corona>> {


        @Override
        protected ArrayList<Corona> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Corona> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(ArrayList<Corona> data) {
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }

    private class IndiaAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String  doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            String result = IndiaUtils.fetchEarthquakeData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(String data) {
            try {
                JSONArray array=new JSONArray(data);
                JSONObject o=array.getJSONObject(11);
                String total=o.getString("cases");
                String active=o.getString("active");
                String recover=o.getString("recovered");
                String death=o.getString("deaths");

                TextView t=(TextView)findViewById(R.id.itotal);
                TextView r=(TextView)findViewById(R.id.irecover);
                TextView a=(TextView)findViewById(R.id.iactive);
                TextView d=(TextView)findViewById(R.id.ideath);

                t.setText(total);
                r.setText(recover);
                a.setText(active);
                d.setText(death);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.globaloption:
                Intent intent = new Intent(MainActivity.this, Global_Activity.class);
                startActivity(intent);
                break;



            case R.id.about:
                Intent intent2 = new Intent(MainActivity.this, about_activity.class);
                startActivity(intent2);

                break;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
