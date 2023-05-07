package com.kyrsovaya.musiclibrary.models.ViewModel;

import static com.kyrsovaya.musiclibrary.utils.Constants.API_KEY;
import static com.kyrsovaya.musiclibrary.utils.Constants.COUNTRY;
import static com.kyrsovaya.musiclibrary.utils.Constants.JSON_FORMAT;
import static com.kyrsovaya.musiclibrary.utils.Constants.LOG_TAG;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kyrsovaya.musiclibrary.data.api.MusicDataService;
import com.kyrsovaya.musiclibrary.data.api.MusicServiceClient;
import com.kyrsovaya.musiclibrary.models.artist.Artists;
import com.kyrsovaya.musiclibrary.models.artist.ChartsApiResponse;
import com.kyrsovaya.musiclibrary.models.artist.GeoApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicActivityViewModel extends ViewModel {
    private MutableLiveData<Artists> topArtists;
    private MutableLiveData<Artists> geoTopArtists;

    public LiveData<Artists> getTopArtists() {
        if (topArtists == null) {
            topArtists = new MutableLiveData<Artists>();
        }
        return topArtists;
    }

    public LiveData<Artists> getGeoTopArtists() {
        if (geoTopArtists == null) {
            geoTopArtists = new MutableLiveData<Artists>();
        }
        return geoTopArtists;
    }

    MusicDataService service = MusicServiceClient.getUserInstance().create(MusicDataService.class);

    public void fetchTopArtists() {
        Call<ChartsApiResponse> call = service.getChartTopArtists(API_KEY, JSON_FORMAT);
        Callback<ChartsApiResponse> callback = new Callback<ChartsApiResponse>() {
            @Override
            public void onResponse(Call<ChartsApiResponse> call, Response<ChartsApiResponse> response) {
                topArtists.postValue(response.body().getTopArtists());
            }

            @Override
            public void onFailure(Call<ChartsApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }

    public void fetchGeoTopArtists() {
        Call<GeoApiResponse> call = service.getGeoTopArtists(COUNTRY, API_KEY, JSON_FORMAT);
        Callback<GeoApiResponse> callback = new Callback<GeoApiResponse>() {
            @Override
            public void onResponse(Call<GeoApiResponse> call, Response<GeoApiResponse> response) {
                geoTopArtists.postValue(response.body().getGeoTopArtists());
                Log.i(LOG_TAG, "GeoApiResponse: " + response.body());
            }
            @Override
            public void onFailure(Call<GeoApiResponse> call, Throwable t) {
                Log.i(LOG_TAG, "Failed to retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }
}
