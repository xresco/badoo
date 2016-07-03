package com.abed.badoo.injection.component;

import android.app.Application;
import android.content.Context;

import com.abed.badoo.data.DataManager;
import com.abed.badoo.injection.ApplicationContext;
import com.abed.badoo.injection.module.ApplicationModule;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();
}
