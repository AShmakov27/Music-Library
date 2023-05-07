package com.kyrsovaya.musiclibrary.models.artist;

import com.google.gson.annotations.SerializedName;

public class ChartsApiResponse {
    @SerializedName("artists")
    private Artists topArtists;

    public ChartsApiResponse(Artists topArtists) {
        this.topArtists = topArtists;
    }

    public Artists getTopArtists() {
        return topArtists;
    }

    public void setTopArtists(Artists topArtists) {
        this.topArtists = topArtists;
    }

    @Override
    public String toString() {
        return "ChartsApiResponse{" +
                "topArtists=" + topArtists +
                '}';
    }
}
