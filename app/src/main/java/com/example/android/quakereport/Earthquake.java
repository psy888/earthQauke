package com.example.android.quakereport;

import java.text.DecimalFormat;

public class Earthquake {
    double mMag;
    String mLoc;
    long mDate;

    public Earthquake(double mag, String loc, long date) {
        mMag = mag;
        mLoc = loc;
        mDate = date;
    }

    public String getMag() {
        DecimalFormat df1 = new DecimalFormat(".#");
        return df1.format(mMag);
    }

    public String getLoc() {
        return mLoc;
    }

    public long getDate() {
        return mDate;
    }
}
