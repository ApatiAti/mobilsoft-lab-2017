package com.example.mobsoft.webkorhaz.ui.main;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.appointment.AppointmentInteractor;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromDbEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromServerEvents;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.ConsultationHourInteractor;
import com.example.mobsoft.webkorhaz.interactor.todo.FavouritesInteractor;
import com.example.mobsoft.webkorhaz.interactor.todo.events.GetFavouritesEvent;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.model.User;
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
    ConsultationHourInteractor consultationHourInteractor;

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
        if (!bus.isRegistered(this)){
            bus.register(this);
        }
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }


    /**
     * Db-ben eltárolt Appoinmentek lekérdezése
     * @param currentUser
     */
    public void loadAppointmentFromDb(final User currentUser){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appointmentInteractor.loadAppointmentsFromDb(currentUser);
            }
        });
    }

    /**
     * Szerver oldalról lekérdezi a felhasználó Appointmentjeit
     */
    public void refreashAppointments(final User user){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appointmentInteractor.reloadAppoinmentListFromServer(user.getPatientId());
            }
        });
    }

    public void refreshDepartmentData(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                consultationHourInteractor.refreshDepartmentData();
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

    // Only for test
    public void onEventMainThread(GetFavouritesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showErrorAtRefreshFromServer("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                for(Todo t : event.getTodos()){
                    screen.showErrorAtRefreshFromServer(t.getName());
                }
            }
        }
    }

    /**
     * {@link LoadAppointmentListFromDbEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(LoadAppointmentListFromDbEvent event) {
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
     * {@link LoadAppointmentListFromServerEvents} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(LoadAppointmentListFromServerEvents event) {
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

    public void testNetwork() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favouritesInteractor.testNetwork();
            }
        });
    }
}
