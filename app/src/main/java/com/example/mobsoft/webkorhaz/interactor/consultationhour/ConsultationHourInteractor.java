package com.example.mobsoft.webkorhaz.interactor.consultationhour;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.RefreshDepartmentsDataEvent;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.SearchConsultationHourEvent;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;
import com.example.mobsoft.webkorhaz.network.todo.DepartmentApi;
import com.example.mobsoft.webkorhaz.repository.Repository;

import java.net.HttpURLConnection;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.HTTP;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourInteractor {
    @Inject
    DepartmentApi departmentApi;
    @Inject
    Repository repository;

    @Inject
    EventBus bus;

    public ConsultationHourInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void searchConsultationHour(ConsultationHourSearch searchParam){
        SearchConsultationHourEvent event = new SearchConsultationHourEvent();
        try {
            List<ConsultationHourDto> consultationHourDTOList = HttpNetwork.seachConsultationHour(searchParam);
            event.setConsultationHourDtos(consultationHourDTOList);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }


    public Object getDepartmentsDataFromServer() {
        return null;
    }

    public void refreshDepartmentData() {
        Call<List<Department>> departmentsAndTypesGet = departmentApi.getDepartmentsAndTypesGet();
        RefreshDepartmentsDataEvent event = new RefreshDepartmentsDataEvent();
        try {

            Response<List<Department>> execute = departmentsAndTypesGet.execute();
            int responseCode = execute.code();

            if(HttpURLConnection.HTTP_OK == responseCode ){
                List<Department> departmentData = execute.body();

                saveDepartmentList(departmentData);

                event.setDepartment(departmentData);
                bus.post(event);
            } else {
                throw new RuntimeException("Network error!");
            }
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    private void saveDepartmentList(List<Department> departmentData) {
        for (Department department: departmentData) {


        }
    }
}
