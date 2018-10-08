package com.example.android.quakereport;

public class Earthquake {
    private double mMag;
    private String mLoc;
    private long mDate;
    private String mUrl;

    public Earthquake(Double mag, String loc, long date, String url) {
        mMag = mag;
        mLoc = loc;
        mDate = date;
        mUrl = url;
    }

    public double getMag() {
        return mMag;
    }

    public String getLoc() {
        return mLoc;
    }

    public long getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
