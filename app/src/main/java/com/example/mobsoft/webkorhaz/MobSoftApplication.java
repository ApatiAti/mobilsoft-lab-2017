package com.example.mobsoft.webkorhaz;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.repository.Repository;
import com.example.mobsoft.webkorhaz.ui.UIModule;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;



public class MobSoftApplication extends Application {

    /**
     * Stores the current logged user
     */
    private User currentUser;
    private Tracker mTracker;

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
        Fabric.with(this, new Crashlytics());

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

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}