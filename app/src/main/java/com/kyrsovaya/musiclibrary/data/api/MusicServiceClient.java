package com.kyrsovaya.musiclibrary.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.kyrsovaya.musiclibrary.utils.Constants.BASE_URL;

public class MusicServiceClient {
    private static Retrofit retrofit = null;


    public static synchronized Retrofit getUserInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
