package com.example.mobsoft.webkorhaz.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch.ConsultationHourSearchActivity;
import com.example.mobsoft.webkorhaz.ui.consultationHourList.ConsultationHourListActivity;
import com.example.mobsoft.webkorhaz.ui.login.LoginActivity;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;

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
        setContentView(R.layout.activity_navigation);

        MobSoftApplication.injector.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_navigation);
        // Sets the Toolbar to act as the ActionBar
        setSupportActionBar(toolbar);
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
    public void afterLogout() {
        Intent i = new Intent(NavigationActivity.this, LoginActivity.class);
        ((MobSoftApplication) getApplication()).setCurrentUser(null);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    public void logout(View view){
        navigationPresenter.logout();
    }


    public void showConsultationHourSearch(View view) {
        Intent i = new Intent(NavigationActivity.this, ConsultationHourSearchActivity.class);
        startActivity(i);
    }


    public void showAppointmentList(View view) {
        Intent i = new Intent(NavigationActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void error(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
