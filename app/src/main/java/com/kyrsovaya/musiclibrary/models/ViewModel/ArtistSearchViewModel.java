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
import com.kyrsovaya.musiclibrary.models.artist.ArtistSearchResults;
import com.kyrsovaya.musiclibrary.models.artist.ArtistsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistSearchViewModel extends ViewModel {

    private MutableLiveData<ArtistSearchResults> artistResults;

    public LiveData<ArtistSearchResults> getArtistSearchResults() {
        if (artistResults == null) {
            artistResults = new MutableLiveData<ArtistSearchResults>();
        }
        return artistResults;
    }

    MusicDataService service = MusicServiceClient.getUserInstance().create(MusicDataService.class);

    public void fetchArtistSearchResults(String artistName) {
        Call<ArtistsApiResponse> call = service.getArtistSearch(artistName, API_KEY, JSON_FORMAT);
        Callback<ArtistsApiResponse> callback = new Callback<ArtistsApiResponse>() {
            @Override
            public void onResponse(Call<ArtistsApiResponse> call, Response<ArtistsApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
                artistResults.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ArtistsApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }
}
