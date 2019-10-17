package com.aaroza.classroom.newui.utils;

import com.aaroza.classroom.newui.App;
import com.aaroza.classroom.newui.services.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constants{
    public static final String BASE_URL_SOCKET = "http://192.168.10.17:3000/con";
    public static String BASE_URL = "http://192.168.10.17:3000/";
    private static ApiService apiService;
    private static SPreferences sPreferences;

    public static final ApiService getApiService() {
        if (apiService == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(5, TimeUnit.MINUTES);
            httpClient.readTimeout(5, TimeUnit.MINUTES);
            httpClient.addInterceptor(logging);
            apiService = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }

    public static final SPreferences getSPreferences() {
        if (sPreferences == null) {
            sPreferences = new SPreferences(App.context);
        }
        return sPreferences;
    }
}
