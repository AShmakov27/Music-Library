package com.kyrsovaya.musiclibrary.models.album;

import com.kyrsovaya.musiclibrary.models.artist.Image;
import com.kyrsovaya.musiclibrary.models.track.Tracks;

import java.util.List;

public class Album {
    private String artist;
    private String name;
    private List<Image> image;
    private Wiki wiki;
    private Tracks tracks;

    public Album(String name, List<Image> image) {
        this.name = name;
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", wiki=" + wiki +
                ", tracks=" + tracks +
                '}';
    }
}
