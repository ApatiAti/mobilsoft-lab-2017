package com.example.mobsoft.webkorhaz.interactor.login;

import com.example.mobsoft.webkorhaz.interactor.appointment.events.GetAppoinmentsEvent;
import com.example.mobsoft.webkorhaz.interactor.login.events.LoginEvent;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.repository.Repository;


import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Apati on 2017.04.04..
 */

public class LoginInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public LoginInteractor() {
    }

    /**
     * Bejelentkezési próba
     */
    public void login(User user){
        LoginEvent event = new LoginEvent();
        try {
            User activeUser = null; // network.startLogin(user);
            event.setUser(activeUser);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
