package com.example.mobsoft.webkorhaz.ui.main;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.appointment.AppointmentInteractor;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentsFromDbEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentsFromServerEvents;
import com.example.mobsoft.webkorhaz.interactor.todo.FavouritesInteractor;
import com.example.mobsoft.webkorhaz.ui.Presenter;


import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 31..
 */


public class MainPresenter extends Presenter<MainScreen> {

    /**
     * LAbor miatt marad
     */
    @Inject
    FavouritesInteractor favouritesInteractor;

    @Inject
    AppointmentInteractor appointmentInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public MainPresenter() {
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }


    /**
     * Db-ben eltárolt Appoinmentek lekérdezése
     */
    public void loadAppointmentFromDb(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appointmentInteractor.loadAppointmentsFromDb();
            }
        });
    }

    /**
     * Szerver oldalról lekérdezi a felhasználó Appointmentjeit
     */
    public void refreashAppointments(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appointmentInteractor.reloadAppoinmentsFromServer();
            }
        });
    }



    /**
     *  labor miatt
     */
    public void getFavourites() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favouritesInteractor.getFavourites();
            }
        });
    }


    /**
     * {@link LoadAppointmentsFromDbEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(LoadAppointmentsFromDbEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showErrorAtLoadFromDb("DB error");
            }
            Log.e("DB", "Error reading appointments from DB", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showAppointments(event.getAppointments());
            }
        }
    }

    /**
     * {@link LoadAppointmentsFromServerEvents} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(LoadAppointmentsFromServerEvents event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showErrorAtRefreshFromServer("Network error");
            }
            Log.e("DB", "Error reading appointments from DB", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showAppointments(event.getAppointments());
            }
        }
    }
}
