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
import com.kyrsovaya.musiclibrary.models.album.Album;
import com.kyrsovaya.musiclibrary.models.album.AlbumApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumDetailsViewModel extends ViewModel {
    private MutableLiveData<Album> album;

    public LiveData<Album> getAlbum() {
        if (album == null) {
            album = new MutableLiveData<Album>();
        }
        return album;
    }

    MusicDataService service = MusicServiceClient.getUserInstance().create(MusicDataService.class);

    public void fetchAlbumInfo(String artistName, String albumName) {

        Call<AlbumApiResponse> call = service.getAlbumInfo(artistName, albumName, API_KEY, JSON_FORMAT);
        Callback<AlbumApiResponse> callback = new Callback<AlbumApiResponse>() {

            @Override
            public void onResponse(Call<AlbumApiResponse> call, Response<AlbumApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
                album.postValue(response.body().getAlbum());
            }

            @Override
            public void onFailure(Call<AlbumApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };

        call.enqueue(callback);
    }
}
