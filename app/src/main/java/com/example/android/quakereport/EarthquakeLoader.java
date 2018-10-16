package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    /**Tag for log messages*/
    private static final String LOG_TAG = "\n\n"+ EarthquakeLoader.class.getName();
    /**Query Url*/
    String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        // TODO: Finish implementing this constructor
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "onStart method ");
        forceLoad();
    }

    /**
     * This is on a background thread.
     * @return List of Earthquake
     */
    @Override
    public List<Earthquake> loadInBackground() {
        // TODO: Implement this method
        Log.d(LOG_TAG, "Do in background method");
        // Don't perform the request if there are no URL, or the URL is null.
        if (mUrl.isEmpty() || mUrl == null) {
            return null;
        }

        List<Earthquake> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }




}
