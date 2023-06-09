package com.kyrsovaya.musiclibrary.models.artist;

public class ArtistApiResponse {
    private Artist artist;

    public ArtistApiResponse(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "ArtistApiResponse{" +
                "artist=" + artist +
                '}';
    }
}
