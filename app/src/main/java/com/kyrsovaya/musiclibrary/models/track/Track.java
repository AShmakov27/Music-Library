package com.kyrsovaya.musiclibrary.models.track;

import com.kyrsovaya.musiclibrary.models.artist.Artist;
import com.kyrsovaya.musiclibrary.models.artist.Image;

import java.util.List;

public class Track {
    private String name;
    private Artist artist;
    private List<Image> image;

    public Track(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", image=" + image +
                '}';
    }
}
