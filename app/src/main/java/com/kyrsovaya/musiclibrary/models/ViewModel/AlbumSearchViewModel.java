package com.kyrsovaya.musiclibrary.models.ViewModel;

import static com.kyrsovaya.musiclibrary.utils.Constants.API_KEY;
import static com.kyrsovaya.musiclibrary.utils.Constants.JSON_FORMAT;
import static com.kyrsovaya.musiclibrary.utils.Constants.LOG_TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kyrsovaya.musiclibrary.data.api.MusicDataService;
import com.kyrsovaya.musiclibrary.data.api.MusicServiceClient;
import com.kyrsovaya.musiclibrary.models.album.AlbumSearchResults;
import com.kyrsovaya.musiclibrary.models.album.AlbumsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumSearchViewModel extends ViewModel {
    private MutableLiveData<AlbumSearchResults> albumResults;

    public MutableLiveData<AlbumSearchResults> getAlbumSearchResults() {
        if (albumResults == null) {
            albumResults = new MutableLiveData<AlbumSearchResults>();
        }
        return albumResults;
    }

    MusicDataService service = MusicServiceClient.getUserInstance().create(MusicDataService.class);

    public void fetchAlbumSearchResults(String albumName) {

        Call<AlbumsApiResponse> call = service.getAlbumSearch(albumName, API_KEY, JSON_FORMAT);

        Callback<AlbumsApiResponse> callback = new Callback<AlbumsApiResponse>() {

            @Override
            public void onResponse(Call<AlbumsApiResponse> call, Response<AlbumsApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
                albumResults.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<AlbumsApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };

        call.enqueue(callback);
    }
}
