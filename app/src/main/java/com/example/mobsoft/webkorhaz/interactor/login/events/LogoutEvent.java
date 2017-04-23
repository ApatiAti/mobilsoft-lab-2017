package com.example.mobsoft.webkorhaz.interactor.login.events;

import com.example.mobsoft.webkorhaz.model.User;

/**
 * Created by Apati on 2017.04.04..
 */

public class LogoutEvent {
    private int code;
    private User user;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public LogoutEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

//</editor-fold>
}