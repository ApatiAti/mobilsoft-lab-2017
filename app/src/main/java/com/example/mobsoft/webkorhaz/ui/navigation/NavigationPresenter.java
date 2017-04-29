package com.example.mobsoft.webkorhaz.ui.navigation;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.login.LoginInteractor;
import com.example.mobsoft.webkorhaz.interactor.login.events.LogoutEvent;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;

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


    @Override
    public void attachScreen(NavigationScreen screen) {
        injector.inject(this);
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
     * {@link LogoutEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(LogoutEvent event) {
        Throwable throwable = event.getThrowable();
        if (throwable != null) {
            throwable.printStackTrace();
            if (screen != null) {
                screen.error(throwable.getMessage());
            }
            Log.e("DB", "Error reading appointments from DB", throwable);
        } else {
            if (screen != null) {
                screen.afterLogout();
            }
        }
    }
}
