package com.example.mobsoft.webkorhaz.interactor.appointment.events;

import com.example.mobsoft.webkorhaz.model.Appointment;

import java.util.List;

/**
 * Created by Apati on 2017.04.08..
 */

public class ShowAppointmentEvent {
    private int code;
    private Appointment appointment;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public ShowAppointmentEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

//</editor-fold>
}