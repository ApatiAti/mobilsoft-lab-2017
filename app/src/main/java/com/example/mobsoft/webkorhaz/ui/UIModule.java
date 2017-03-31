package com.example.mobsoft.webkorhaz.ui;

import android.content.Context;


import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentPresenter;
import com.example.mobsoft.webkorhaz.ui.login.LoginPresenter;
import com.example.mobsoft.webkorhaz.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    @Singleton
    public AppointmentPresenter provideAppointmentPresenter() {
        return new AppointmentPresenter();
    }
}