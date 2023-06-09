package com.kyrsovaya.musiclibrary.models.artist;

public class ArtistSearchResults {
    private Artists artistmatches;

    public ArtistSearchResults(Artists artistmatches) {
        this.artistmatches = artistmatches;
    }

    public Artists getArtistmatches() {
        return artistmatches;
    }

    public void setArtistmatches(Artists artistmatches) {
        this.artistmatches = artistmatches;
    }

    @Override
    public String toString() {
        return "ArtistSearchResults{" +
                "artist matches=" + artistmatches +
                '}';
    }
}
