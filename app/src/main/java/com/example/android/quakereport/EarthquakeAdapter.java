package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private Context mContext;
    private ArrayList<Earthquake> mEarthquakesList;
    private static final String LOCATION_SEPARATOR = " of ";

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
        TextView offsetLocationTV = (TextView) listItem.findViewById(R.id.location_offset_text_view);
        TextView primaryLocationTV = (TextView) listItem.findViewById(R.id.location_primary_text_view);
        TextView dateTextView = (TextView) listItem.findViewById(R.id.date_text_view);
        TextView timeTextView = (TextView) listItem.findViewById(R.id.time_text_view);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEQ.getMagnitude());
        // Need to change the color number into a color resource ID
        int magnitudeResourceColor = ContextCompat.getColor(getContext(), magnitudeColor);
        magnitudeCircle.setColor(magnitudeResourceColor);

        Date dateObject = new Date(currentEQ.getTimeInMilliseconds());

        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        magnitudeTextView.setText(formatMagnitude(currentEQ.getMagnitude()));

        String splitLocationsText[] = splitLocationString(currentEQ.getLocation());
        offsetLocationTV.setText(splitLocationsText[0]);
        primaryLocationTV.setText(splitLocationsText[1]);

        return listItem;

    }

    private int getMagnitudeColor(double magnitude) {
        int magColorResourceId;
        int intMag = (int) Math.floor(magnitude);
        switch (intMag) {
            case 0:
            case 1:
                magColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magColorResourceId = R.color.magnitude9;
                break;
            default:
                magColorResourceId = R.color.magnitude10plus;
                break;
        }
        return magColorResourceId;
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

    private String[] splitLocationString(String originalLocation) {

        String locationOffset;
        String primaryLocation;
        String returnArray[] = new String[2];

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        returnArray[0] = locationOffset;
        returnArray[1] = primaryLocation;

        return returnArray;

    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        String magnitudeDouble = magnitudeFormatter.format(magnitude);
        return magnitudeDouble;
    }
}
