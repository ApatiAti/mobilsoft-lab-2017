package com.example.mobsoft.webkorhaz.ui.navigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class NavigationActivity extends AppCompatActivity implements NavigationScreen {

    @Inject
    NavigationPresenter navigationPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigationPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        navigationPresenter.detachScreen();
    }

    @Override
    public void showConsultationHourSearch() {

    }

    @Override
    public void showAppointmnent() {

    }
}
