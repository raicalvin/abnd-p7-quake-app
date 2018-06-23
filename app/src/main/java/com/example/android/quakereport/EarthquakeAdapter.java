package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private Context mContext;
    private ArrayList<Earthquake> mEarthquakesList;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param earthquakes The ArrayList containing all Earthquake objects
     */
    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
        mContext = context;
        mEarthquakesList = earthquakes;
    }

    /**
     * {@inheritDoc}
     *
     * @param position is the position of the current Earthquake item
     * @param convertView View to be reused in ListView
     * @param parent maybe the ListView in the earthquake_activity?
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (convertView == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.eq_list_item, parent, false);
        }
    }
}
