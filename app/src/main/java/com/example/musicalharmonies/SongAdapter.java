package com.example.musicalharmonies;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link SongAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Song} objects.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    /** Resource ID for the background color for this list of songs */
    private int mColorResourceId;

    /**
     * Create a new {@link SongAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param songs is the list of {@link Song}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of songs
     */
    public SongAdapter(Context context, ArrayList<Song> songs, int colorResourceId) {
        super(context, 0, songs);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID song_name_text_view.
        TextView songNameTextView = (TextView) listItemView.findViewById(R.id.song_name_text_view);
        // Get the name (Miwok translation in Miwok app) of the song from the currentSong object
        // and set this text on the songName TextView.
        songNameTextView.setText(currentSong.getSongNameId());

        // Find the TextView in the list_item.xml layout with the ID artist_name_text_view.
        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.artist_name_text_view);
        // Get the artist's name (default translation in Miwok app) of the song from the currentSong
        // object and set this text on the artistName TextView.
        artistNameTextView.setText(currentSong.getArtistNameId());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Check if an image is provided for this song or not
        if (currentSong.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentSong.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }


}
