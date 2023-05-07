package com.kyrsovaya.musiclibrary.data.api;

import com.kyrsovaya.musiclibrary.models.album.AlbumApiResponse;
import com.kyrsovaya.musiclibrary.models.album.AlbumsApiResponse;
import com.kyrsovaya.musiclibrary.models.album.TopAlbumsApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.ArtistApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.ArtistsApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.ChartsApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.GeoApiResponse;
import com.kyrsovaya.musiclibrary.models.track.TracksApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicDataService {

    @GET("?method=artist.getinfo")
    Call<ArtistApiResponse> getArtistInfo(@Query("artist") String artist_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=artist.search")
    Call<ArtistsApiResponse> getArtistSearch(@Query("artist") String artist_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=album.getInfo")
    Call<AlbumApiResponse> getAlbumInfo(@Query("artist") String artist_name, @Query("album") String album_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=album.search")
    Call<AlbumsApiResponse> getAlbumSearch(@Query("album") String album_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=artist.gettopalbums")
    Call<TopAlbumsApiResponse> getTopAlbums(@Query("artist") String artist_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=track.search")
    Call<TracksApiResponse> getTrackSearch(@Query("track") String track_name, @Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=chart.gettopartists")
    Call<ChartsApiResponse> getChartTopArtists(@Query("api_key") String api_key, @Query("format") String format);

    @GET("?method=geo.gettopartists")
    Call<GeoApiResponse> getGeoTopArtists(@Query("country") String country, @Query("api_key") String api_key, @Query("format") String format);
}
