package com.example.mobsoft.webkorhaz.ui.navigation;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromServerEvents;
import com.example.mobsoft.webkorhaz.interactor.login.LoginInteractor;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class NavigationPresenter extends Presenter<NavigationScreen> {

    @Inject
    LoginInteractor loginInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public NavigationPresenter() {
    }

    @Override
    public void attachScreen(NavigationScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void logout(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loginInteractor.logout();
            }
        });
    }

    /**
     * {@link LoadAppointmentListFromServerEvents} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(LoadAppointmentListFromServerEvents event) {
        Throwable throwable = event.getThrowable();
        if (throwable != null) {
            throwable.printStackTrace();
            if (screen != null) {
                screen.error(throwable.getLocalizedMessage());
            }
            Log.e("DB", "Error reading appointments from DB", throwable);
        } else {
            if (screen != null) {
                screen.afterLogout();
            }
        }
    }
}
