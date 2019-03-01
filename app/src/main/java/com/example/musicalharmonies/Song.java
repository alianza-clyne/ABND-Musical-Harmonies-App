package com.example.musicalharmonies;

/**
 * {@link Song} represents a song that the user wants to play.
 * It contains resource IDs for the song artist's name (default translation in Miwok app),
 * song name (Miwok translation in Miwok app), audio file,
 * and optional image file for that song.
 */

public class Song {

    /** String resource ID for the artist's name (default translation in Miwok app) of the song */
    private int mArtistNameId;

    /** String resource ID for the name (Miwok translation in Miwok app) of the song */
    private int mSongNameId;

    /** Audio resource ID for the song */
    private int mAudioResourceId;

    /** Image resource ID for the song */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this song */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Song object - song with no image.
     *
     * @param artistNameId is the string resource ID for the artist's name (default
     *                             translation in Miwok app) of the song
     * @param songNameId is the string resource Id for the name (Miwok translation in
     *                           Miwok app) of the song
     * @param audioResourceId is the resource ID for the audio file associated with this song
     */
    public Song(int artistNameId, int songNameId, int audioResourceId) {
        mArtistNameId = artistNameId;
        mSongNameId = songNameId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Song object - song with an image.
     *
     * @param artistNameId is the string resource ID for the artist's name (default
     *                                translation in Miwok app) of the song
     * @param songNameId is the string resource Id for the name (Miwok translation in
     *                                 Miwok app) of the song
     * @param imageResourceId is the drawable resource ID for the image associated with the song
     * @param audioResourceId is the resource ID for the audio file associated with this song
     */
    public Song(int artistNameId, int songNameId, int imageResourceId,
                int audioResourceId) {
        mArtistNameId = artistNameId;
        mSongNameId = songNameId;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the string resource ID for the artist's name (default
     *                                      translation in Miwok app) of the song
     */
    public int getArtistNameId() {
        return mArtistNameId;
    }

    /**
     * Get the string resource ID for the name (Miwok translation in
     *                                       Miwok app) of the song
     */
    public int getSongNameId() {
        return mSongNameId;
    }

    /**
     * Return the image resource ID of the song.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this song.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the song.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

}
