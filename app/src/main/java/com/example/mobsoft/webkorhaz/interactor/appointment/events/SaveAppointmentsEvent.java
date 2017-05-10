package com.example.mobsoft.webkorhaz.interactor.appointment.events;

import com.example.mobsoft.webkorhaz.model.Appointment;

/**
 * Created by Apati on 2017.04.08..
 */

public class SaveAppointmentsEvent {
    private AppointmentsEventCode code;
    private Appointment appointment;
    private Throwable throwable;

    public SaveAppointmentsEvent(AppointmentsEventCode code) {
        this.code = code;
    }

    public AppointmentsEventCode getCode() {
        return code;
    }

    public void setCode(AppointmentsEventCode code) {
        this.code = code;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}