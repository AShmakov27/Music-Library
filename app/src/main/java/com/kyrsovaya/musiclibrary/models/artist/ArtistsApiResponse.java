package com.kyrsovaya.musiclibrary.models.artist;

public class ArtistsApiResponse {
    private ArtistSearchResults results;

    public ArtistsApiResponse(ArtistSearchResults results) {
        this.results = results;
    }

    public ArtistSearchResults getResults() {
        return results;
    }

    public void setResults(ArtistSearchResults results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ArtistsApiResponse{" +
                "results=" + results +
                '}';
    }
}
