package com.example.mobsoft.webkorhaz.ui.login;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.login.LoginInteractor;
import com.example.mobsoft.webkorhaz.interactor.login.events.LoginEvent;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;



public class LoginPresenter extends Presenter<LoginScreen> {

    public static final String LOGIN_PRESENTER = "LoginPresenter";
    @Inject
    LoginInteractor loginInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public LoginPresenter() {
    }

    public void startLogin(final User user){
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
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }



    /**
     * {@link LoginEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(LoginEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.loginError();
            }
            Log.e(LOGIN_PRESENTER, "Error at login in", event.getThrowable());
        } else {
            User user = event.getUser();
            if (user != null && screen != null) {
                screen.loginSucces(user);
            } else {
                screen.loginError();
                Log.e(LOGIN_PRESENTER, "Error at login in", event.getThrowable());
            }
        }
    }

}