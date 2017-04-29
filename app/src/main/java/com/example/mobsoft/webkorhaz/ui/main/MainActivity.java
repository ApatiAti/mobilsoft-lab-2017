package com.example.mobsoft.webkorhaz.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.navigation.NavigationActivity;
import com.example.mobsoft.webkorhaz.ui.util.adapter.AppointmentAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreen {

    DateFormat dateFormat;
    RecyclerView recyclerView;
    AppointmentAdapter appointmentAdapter;
    private List<Appointment> appointmentList = new ArrayList<>();
    User currentUser;

    @Inject
    MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);

        currentUser = ((MobSoftApplication) getApplication()).getCurrentUser();
        dateFormat = new SimpleDateFormat(getString(R.string.fullDateTimeFormat));
        appointmentAdapter = new AppointmentAdapter(appointmentList, dateFormat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_apppointment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(appointmentAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Appointment appointment = appointmentList.get(position);
                Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
                intent.putExtra(getString(R.string.resource_intent_appointment), appointment);
//                intent.putExtra(getString(R.string.resource_intent_appointment_position), position);
                startActivity(intent);
            }

//            @Override
//            public void onLongClick(View view, int position) {
//                Appointment movie = appointmentList.get(position);
//                Toast.makeText(getApplicationContext(), "Hosszan " + movie.getDepartmentName() + " is selected!", Toast.LENGTH_SHORT).show();
//            }
        }));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(R.string.title_activity_main);
        // Sets the Toolbar to act as the ActionBar
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);

        // TODO ez így nem jó, mert ha saját magára navigálunk vissza akkor a presenter már fel van iratkozva erre aaz eseményre
        mainPresenter.loadAppointmentFromDb(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }


    // Menu ikonok inflate-elése az actionbar-ban
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Menu elemek onClick eseményének kezelése
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miNavigation:
                navigateToNavigationActivity();
                return true;
            case R.id.miRefresh:
                Toast.makeText(this, getString(R.string.main_refresh_from_network) , Toast.LENGTH_SHORT).show();
                mainPresenter.refreashAppointments(currentUser);
                return true;
            case R.id.miRefreshDepartment:
                Toast.makeText(this, getString(R.string.main_refresh_department_from_network) , Toast.LENGTH_SHORT).show();
                mainPresenter.refreshDepartmentData();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *  Átnavigálunk a NavigationActivity-re úgyhogy az nem kerül a BackStackre
     */
    private void navigateToNavigationActivity() {
        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(intent);
    }

    /**
     * Feltölti a képernyőt appointmentekkel
     * @param appointments
     */
    @Override
    public void showAppointments(List<Appointment> appointments) {
        appointmentList.clear();
        appointmentList.addAll(appointments);

        appointmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}