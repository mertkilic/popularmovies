package com.mertkilic.popularmovies.di.module;

import com.mertkilic.popularmovies.data.api.TrackTvService;
import com.mertkilic.popularmovies.di.scopes.PerActivity;
import com.mertkilic.popularmovies.view.activity.ViewModelActivity;
import com.mertkilic.popularmovies.viewmodel.MainViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mert Kilic on 13.9.2016.
 */
@Module
public class ActivityModule {

    private final ViewModelActivity activity;

    public ActivityModule(ViewModelActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    MainViewModel provideMainViewModel(TrackTvService trackTvService) {
        return new MainViewModel(trackTvService);
    }
/*
    @Provides
    @PerActivity
    SearchViewModel provideSearchViewModel() {
        return new SearchViewModel();
    }*/
}
