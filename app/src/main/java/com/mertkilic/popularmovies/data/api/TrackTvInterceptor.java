package com.mertkilic.popularmovies.data.api;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public class TrackTvInterceptor implements Interceptor {

    private String[] headers = {"Content-type", "application/json",
            "trakt-api-key", "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086",
            "trakt-api-version", "2"};

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder()
                .headers(Headers.of(headers));
        return chain.proceed(requestBuilder.build());
    }
}
