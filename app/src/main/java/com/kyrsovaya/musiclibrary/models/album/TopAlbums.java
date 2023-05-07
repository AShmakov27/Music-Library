package com.kyrsovaya.musiclibrary.models.album;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopAlbums {
    @SerializedName("album")
    private List<TopAlbum> topAlbum;

    public TopAlbums(List<TopAlbum> album) {
        this.topAlbum = album;
    }

    public List<TopAlbum> getTopAlbumsList() {
        return topAlbum;
    }

    public void setTopAlbum(List<TopAlbum> album) {
        this.topAlbum = album;
    }

    @Override
    public String toString() {
        return "TopAlbums{" +
                "album=" + topAlbum +
                '}';
    }
}
