package com.mertkilic.popularmovies.data.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public class TrackTvInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder()
                .addHeader("Content-type", "application/json")
                .addHeader("trakt-api-key", "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086")
                .addHeader("trakt-api-version", "2");
        return chain.proceed(requestBuilder.build());
    }
}
