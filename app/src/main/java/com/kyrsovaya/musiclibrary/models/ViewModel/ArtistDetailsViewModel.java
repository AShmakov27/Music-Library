package com.kyrsovaya.musiclibrary.models.ViewModel;

import static com.kyrsovaya.musiclibrary.utils.Constants.API_KEY;
import static com.kyrsovaya.musiclibrary.utils.Constants.JSON_FORMAT;
import static com.kyrsovaya.musiclibrary.utils.Constants.LOG_TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kyrsovaya.musiclibrary.data.api.MusicDataService;
import com.kyrsovaya.musiclibrary.data.api.MusicServiceClient;
import com.kyrsovaya.musiclibrary.models.album.TopAlbums;
import com.kyrsovaya.musiclibrary.models.album.TopAlbumsApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.Artist;
import com.kyrsovaya.musiclibrary.models.artist.ArtistApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistDetailsViewModel extends ViewModel {
    private MutableLiveData<Artist> artist;

    private MutableLiveData<TopAlbums> topAlbums;

    public LiveData<Artist> getArtist() {
        if (artist == null) {
            artist = new MutableLiveData<Artist>();
        }
        return artist;
    }

    public LiveData<TopAlbums> getTopAlbums() {
        if (topAlbums == null) {
            topAlbums = new MutableLiveData<TopAlbums>();
        }
        return topAlbums;
    }

    MusicDataService service = MusicServiceClient.getUserInstance().create(MusicDataService.class);

    public void fetchArtistInfo(String artistName) {

        Call<ArtistApiResponse> call = service.getArtistInfo(artistName, API_KEY, JSON_FORMAT);
        Callback<ArtistApiResponse> callback = new Callback<ArtistApiResponse>() {

            @Override
            public void onResponse(Call<ArtistApiResponse> call, Response<ArtistApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
                artist.postValue(response.body().getArtist());
            }

            @Override
            public void onFailure(Call<ArtistApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };

        call.enqueue(callback);
    }

    public void fetchTopAlbums(String artistName) {

        Call<TopAlbumsApiResponse> call = service.getTopAlbums(artistName, API_KEY, JSON_FORMAT);

        Callback<TopAlbumsApiResponse> callback = new Callback<TopAlbumsApiResponse>() {

            @Override
            public void onResponse(Call<TopAlbumsApiResponse> call, Response<TopAlbumsApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
                topAlbums.postValue(response.body().getTopAlbums());
            }

            @Override
            public void onFailure(Call<TopAlbumsApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };

        call.enqueue(callback);
    }
}
