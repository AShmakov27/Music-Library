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
import com.kyrsovaya.musiclibrary.models.track.TrackSearchResults;
import com.kyrsovaya.musiclibrary.models.track.TracksApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackSearchViewModel extends ViewModel {
    private MutableLiveData<TrackSearchResults> trackResults;

    public LiveData<TrackSearchResults> getTrackSearchResults() {
        if (trackResults == null) {
            trackResults = new MutableLiveData<TrackSearchResults>();
        }
        return trackResults;
    }

    MusicDataService service = MusicServiceClient.getUserInstance().create(MusicDataService.class);

    public void fetchTrackSearchResults(String trackName) {

        Call<TracksApiResponse> call = service.getTrackSearch(trackName, API_KEY, JSON_FORMAT);

        Callback<TracksApiResponse> callback = new Callback<TracksApiResponse>() {

            @Override
            public void onResponse(Call<TracksApiResponse> call, Response<TracksApiResponse> response) {
                Log.i(LOG_TAG, "onResponse: " + response.body());
                trackResults.postValue(response.body().getResults());
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
