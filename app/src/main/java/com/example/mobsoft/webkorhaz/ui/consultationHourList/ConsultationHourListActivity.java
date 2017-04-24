package com.example.mobsoft.webkorhaz.ui.consultationHourList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDtoList;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;
import com.example.mobsoft.webkorhaz.ui.util.adapter.ConsultationHourListAdapter;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class ConsultationHourListActivity extends AppCompatActivity implements ConsultationHourListScreen {

    private List<ConsultationHourDTO> consultationHourDTOList;

    ListView listView;

    @Inject
    ConsultationHourListPresenter consultationHourListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultationhour_list);

        MobSoftApplication.injector.inject(this);

        ConsultationHourDtoList dtoList = (ConsultationHourDtoList) getIntent().getSerializableExtra(getString(R.string.resource_intent_consultationHour));
        String departmentName = (String) getIntent().getSerializableExtra(getString(R.string.departmentName));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setTitle(departmentName);
        setSupportActionBar(toolbar);


        consultationHourDTOList = dtoList.getList();

        ConsultationHourListAdapter adapter = new ConsultationHourListAdapter(getBaseContext(), R.layout.list_item_consultationhour_dto, consultationHourDTOList);

        listView = (ListView) findViewById(R.id.consultatioHourSearchList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConsultationHourListActivity.this, AppointmentActivity.class);
                // adatokat át kellene adni vhogy
                startActivity(intent);
            }
        });

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


    @Deprecated
    @Override
    public void showConsultationHourList() {
        consultationHourListPresenter.showConsultationHourList(null);
    }

    @Deprecated
    @Override
    public void showApointmentCreateScreen() {

    }
}
