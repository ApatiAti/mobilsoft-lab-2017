package com.example.mobsoft.webkorhaz.interactor.appointment.events;


/**
 * Created by Apati on 2017.04.30..
 */

public class DeleteAppointmentEvent {
    private AppointmentsEventCode code = AppointmentsEventCode.DELETE;
    private boolean succes;
    private Throwable throwable;


    public DeleteAppointmentEvent() {
    }

    public AppointmentsEventCode getCode() {
        return code;
    }

    public void setCode(AppointmentsEventCode code) {
        this.code = code;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
