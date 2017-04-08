package com.example.mobsoft.webkorhaz.ui.consultationHourList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class ConsultationHourListActivity extends AppCompatActivity implements ConsultationHourListScreen {

    @Inject
    ConsultationHourListPresenter consultationHourListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        consultationHourListPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        consultationHourListPresenter.detachScreen();
    }


    @Override
    public void showConsultationHourList() {
        consultationHourListPresenter.showConsultationHourList(null);
    }

    @Override
    public void showApointmentCreateScreen() {

    }
}
