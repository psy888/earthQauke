package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter {
    private Context mContext;
    private List<Earthquake> earthquakeList = new ArrayList<>();

    public EarthQuakeAdapter(@NonNull Context context, ArrayList<Earthquake> list) {
        super(context, 0, list);
        earthquakeList = list;
        mContext = context;

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

        //time format
        Date eventDate = new Date(currentEarthQuake.getDate()); // Create new instance of date object and pass timestamp to it
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nHH:mm"); // Create new SimpleDateFormat and pass Pattern to format Date
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC")); //??? What for it ?
        String formattedDate = sdf.format(eventDate);


        //Set Layout resources values
        mag.setText(currentEarthQuake.getMag());
        String location = currentEarthQuake.getLoc();

        int isDistanceSet = location.indexOf(" of ");
        if (isDistanceSet != -1) {
            String distance = location.substring(0, isDistanceSet + 3).toUpperCase();
            dist.setText(distance);
            String locName = location.substring(isDistanceSet + 4);
            loc.setText(locName);

        } else {
            dist.setVisibility(View.GONE);
            loc.setText(location);
        }



        date.setText(String.valueOf(formattedDate));

        return listItem;
    }
}
