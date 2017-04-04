package com.example.mobsoft.webkorhaz.ui.login;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.appointment.events.GetAppoinmentsEvent;
import com.example.mobsoft.webkorhaz.interactor.login.LoginInteractor;
import com.example.mobsoft.webkorhaz.interactor.login.events.LoginEvent;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.concurrent.Executor;

import javax.annotation.security.RunAs;
import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class LoginPresenter extends Presenter<LoginScreen> {

    @Inject
    LoginInteractor loginInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public LoginPresenter() {
    }

    public void login(final User user){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loginInteractor.login(user);
            }
        });
    }

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void onEventMainThread(LoginEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.loginError();
            }
            Log.e("Networking", "Error at login in", event.getThrowable());
        } else {
            if (screen != null) {
                screen.loginSucces();
            }
        }
    }
}