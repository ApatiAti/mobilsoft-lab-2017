package com.example.mobsoft.webkorhaz.ui.appointment;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;


/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class AppointmentActivity extends AppCompatActivity implements AppointmentScreen {

    DateFormat fullDateTimeFormat;
    DateFormat timeFormat;

    Appointment oldAppointment;
    Appointment activeAppointment;

    @Inject
    AppointmentPresenter appointmentPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        MobSoftApplication.injector.inject(this);

        fullDateTimeFormat = new SimpleDateFormat(getString(R.string.fullDateTimeFormat));
        timeFormat = new SimpleDateFormat(getString(R.string.timeFormat));

        oldAppointment = (Appointment) getIntent().getSerializableExtra(getString(R.string.resource_intent_appointment));
        refreshAppointmentFromOldValue(oldAppointment);

        EditText editTextDoctor = (EditText) findViewById(R.id.appointmentEditDoctor);
        EditText editTextRoom = (EditText) findViewById(R.id.appointmentEditRoom);
        EditText editTextDate = (EditText) findViewById(R.id.appointmentEditDate);
        EditText editTextComplaints = (EditText) findViewById(R.id.appointmentEditComplaints);

        String dateInterval = fullDateTimeFormat.format(activeAppointment.getBeginDate()) +  " - " + timeFormat.format(activeAppointment.getEndDate());

        editTextDoctor.setText(activeAppointment.getDoctorsName());
        editTextRoom.setText(activeAppointment.getRoom());
        editTextDate.setText(dateInterval);
        editTextComplaints.setText(activeAppointment.getComplaint());

        initToolBar(activeAppointment);
    }

    private void initToolBar(Appointment appointment) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(appointment.getDepartmentName());
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    public void showMessage(String message) {
        Toast.makeText(this, "Hiba történt! " + message, Toast.LENGTH_SHORT).show();
    }

    public void saveAppointment(Appointment appointment) {
        appointmentPresenter.saveAppointment(appointment);
    }

    public void refreshAppointmentFromOldValue(Appointment oldAppointment) {
        activeAppointment = new Appointment(oldAppointment);
    }


    // Toolbar back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
