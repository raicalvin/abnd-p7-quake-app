package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mEqURL;

    public Earthquake(double magnitude, String location, long date, String eqURL) {

        mMagnitude = magnitude; mLocation = location; mTimeInMilliseconds = date; mEqURL = eqURL;

    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() { return mEqURL; }
}
