package com.example.mobsoft.webkorhaz.ui.main;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.todo.FavouritesInteractor;
import com.example.mobsoft.webkorhaz.interactor.todo.events.GetFavouritesEvent;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.ui.Presenter;


import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 31..
 */


public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    FavouritesInteractor favouritesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public MainPresenter() {
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }

    public void getFavourites() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favouritesInteractor.getFavourites();
            }
        });
    }


    public void onEventMainThread(GetFavouritesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                for(Todo t : event.getTodos()){
                    screen.showMessage(t.getName());;
                }
            }
        }
    }
}
