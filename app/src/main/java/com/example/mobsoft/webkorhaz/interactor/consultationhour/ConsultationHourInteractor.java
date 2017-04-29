package com.example.mobsoft.webkorhaz.interactor.consultationhour;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.RefreshDepartmentsDataEvent;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.SearchConsultationHourEvent;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.network.todo.ConsultationHourApi;
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
    ConsultationHourApi consultationHourApi;
    @Inject
    Repository repository;

    @Inject
    EventBus bus;

    public ConsultationHourInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void searchConsultationHour(ConsultationHourSearch searchParam){
        Call<List<ConsultationHourDto>> listCall = consultationHourApi.consultationHourSearchPost(searchParam);

        SearchConsultationHourEvent event = new SearchConsultationHourEvent();
        try {
            Response<List<ConsultationHourDto>> execute = listCall.execute();
            int responesCode = execute.code();

            if(HttpURLConnection.HTTP_OK == responesCode){
                List<ConsultationHourDto> consultationHourDTOList = execute.body();

                event.setConsultationHourDtos(consultationHourDTOList);
                bus.post(event);
            } else {
                throw new RuntimeException("Hiba történt a keresés során");
            }
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }


    public void getDepartmentsDataFromDb() {
        // TODO új eventett létrehozni hozzá
        RefreshDepartmentsDataEvent event = new RefreshDepartmentsDataEvent();
        try {
            List<Department> departmentList = repository.getDepartments();

            for (Department department: departmentList) {
                department.setConsultationHourTypeList(repository.getConsultationHourTypeByDepartment_Id(department.getId()));
            }

            event.setDepartment(departmentList);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void refreshDepartmentDataFromServer() {
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
                dbDepartment = repository.saveDepartment(dbDepartment);

                List<ConsultationHourType> dbCHType = saveOrUpdateConsultationHourList(dbDepartment, department.getConsultationHourTypeList());
                dbDepartment.setConsultationHourTypeList(dbCHType);

            } else {
                dbDepartment = repository.saveDepartment(department);

                List<ConsultationHourType> newConsultationHourTypeList = new ArrayList<>();
                for (ConsultationHourType consultationHourType : department.getConsultationHourTypeList()) {
                    consultationHourType.setDepartment(dbDepartment);
                    consultationHourType = repository.saveConsultationHourType(consultationHourType);
                    newConsultationHourTypeList.add(consultationHourType);
                }

                dbDepartment.setConsultationHourTypeList(newConsultationHourTypeList);
            }

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
                dbConsultationHourType.setDepartment(dbDepartment);
            }

            dbConsultationHourType = repository.saveConsultationHourType(dbConsultationHourType);
            returnList.add(dbConsultationHourType);
        }

        return returnList;
    }
}
