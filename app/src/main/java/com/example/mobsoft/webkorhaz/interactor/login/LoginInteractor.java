package com.example.mobsoft.webkorhaz.interactor.login;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.login.events.LoginEvent;
import com.example.mobsoft.webkorhaz.interactor.login.events.LogoutEvent;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;
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
        MobSoftApplication.injector.inject(this);
    }

    /**
     * Bejelentkezési próba
     */
    public void login(User user){
        LoginEvent event = new LoginEvent();
        try {
            User activeUser = HttpNetwork.startLogin(user);
            // user and activeUser must be merged.
            event.setUser(activeUser);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }


    /**
     * Bejelentkezési próba
     */
    public void logout(){
        LogoutEvent event = new LogoutEvent();
        try {
            User activeUser = HttpNetwork.startLogout();
            event.setUser(activeUser);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
