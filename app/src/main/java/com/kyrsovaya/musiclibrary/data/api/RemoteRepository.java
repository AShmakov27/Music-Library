package com.kyrsovaya.musiclibrary.data.api;

import static com.kyrsovaya.musiclibrary.utils.Constants.ALBUM_NAME;
import static com.kyrsovaya.musiclibrary.utils.Constants.API_KEY;
import static com.kyrsovaya.musiclibrary.utils.Constants.ARTIST_NAME;
import static com.kyrsovaya.musiclibrary.utils.Constants.JSON_FORMAT;
import static com.kyrsovaya.musiclibrary.utils.Constants.LOG_TAG;
import static com.kyrsovaya.musiclibrary.utils.Constants.TRACK_NAME;

import android.util.Log;

import com.kyrsovaya.musiclibrary.models.album.AlbumApiResponse;
import com.kyrsovaya.musiclibrary.models.album.AlbumsApiResponse;
import com.kyrsovaya.musiclibrary.models.album.TopAlbumsApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.ArtistApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.ArtistsApiResponse;
import com.kyrsovaya.musiclibrary.models.track.TracksApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {

    MusicDataService service = MusicServiceClient.getUserInstance().create(MusicDataService.class);

    public void getArtistInfoResults() {
        Call<ArtistApiResponse> call = service.getArtistInfo(ARTIST_NAME, API_KEY, JSON_FORMAT);
        Callback<ArtistApiResponse> callback = new Callback<ArtistApiResponse>() {
            @Override
            public void onResponse(Call<ArtistApiResponse> call, Response<ArtistApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
            }
            @Override
            public void onFailure(Call<ArtistApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }

    public void getArtistSearchResults() {
        Call<ArtistsApiResponse> call = service.getArtistSearch(ARTIST_NAME, API_KEY, JSON_FORMAT);
        Callback<ArtistsApiResponse> callback = new Callback<ArtistsApiResponse>() {
            @Override
            public void onResponse(Call<ArtistsApiResponse> call, Response<ArtistsApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
            }
            @Override
            public void onFailure(Call<ArtistsApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }

    public void getAlbumInfoResults() {
        Call<AlbumApiResponse> call = service.getAlbumInfo(ARTIST_NAME, ALBUM_NAME, API_KEY, JSON_FORMAT);
        Callback<AlbumApiResponse> callback = new Callback<AlbumApiResponse>() {
            @Override
            public void onResponse(Call<AlbumApiResponse> call, Response<AlbumApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
            }
            @Override
            public void onFailure(Call<AlbumApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }

    public void getAlbumSearchResults() {
        Call<AlbumsApiResponse> call = service.getAlbumSearch(ALBUM_NAME, API_KEY, JSON_FORMAT);
        Callback<AlbumsApiResponse> callback = new Callback<AlbumsApiResponse>() {
            @Override
            public void onResponse(Call<AlbumsApiResponse> call, Response<AlbumsApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
            }
            @Override
            public void onFailure(Call<AlbumsApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }

    public void getTopAlbums() {
        Call<TopAlbumsApiResponse> call = service.getTopAlbums(ARTIST_NAME, API_KEY, JSON_FORMAT);
        Callback<TopAlbumsApiResponse> callback = new Callback<TopAlbumsApiResponse>() {
            @Override
            public void onResponse(Call<TopAlbumsApiResponse> call, Response<TopAlbumsApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
            }
            @Override
            public void onFailure(Call<TopAlbumsApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }

    public void getTrackSearch() {
        Call<TracksApiResponse> call = service.getTrackSearch(TRACK_NAME, API_KEY, JSON_FORMAT);
        Callback<TracksApiResponse> callback = new Callback<TracksApiResponse>() {
            @Override
            public void onResponse(Call<TracksApiResponse> call, Response<TracksApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
            }
            @Override
            public void onFailure(Call<TracksApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }
}
