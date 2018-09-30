package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class EarthquakeAdapter extends ArrayAdapter {
    private Context mContext;
    private static final String LOCATION_SEPARATOR = " of ";
    private List<Earthquake> earthquakeList;

    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> list) {
        super(context, 0, list);
        earthquakeList = list;
        mContext = context;
    }


    private String dateFormat(Date dataObject) //helper method format Date
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return dateFormat.format(dataObject);
    }

    private String timeFormat(Date dataObject) //helper method format Time
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return timeFormat.format(dataObject);
    }

    private String formatMagnitude(Double magnitude) //magnitude formatting
    {
        DecimalFormat decimalFormat = new DecimalFormat(".#");
        return decimalFormat.format(magnitude);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }
        //get Current object
        Earthquake currentEarthQuake = earthquakeList.get(position);

        //get Layout resources
        TextView mag = (TextView) listItem.findViewById(R.id.mag);
        TextView dist = (TextView) listItem.findViewById(R.id.distance);
        TextView loc = (TextView) listItem.findViewById(R.id.location);
        TextView date = (TextView) listItem.findViewById(R.id.date);
        TextView time = (TextView) listItem.findViewById(R.id.time);

        //date format
        Date eventDate = new Date(currentEarthQuake.getDate()); // Create new instance of date object and pass timestamp to it



        //Set Layout resources values
        mag.setText(formatMagnitude(currentEarthQuake.getMag()));


        String location = currentEarthQuake.getLoc();
        //location formatting
        if (location.contains(LOCATION_SEPARATOR)) { //if distance from city is present
            String[] parts = location.split(LOCATION_SEPARATOR, 2); //split string
            dist.setText((parts[0] + LOCATION_SEPARATOR).trim().toUpperCase());
            loc.setText(parts[1]);

        } else { //if earthquake was in city
            dist.setVisibility(View.GONE); //Remove Distance TextView from layout
            loc.setText(location); //Set full string to location textView
        }


        //formatting and setting date and time
        date.setText(dateFormat(eventDate));
        time.setText(timeFormat(eventDate));

        return listItem;
    }
}
