package com.example.android.architecture.blueprints.todoapp.application;

import android.app.Application;

/**
 * Created by Zhuinden on 2017.07.26..
 */

public class CustomApplication
        extends Application {
    private static CustomApplication INSTANCE;

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        applicationComponent = DaggerApplicationComponent.builder().androidModule(new AndroidModule(this)).build();
    }

    public static CustomApplication get() {
        return INSTANCE;
    }
}