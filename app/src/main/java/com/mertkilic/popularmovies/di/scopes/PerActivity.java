package com.mertkilic.popularmovies.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Mert Kilic on 13.9.2016.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}
