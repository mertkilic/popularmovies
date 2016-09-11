package com.mertkilic.popularmovies.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.mertkilic.popularmovies.viewmodel.ViewModel;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public abstract class ViewModelActivity extends AppCompatActivity {

    protected ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        viewModel.onCreate(new Handler(Looper.getMainLooper()));
    }

    abstract protected void initViewModel();

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
