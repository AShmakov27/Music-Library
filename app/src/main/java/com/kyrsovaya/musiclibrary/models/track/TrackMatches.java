package com.kyrsovaya.musiclibrary.models.track;

import java.util.List;

public class TrackMatches {
    private List<TrackMatch> track;

    public TrackMatches(List<TrackMatch> track) {
        this.track = track;
    }

    public List<TrackMatch> getTrack() {
        return track;
    }

    public void setTrack(List<TrackMatch> track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "Tracks{" +
                "track=" + track +
                '}';
    }
}
