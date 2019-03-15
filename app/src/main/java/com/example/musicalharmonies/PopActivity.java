package com.example.musicalharmonies;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;



    public class PopActivity extends AppCompatActivity {

        /** Handles playback of all the sound files */
        private MediaPlayer mMediaPlayer;

        /** Handles audio focus when playing a sound file */
        private AudioManager mAudioManager;

        /**
         * This listener gets triggered when the {@link MediaPlayer} has completed
         * playing the audio file.
         */
        private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Now that the sound file has finished playing, release the media player resources.
                releaseMediaPlayer();
            }
        };

        /**
         * This listener gets triggered whenever the audio focus changes
         * (i.e., we gain or lose audio focus because of another app or device).
         */
        private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                    // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume. We'll treat
                    // both cases the same way because our app is playing short sound files.

                    // Pause playback and reset player to the start of the file. That way, we can
                    // play the song from the beginning when we resume playback.
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    mMediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                    // Stop playback and clean up resources
                    releaseMediaPlayer();
                }
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.song_list);

            // Create and setup the {@link AudioManager} to request audio focus
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            // Create a list of songs
            final ArrayList<Song> songs = new ArrayList<Song>();
            songs.add(new Song(R.string.artist_normani_ft_6lack, R.string.pop_waves,
                    R.drawable.family_father, R.raw.pop_waves));
            songs.add(new Song(R.string.artist_whitney_ft_mariah, R.string.pop_when_you_believe,
                    R.drawable.family_mother, R.raw.pop_when_you_believe));
            songs.add(new Song(R.string.artist_sinead_ft_grades, R.string.pop_if_you_let_me,
                    R.drawable.family_son, R.raw.pop_if_you_let_me));
            songs.add(new Song(R.string.artist_ariana_grande, R.string.pop_7_rings,
                    R.drawable.family_daughter, R.raw.pop_7_rings));
            songs.add(new Song(R.string.artist_kendrick_ft_sza, R.string.pop_all_the_stars,
                    R.drawable.family_older_brother, R.raw.pop_all_the_stars));
            songs.add(new Song(R.string.artist_fifth_harmony, R.string.pop_no_way,
                    R.drawable.family_younger_brother, R.raw.pop_no_way));
            songs.add(new Song(R.string.artist_michael_jackson, R.string.pop_remember_the_time,
                    R.drawable.family_older_sister, R.raw.pop_remember_the_time));
            songs.add(new Song(R.string.artist_bruno_ft_cardi, R.string.pop_finesse_remix,
                    R.drawable.family_younger_sister, R.raw.pop_finesse_remix));
            songs.add(new Song(R.string.artist_kendrick_ft_rihanna, R.string.pop_loyalty,
                    R.drawable.family_grandmother, R.raw.pop_loyalty));
            songs.add(new Song(R.string.artist_selena, R.string.pop_dreaming_of_you,
                    R.drawable.family_grandfather, R.raw.pop_dreaming_of_you));

            // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
            // adapter knows how to create list items for each item in the list.
            SongAdapter adapter = new SongAdapter(this, songs, R.color.category_pop);

            // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
            // There should be a {@link ListView} with the view ID called list, which is declared in the
            // song_list.xml layout file.
            ListView listView = (ListView) findViewById(R.id.list);

            // Make the {@link ListView} use the {@link SongAdapter} we created above, so that the
            // {@link ListView} will display list items for each {@link Song} in the list.
            listView.setAdapter(adapter);

            // Set a click listener to play the audio when the list item is clicked on
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // Release the media player if it currently exists because we are about to
                    // play a different sound file
                    releaseMediaPlayer();

                    // Get the {@link Song} object at the given position the user clicked on
                    Song song = songs.get(position);

                    // Request audio focus so in order to play the audio file. The app needs to play a
                    // short audio file, so we will request audio focus with a short amount of time
                    // with AUDIOFOCUS_GAIN_TRANSIENT.
                    int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        // We have audio focus now.

                        // Create and setup the {@link MediaPlayer} for the audio resource associated
                        // with the current song
                        mMediaPlayer = MediaPlayer.create(PopActivity.this, song.getAudioResourceId());

                        // Start the audio file
                        mMediaPlayer.start();

                        // Setup a listener on the media player, so that we can stop and release the
                        // media player once the sound has finished playing.
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }
            });
        }

        @Override
        protected void onStop() {
            super.onStop();
            // When the activity is stopped, release the media player resources because we won't
            // be playing any more sounds.
            releaseMediaPlayer();
        }

        /**
         * Clean up the media player by releasing its resources.
         */
        private void releaseMediaPlayer() {
            // If the media player is not null, then it may be currently playing a sound.
            if (mMediaPlayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mMediaPlayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mMediaPlayer = null;

                // Regardless of whether or not we were granted audio focus, abandon it. This also
                // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }
    }

