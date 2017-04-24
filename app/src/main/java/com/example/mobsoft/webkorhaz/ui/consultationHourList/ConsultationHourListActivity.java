package com.example.mobsoft.webkorhaz.ui.consultationHourList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDtoList;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.main.RecyclerTouchListener;
import com.example.mobsoft.webkorhaz.ui.util.adapter.ConsultationHourListAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class ConsultationHourListActivity extends AppCompatActivity implements ConsultationHourListScreen {

    static DateFormat timeFormat;
    static DateFormat fullDateTimeFormat;

    RecyclerView recyclerView;
    ConsultationHourListAdapter consultationHourListAdapter;
    private List<ConsultationHourDTO> consultationHourDtoList = new ArrayList<>();

    @Inject
    ConsultationHourListPresenter consultationHourListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultationhour_list);

        MobSoftApplication.injector.inject(this);

        timeFormat = new SimpleDateFormat(getString(R.string.timeFormat));
        fullDateTimeFormat = new SimpleDateFormat(getString(R.string.fullDateTimeFormat));

        ConsultationHourDtoList dtoList = (ConsultationHourDtoList) getIntent().getSerializableExtra(getString(R.string.resource_intent_consultationHour));
        String departmentName = (String) getIntent().getSerializableExtra(getString(R.string.departmentName));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setTitle(departmentName);
        setSupportActionBar(toolbar);


        consultationHourDtoList = dtoList.getList();
        consultationHourListAdapter = new ConsultationHourListAdapter(consultationHourDtoList, fullDateTimeFormat, timeFormat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consultationhour);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(consultationHourListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ConsultationHourDTO item = consultationHourDtoList.get(position);
                Intent intent = new Intent(ConsultationHourListActivity.this, AppointmentActivity.class);
                intent.putExtra(getString(R.string.resource_intent_consultationHourDto), item);
                startActivity(intent);
            }
        }));
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
