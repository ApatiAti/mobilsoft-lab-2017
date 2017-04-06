package com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class ConsultationHourSearchActivity extends AppCompatActivity implements ConsultationHourSearchScreen {

    @Inject
    ConsultationHourSearchPresenter consultationHourSearchPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        consultationHourSearchPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        consultationHourSearchPresenter.detachScreen();
    }


    public void searchConsultationHour() {

    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, "Hiba történt!\n" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchResults(List<ConsultationHourDTO> consultationHourDTOs) {

    }
}
