package com.example.mobsoft.webkorhaz.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.navigation.NavigationActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreen {

    ListView listView;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);

        listView = (ListView) findViewById(R.id.mainListAppointment);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
                // adatokat át kellene adni vhogy
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_main);
        // Sets the Toolbar to act as the ActionBar
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);

        mainPresenter.loadAppointmentFromDb();
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
                Toast.makeText(this, "//TODO Refresh!\n" , Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *  Átnavigálunk a NavigationActivity-re úgyhogy az nem kerül a BackStackre
     */
    private void navigateToNavigationActivity() {
        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        // Hogy ne tudjunk vissza navigálni
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    /**
     * Feltölti a képernyőt appointmentekkel
     * @param appointments
     */
    @Override
    public void showAppointments(List<Appointment> appointments) {
        AppointmentListAdapter adapter = new AppointmentListAdapter(getBaseContext(), R.layout.list_item_appointment, appointments);

        listView.setAdapter(adapter);

    }

    @Override
    public void showErrorAtLoadFromDb(String message) {
        Toast.makeText(this, "Hiba történt az adatbázis olvasása közben!\n" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorAtRefreshFromServer(String message) {
        Toast.makeText(this, "Hiba történt a szerver elérése közben!\n" + message, Toast.LENGTH_SHORT).show();
    }
}