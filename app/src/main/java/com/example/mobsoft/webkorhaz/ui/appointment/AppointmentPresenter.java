package com.example.mobsoft.webkorhaz.ui.appointment;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.appointment.AppointmentInteractor;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.DeleteAppointmentEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.SaveAppointmentsEvent;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;



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

    public void deleteAppointment(final Appointment appointment) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appointmentInteractor.deleteAppointment(appointment);
            }
        });
    }


    /**
     * {@link DeleteAppointmentEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(DeleteAppointmentEvent event) {
        Throwable throwable = event.getThrowable();
        if (throwable != null) {
            throwable.printStackTrace();
            if (screen != null) {
                screen.showMessage(throwable.getMessage());
            }
            Log.e("Networking", throwable.getMessage() , throwable);
        } else {
            if (screen != null) {
                screen.navigateToAppointmentList("Sikeres időpont törlés! ");
            }
        }
    }


    /**
     * {@link SaveAppointmentsEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(SaveAppointmentsEvent event) {
        Throwable throwable = event.getThrowable();
        if (throwable != null) {
            throwable.printStackTrace();
            if (screen != null) {
                screen.showMessage(throwable.getMessage());
            }
            Log.e("Networking", throwable.getMessage(), throwable);
        } else {
            if (screen != null) {
                screen.navigateToAppointmentList("Sikeres időpont foglalás!");
            }
        }
    }

}
