package com.example.mobsoft.webkorhaz.ui;

import android.content.Context;


import com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch.ConsultationHourSearchPresenter;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentPresenter;
import com.example.mobsoft.webkorhaz.ui.consultationHourList.ConsultationHourListPresenter;
import com.example.mobsoft.webkorhaz.ui.login.LoginPresenter;
import com.example.mobsoft.webkorhaz.ui.main.MainPresenter;
import com.example.mobsoft.webkorhaz.ui.navigation.NavigationPresenter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

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

    @Provides
    @Singleton
    public ConsultationHourSearchPresenter provideConsultationHourSearchPresenter() {
        return new ConsultationHourSearchPresenter();
    }

    @Provides
    @Singleton
    public ConsultationHourListPresenter provideConsultationHourListPresenter() {
        return new ConsultationHourListPresenter();
    }

    @Provides
    @Singleton
    public NavigationPresenter provideNavigationPresenter() {
        return new NavigationPresenter();
    }



    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}