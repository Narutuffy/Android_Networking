package com.example.vamsirao.network;



import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL=  "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);




        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list_view);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter= new EarthquakeAdapter(this,new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        //--------
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake= mAdapter.getItem(position);
                Uri earthquakeUri=Uri.parse(currentEarthquake.getUrl());
                Intent websiteIntent= new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity(websiteIntent);
            }
        });

        ////
        EarthqaukeAsyncTask task= new EarthqaukeAsyncTask();
        task.execute(USGS_REQUEST_URL);

    }

    //-----Inner Async class

    private class EarthqaukeAsyncTask extends AsyncTask<String,Void,List<Earthquake>>{
        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            if(urls.length<1||urls[0]==null){
                return null;
            }

            List<Earthquake> result= QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Earthquake> data) {
           mAdapter.clear();

            if(data!=null && !data.isEmpty()){
                mAdapter.addAll(data);
            }
        }
    }
}