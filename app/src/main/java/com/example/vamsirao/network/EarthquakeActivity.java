package com.example.vamsirao.network;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        /*earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2017"));
        earthquakes.add(new Earthquake("6.1","London","Jan 14, 2017"));
        earthquakes.add(new Earthquake("8.0","Lahore","Dec 15 , 2016"));
        earthquakes.add(new Earthquake("7.7","Mexico City","Dec 2, 2016"));
        earthquakes.add(new Earthquake("12.2","Trump Plaza","Nov 4, 2016"));
        earthquakes.add(new Earthquake("5.2","Paris","Dec 10, 2016"));
*/



        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list_view);

        // Create a new {@link ArrayAdapter} of earthquakes
        final EarthquakeAdapter adapter= new EarthquakeAdapter(this,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        //--------
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake= adapter.getItem(position);
                Uri earthquakeUri=Uri.parse(currentEarthquake.getUrl());
                Intent websiteIntent= new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity(websiteIntent);
            }
        });

    }
}