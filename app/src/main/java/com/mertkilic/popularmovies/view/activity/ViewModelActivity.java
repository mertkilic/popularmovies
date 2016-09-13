package com.mertkilic.popularmovies.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.mertkilic.popularmovies.PopularMoviesApp;
import com.mertkilic.popularmovies.di.component.ActivityComponent;
import com.mertkilic.popularmovies.di.component.DaggerActivityComponent;
import com.mertkilic.popularmovies.di.module.ActivityModule;
import com.mertkilic.popularmovies.viewmodel.ViewModel;

import javax.inject.Inject;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public abstract class ViewModelActivity<V extends ViewModel> extends AppCompatActivity {

    @Inject protected V viewModel;
    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        viewModel.onCreate(new Handler(Looper.getMainLooper()));
    }

    abstract protected void initViewModel();

    protected ActivityComponent activityComponent() {
        if(activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .appComponent(PopularMoviesApp.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return activityComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
