package com.mertkilic.popularmovies.di.module;

import android.app.Application;
import android.content.res.Resources;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.mertkilic.popularmovies.data.api.TrackTvInterceptor;
import com.mertkilic.popularmovies.data.api.TrackTvService;
import com.mertkilic.popularmovies.di.scopes.PerApplication;
import com.mertkilic.popularmovies.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    public Application provideApplication() {
        return application;
    }

    @Provides
    @PerApplication
    Resources provideResources() {
        return application.getResources();
    }

    @Provides
    @PerApplication
    Interceptor provideInterceptor() {
        return new TrackTvInterceptor();
    }

    @Provides
    @PerApplication
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @PerApplication
    TrackTvService provideTrackTvService(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(TrackTvService.END_POINT)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build().create(TrackTvService.class);
    }
}
