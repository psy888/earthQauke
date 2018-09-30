package com.example.android.quakereport;

public class Earthquake {
    private double mMag;
    private String mLoc;
    private long mDate;

    public Earthquake(double mag, String loc, long date) {
        mMag = mag;
        mLoc = loc;
        mDate = date;
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
}
