package com.example.mobsoft.webkorhaz;

import android.app.Application;

import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.repository.Repository;
import com.example.mobsoft.webkorhaz.ui.UIModule;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class MobSoftApplication extends Application {

    /**
     * Stores the current logged user
     */
    private User currentUser;

    @Inject
    Repository repository;

    public static MobSoftApplicationComponent injector;


	public void setInjector(MobSoftApplicationComponent appComponent) {
		injector = appComponent;
		injector.inject(this);
		repository.open(getApplicationContext());
	}

	@Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerMobSoftApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();

        injector.inject(this);

        currentUser = null;
        repository.open(getApplicationContext());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}