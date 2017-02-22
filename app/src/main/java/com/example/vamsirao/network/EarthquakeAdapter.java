package com.example.vamsirao.network;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by Vamsi Rao on 2/10/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, parent, false);
        }
        //Getting current Earthquake object
        Earthquake currentEarthquake = getItem(position);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());


        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

          magnitudeView.setText(formattedMagnitude);

        //Getting Magnitude View Background
        GradientDrawable magnitudeCircle=(GradientDrawable)magnitudeView.getBackground();

        //Using getMagnitudeColor to get the color of the magnitudeView based on the intensity of earthquake
        int magnitudeColor=getMagnitudeColor(currentEarthquake.getMagnitude());

        //Set the background
        magnitudeCircle.setColor(magnitudeColor);
//-----------------------------------------------------------------------------------
        // TextView locationView= (TextView)listItemView.findViewById(R.id.location);

       //Extracting the primary location and offset location from original location

        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String offsetLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView offsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetView.setText(offsetLocation);

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);


        //locationView.setText(currentEarthquake.getLocation());
//--------------------------------------------------------------------------------

        //Setting Date View and Time View with proper formatting
        Date dateObject = new Date(currentEarthquake.getTimeMilliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD,yyyy");
        String formattedDate = dateFormatter.format(dateObject);
        dateView.setText(formattedDate);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormatter.format(dateObject);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        timeView.setText(formattedTime);


        return listItemView;

    }
    private String formatMagnitude(double magnitude){

        DecimalFormat magnitudeFormat= new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);

    }
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor= (int)Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:

            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;

            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
                break;
                    }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
