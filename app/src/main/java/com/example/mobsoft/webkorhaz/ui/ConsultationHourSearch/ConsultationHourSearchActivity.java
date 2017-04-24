package com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch;

import android.database.DataSetObservable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.dto.DepartmentData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class ConsultationHourSearchActivity extends AppCompatActivity implements ConsultationHourSearchScreen {

    List<DepartmentData> departmentDataList = new ArrayList<>();
    Map<Long, List<ConsultationHourType>> departmentTypeMap = new HashMap<>();

    Spinner spinnerDepartmentName;
    Spinner spinnerTypeName;

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
        Long departmentId = ((DepartmentData) spinnerDepartmentName.getSelectedItem()).getDepartmentId();
        List<ConsultationHourType> consultationHourTypes = departmentTypeMap.get(departmentId);


        ArrayAdapter<ConsultationHourType> consultationTypeArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, consultationHourTypes);
        consultationTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeName.setAdapter(consultationTypeArrayAdapter);

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
        if (!departmentDataList.isEmpty()) {

            ArrayAdapter<DepartmentData> departmentDataArrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentDataList);
            departmentDataArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDepartmentName.setAdapter(departmentDataArrayAdapter);

            DepartmentData key = departmentDataList.get(0);
            if (key != null) {
                spinnerDepartmentName.setSelection(0);
                initializingTypeNameSpinner();
            }
        }
    }


    public void searchConsultationHour() {
        // navigálás tovább az adatokkal.
//        consultationHourSearchPresenter.search(new ConsultationHourSearch());

        String departmentName = ((DepartmentData) spinnerDepartmentName.getSelectedItem()).getDepartmentName();
        String chTypeName= ((ConsultationHourType) spinnerTypeName.getSelectedItem()).getType();
        Toast.makeText(this, "Sikeres keresés: " + departmentName + " ; " + chTypeName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadDepartmentData(List<DepartmentData> dataList) {
        Map<Long, List<ConsultationHourType>> newMap = new HashMap<>();
        for (DepartmentData data : dataList){
            newMap.put(data.getDepartmentId(), data.getConsultationHourTypeList());
        }

        departmentDataList = dataList;
        departmentTypeMap = newMap;

        initializingSpinners();
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, "Hiba történt!\n" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchResults(List<ConsultationHourDTO> consultationHourDTOs) {

    }
}
