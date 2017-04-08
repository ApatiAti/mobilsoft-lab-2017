package com.example.mobsoft.webkorhaz.interactor.appointment.events;

import com.example.mobsoft.webkorhaz.model.Appointment;

import java.util.List;

/**
 * Created by Apati on 2017.04.08..
 */

public class SaveAppointmentsEvents {
    private int code;
    private boolean succes;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public SaveAppointmentsEvents() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

//</editor-fold>
}