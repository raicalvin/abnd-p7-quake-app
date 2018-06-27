package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        Earthquake currentEQ = mEarthquakesList.get(position);

        TextView magnitudeTextView = (TextView) listItem.findViewById(R.id.magnitude_text_view);
        TextView locationTextView = (TextView) listItem.findViewById(R.id.location_text_view);
        TextView dateTextView = (TextView) listItem.findViewById(R.id.date_text_view);
        TextView timeTextView = (TextView) listItem.findViewById(R.id.time_text_view);

        Date dateObject = new Date(currentEQ.getTimeInMilliseconds());

        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        magnitudeTextView.setText(String.valueOf(currentEQ.getmMagnitude()));
        locationTextView.setText(currentEQ.getmLocation());

        return listItem;

    }

    /**
     * Return the formatted date string (i.e. November 29, 2006) from a Date object
     * @param dateObject
     * @return
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. 1:29 PM) from a Date object
     * @param dateObject
     * @return
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
