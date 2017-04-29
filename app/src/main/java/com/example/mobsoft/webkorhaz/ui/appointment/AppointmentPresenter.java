package com.example.mobsoft.webkorhaz.ui.appointment;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.appointment.AppointmentInteractor;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.ReloadAppoinmentFromServerEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.SaveAppointmentsEvents;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.ShowAppointmentEvent;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class AppointmentPresenter extends Presenter<AppointmentScreen> {

    @Inject
    AppointmentInteractor appointmentInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public AppointmentPresenter() {
    }

    @Override
    public void attachScreen(AppointmentScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }


    public void saveorUpdateAppointment(final Appointment appointment){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appointmentInteractor.saveOrUpdateAppointent(appointment);
            }
        });
    }

    public void refreshAppointmentFromServer(final Appointment appointment){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appointmentInteractor.reloadAppoinmentFromServer(appointment);
            }
        });
    }

    /**
     * {@link ShowAppointmentEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    /* TODO átnézni kell-e ez
    public void onEventMainThread(ShowAppointmentEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            String msg = "Error at Loading Appointment";
            if (screen != null) {
                screen.showMessage(msg);
            }
            Log.e("Networking", msg, event.getThrowable());
        } else {
            if (screen != null) {
                screen.showAppointment(event.getAppointment());
            }
        }
    }
    */

    /**
     * {@link SaveAppointmentsEvents} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(SaveAppointmentsEvents event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            String msg = "Error at Loading Appointment";
            if (screen != null) {
                screen.showMessage(msg);
            }
            Log.e("Networking", msg, event.getThrowable());
        } else {
            if (screen != null) {
                screen.appointmentSaved(event.getAppointment());
            }
        }
    }

    /**
     * {@link ReloadAppoinmentFromServerEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    /* TODO átnézni kell-e ez
    public void onEventMainThread(ReloadAppoinmentFromServerEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            String msg = "Error at Loading Appointment from server";
            if (screen != null) {
                screen.showMessage(msg);
            }
            Log.e("Networking", msg, event.getThrowable());
        } else {
            if (screen != null) {
                screen.showAppointment(event.getAppointment());
            }
        }
    }
    */
}
