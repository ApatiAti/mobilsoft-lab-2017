package com.example.mobsoft.webkorhaz;

import android.app.Application;

import com.example.mobsoft.webkorhaz.repository.Repository;
import com.example.mobsoft.webkorhaz.ui.UIModule;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class MobSoftApplication extends Application {

    @Inject
    Repository repository;

    public static MobSoftApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerMobSoftApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();

        injector.inject(this);
        repository.open(getApplicationContext());
    }
}