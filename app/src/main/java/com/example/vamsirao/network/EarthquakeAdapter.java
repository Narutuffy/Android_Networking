package com.example.vamsirao.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by Vamsi Rao on 2/10/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView= convertView;
        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, parent, false);
        }
        Earthquake currentEarthquake= getItem(position);

        TextView magnitudeView= (TextView)listItemView.findViewById(R.id.magnitude);

        magnitudeView.setText(currentEarthquake.getMagnitude());

        TextView locationView= (TextView)listItemView.findViewById(R.id.location);

        locationView.setText(currentEarthquake.getLocation());

        Date dateObject = new Date (currentEarthquake.getTimeMilliseconds());

        TextView dateView= (TextView)listItemView.findViewById(R.id.date);

        SimpleDateFormat dateFormatter= new SimpleDateFormat("MMM DD,yyyy");
        String formattedDate= dateFormatter.format(dateObject);
        dateView.setText(formattedDate);

        SimpleDateFormat timeFormatter= new SimpleDateFormat("h:mm a");
        String formattedTime= timeFormatter.format(dateObject);

        TextView timeView= (TextView)listItemView.findViewById(R.id.time);

        timeView.setText(formattedTime);


        return listItemView;

    }
}
