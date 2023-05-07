package com.kyrsovaya.musiclibrary.data.api;

import com.kyrsovaya.musiclibrary.models.album.AlbumApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.ArtistApiResponse;
import com.kyrsovaya.musiclibrary.models.track.TracksApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicDataService {

    @GET("?method=artist.getinfo")
    Call<ArtistApiResponse> getArtistInfo(@Query("artist") String artist_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=album.getInfo")
    Call<AlbumApiResponse> getAlbumInfo(@Query("artist") String artist_name, @Query("album") String album_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=track.search")
    Call<TracksApiResponse> getTrackSearch(@Query("track") String track_name, @Query("api_key") String api_key, @Query("format") String format);
}
