package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param earthquakes The ArrayList containing all Earthquake objects
     */
    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }
}
