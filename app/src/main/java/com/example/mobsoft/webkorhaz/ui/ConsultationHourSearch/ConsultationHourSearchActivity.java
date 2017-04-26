package com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDtoList;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.ui.MySlideDateTimeListener;
import com.example.mobsoft.webkorhaz.ui.consultationHourList.ConsultationHourListActivity;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class ConsultationHourSearchActivity extends AppCompatActivity implements ConsultationHourSearchScreen {

    static SimpleDateFormat simpleDateFormat;

    List<Department> departmentList = new ArrayList<>();
    Map<Long, List<ConsultationHourType>> departmentTypeMap = new HashMap<>();

    Spinner spinnerDepartmentName;
    Spinner spinnerTypeName;

    MySlideDateTimeListener startDateListener;
    MySlideDateTimeListener endDateListener;

    @Inject
    ConsultationHourSearchPresenter consultationHourSearchPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultationhour_search);
        MobSoftApplication.injector.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar
        setSupportActionBar(toolbar);


        simpleDateFormat = new SimpleDateFormat(getString(R.string.fullDateTimeFormat));

        TextView startDateTextView = (TextView) findViewById(R.id.chsearchDateBegin);
        TextView endDateTextView = (TextView) findViewById(R.id.chsearchDateEnd);

        Date sysdate = new Date();
        startDateTextView.setText(simpleDateFormat.format(sysdate));
        endDateTextView.setText(simpleDateFormat.format(sysdate));


        startDateListener = new MySlideDateTimeListener(startDateTextView, simpleDateFormat);
        endDateListener = new MySlideDateTimeListener(endDateTextView, simpleDateFormat);

        creatingSpinners();
    }

    private void creatingSpinners() {
        spinnerDepartmentName = (Spinner) findViewById(R.id.chSearchSpinnerDepartmentName);
        spinnerTypeName = (Spinner) findViewById(R.id.chSearchSpinnerTypeName);

        spinnerDepartmentName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initializingTypeNameSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void initializingTypeNameSpinner(){
        Long departmentId = getSelectedDepartmentData().getDepartmentId();
        List<ConsultationHourType> consultationHourTypes = departmentTypeMap.get(departmentId);


        ArrayAdapter<ConsultationHourType> consultationTypeArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, consultationHourTypes);
        consultationTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeName.setAdapter(consultationTypeArrayAdapter);

    }

    private Department getSelectedDepartmentData() {
        return (Department) spinnerDepartmentName.getSelectedItem();
    }

    @Override
    protected void onStart() {
        super.onStart();
        consultationHourSearchPresenter.attachScreen(this);

        consultationHourSearchPresenter.getDepartmentsDataFromServer();
        initializingSpinners();
    }

    @Override
    protected void onStop() {
        super.onStop();
        consultationHourSearchPresenter.detachScreen();
    }

    private void initializingSpinners(){
        if (!departmentList.isEmpty()) {

            ArrayAdapter<Department> departmentDataArrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentList);
            departmentDataArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDepartmentName.setAdapter(departmentDataArrayAdapter);

            Department key = departmentList.get(0);
            if (key != null) {
                spinnerDepartmentName.setSelection(0);
                initializingTypeNameSpinner();
            }
        }
    }

    public void searchConsultationHour(View view) {
        Department selectedDepartment = getSelectedDepartmentData();
        ConsultationHourType selectedConsultationHourType = getSelectedConsultationHourType();

        ConsultationHourSearch consultationHourSearch = new ConsultationHourSearch();
        consultationHourSearch.setBeginDate(startDateListener.getSelectedDate());
        consultationHourSearch.setEndDate(startDateListener.getSelectedDate());
        consultationHourSearch.setDepartmentName(selectedDepartment.getDepartmentName());
        consultationHourSearch.setType(selectedConsultationHourType.getType());

        consultationHourSearchPresenter.search(consultationHourSearch);

        String departmentName = selectedDepartment.getDepartmentName();
        String chTypeName = selectedConsultationHourType.getType();
        Toast.makeText(this, "Sikeres keresés: " + departmentName + " ; " + chTypeName, Toast.LENGTH_SHORT).show();

    }

    private ConsultationHourType getSelectedConsultationHourType() {
        return (ConsultationHourType)spinnerTypeName.getSelectedItem();
    }


    @Override
    public void loadDepartmentData(List<Department> dataList) {
        Map<Long, List<ConsultationHourType>> newMap = new HashMap<>();
        for (Department data : dataList){
            newMap.put(data.getDepartmentId(), data.getConsultationHourTypeList());
        }

        departmentList = dataList;
        departmentTypeMap = newMap;

        initializingSpinners();
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, "Hiba történt!\n" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchResults(List<ConsultationHourDto> consultationHourDtoList) {
        Intent intent = new Intent(this, ConsultationHourListActivity.class);
        intent.putExtra(getString(R.string.resource_intent_consultationHour), new ConsultationHourDtoList(consultationHourDtoList));
        startActivity(intent);
    }


    public void selectDateTime(View view) {
        SlideDateTimeListener listener;
        if (view.getId() ==  R.id.chsearchDateBegin ){
            listener = startDateListener;
        } else {
            listener = endDateListener;
        }

        new SlideDateTimePicker.Builder(getSupportFragmentManager())
                .setListener(listener)
                .setInitialDate(new Date())
                .setMinDate(new Date())
                //.setMaxDate(maxDate)
                .setIs24HourTime(true)
                .setTheme(SlideDateTimePicker.HOLO_LIGHT)
//                .setIndicatorColor(Color.parseColor("#990000"))
                .build()
                .show();
    }


}
