package com.example.mobsoft.webkorhaz.ui.appointment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;

import javax.inject.Inject;


/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class AppointmentActivity extends AppCompatActivity implements AppointmentScreen {

    @Inject
    AppointmentPresenter appointmentPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        appointmentPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        appointmentPresenter.detachScreen();
    }


    @Override
    public void showAppointment(int id) {

    }

    @Override
    public void saveAppointment(Object appointment) {

    }

    @Override
    public void reloadAppointment(Object appointment) {

    }
}
