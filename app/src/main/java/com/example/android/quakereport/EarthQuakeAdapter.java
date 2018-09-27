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
import java.util.TimeZone;

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
        TextView loc = (TextView) listItem.findViewById(R.id.location);
        TextView date = (TextView) listItem.findViewById(R.id.date);

        //time format
        Date eventDate = new Date(currentEarthQuake.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(eventDate);


        //Set Layout resources values
        mag.setText(currentEarthQuake.getMag());
        loc.setText(currentEarthQuake.getLoc());
        date.setText(String.valueOf(formattedDate));

        return listItem;
    }
}
