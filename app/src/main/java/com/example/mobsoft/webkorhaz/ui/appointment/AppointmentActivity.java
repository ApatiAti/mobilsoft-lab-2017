package com.example.mobsoft.webkorhaz.ui.appointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;




public class AppointmentActivity extends AppCompatActivity implements AppointmentScreen {

    DateFormat fullDateTimeFormat;
    DateFormat timeFormat;

    Appointment oldAppointment;
    Appointment activeAppointment;

    EditText editTextDoctor;
    EditText editTextCHType;
    EditText editTextRoom;
    EditText editTextDate;
    EditText editTextComplaints;

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
        refreshAppointmentFromOldValue();

        editTextDoctor = (EditText) findViewById(R.id.appointmentEditDoctor);
        editTextCHType = (EditText) findViewById(R.id.appointmentEditCHType);
        editTextRoom = (EditText) findViewById(R.id.appointmentEditRoom);
        editTextDate = (EditText) findViewById(R.id.appointmentEditDate);
        editTextComplaints = (EditText) findViewById(R.id.appointmentEditComplaints);

        refreshAppointmentView();

        initToolBar(activeAppointment);
    }

    private void refreshAppointmentView() {
        String dateInterval = fullDateTimeFormat.format(activeAppointment.getBeginDate()) +  " - " + timeFormat.format(activeAppointment.getEndDate());

        editTextDoctor.setText(activeAppointment.getDoctorsName());
        editTextCHType.setText(activeAppointment.getConsultationHourType().getType());
        editTextRoom.setText(activeAppointment.getRoom());
        editTextDate.setText(dateInterval);
        editTextComplaints.setText(activeAppointment.getComplaints());
    }

    private void initToolBar(Appointment appointment) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(appointment.getDepartment().getDepartmentName());
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToAppointmentList(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(AppointmentActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void refreshAppointmentFromOldValue() {
        activeAppointment = new Appointment(oldAppointment);
    }


    // Menu ikonok inflate-elése az actionbar-ban
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater().inflate(R.menu.menu_appointment, menu);
        return true;
    }

    // Menu elemek onClick eseményének kezelése
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miReload:
                refreshAppointmentFromOldValue();
                refreshAppointmentView();
                return true;
            case R.id.miSave:
                activeAppointment.setComplaints(editTextComplaints.getText().toString());
                appointmentPresenter.saveorUpdateAppointment(activeAppointment);
                return true;
            case R.id.miDelete:
                appointmentPresenter.deleteAppointment(activeAppointment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Toolbar back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
