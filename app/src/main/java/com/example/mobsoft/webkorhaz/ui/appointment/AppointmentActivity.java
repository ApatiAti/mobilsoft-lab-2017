package com.example.mobsoft.webkorhaz.ui.appointment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;

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

    /**
     * Módosíthatóvá teszi az Apponitmentet
     */
    public void editAppointment(){

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, "Hiba történt! " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAppointment(Appointment appointment) {

    }

    public void saveAppointment(Appointment appointment) {
        appointmentPresenter.saveAppointment(appointment);
    }

    public void refreshAppointmentFromServer(Appointment appointment) {
        appointmentPresenter.refreshAppointmentFromServer(appointment);
    }
}
