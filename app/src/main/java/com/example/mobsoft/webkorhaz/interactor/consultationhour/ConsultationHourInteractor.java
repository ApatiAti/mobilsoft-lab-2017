package com.example.mobsoft.webkorhaz.interactor.consultationhour;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.RefreshDepartmentsDataEvent;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.SearchConsultationHourEvent;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;
import com.example.mobsoft.webkorhaz.network.todo.DepartmentApi;
import com.example.mobsoft.webkorhaz.repository.Repository;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Response;

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

                departmentData = saveDepartmentList(departmentData);

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

    /**
     * A paraméterkéne átadott Departmentekkel a db-t frissítjük.
     * Ha léterzik a department  akkor felül írjuk
     * Ha nem létezik a db-nen ilyen department akko létrehozzuk
     * @param departmentData
     * @return
     */
    private List<Department> saveDepartmentList(List<Department> departmentData) {
        List<Department> newDepartmentList = new ArrayList<>();

        for (Department department: departmentData) {
            Department dbDepartment = repository.getDepartmentByDepartmentId(department.getDepartmentId());

            if (dbDepartment != null){
                dbDepartment.setDepartmentName(department.getDepartmentName());

                List<ConsultationHourType> dbCHType = saveOrUpdateConsultationHourList(dbDepartment, department.getConsultationHourTypeList());
                dbDepartment.setConsultationHourTypeList(dbCHType);

            } else {
                for (ConsultationHourType consultationHourType : department.getConsultationHourTypeList()) {
                    consultationHourType = repository.saveConsultationHourType(consultationHourType);
                }
                dbDepartment = department;
            }

            dbDepartment = repository.saveDepartment(dbDepartment);
            newDepartmentList.add(dbDepartment);
        }

        return newDepartmentList;
    }

    /**
     * Megadott db-ben meglévő department consulationHour-jait frissítjük a paraméterben megadott lista tartalmával.
     * Ha a parméterkéne kapott listában olyan elem szerepel amit még nem tartalmaz a db akkor létrehozunk egyett hozzá
     *
     * @param dbDepartment
     * @param consultationHourTypeList
     * @return
     */
    private List<ConsultationHourType> saveOrUpdateConsultationHourList(Department dbDepartment, List<ConsultationHourType> consultationHourTypeList) {
        List<ConsultationHourType> returnList = new ArrayList<>();

        Map<Long, ConsultationHourType> dbCHTypeMap = new HashMap<>();
        List<ConsultationHourType> dbCHTypeList = dbDepartment.getConsultationHourTypeList();
        if (dbCHTypeList != null) {
            for (ConsultationHourType consultationHourType : dbCHTypeList) {
                dbCHTypeMap.put(consultationHourType.getConsultationHourTypeId(), consultationHourType);
            }
        }

        for (ConsultationHourType consultationHourType : consultationHourTypeList){
            ConsultationHourType dbConsultationHourType = dbCHTypeMap.get(consultationHourType.getConsultationHourTypeId());

            if (dbConsultationHourType != null){
                dbConsultationHourType.setType(consultationHourType.getType());
            } else {
                dbConsultationHourType = consultationHourType;
            }

            dbConsultationHourType = repository.saveConsultationHourType(dbConsultationHourType);
            returnList.add(dbConsultationHourType);
        }

        return returnList;
    }
}
