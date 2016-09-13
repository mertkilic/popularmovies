package com.mertkilic.popularmovies.di.component;

import com.mertkilic.popularmovies.di.module.ActivityModule;
import com.mertkilic.popularmovies.di.scopes.PerActivity;
import com.mertkilic.popularmovies.view.activity.MainActivity;
import com.mertkilic.popularmovies.view.activity.SearchActivity;

import dagger.Component;

/**
 * Created by Mert Kilic on 13.9.2016.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(SearchActivity activity);
}