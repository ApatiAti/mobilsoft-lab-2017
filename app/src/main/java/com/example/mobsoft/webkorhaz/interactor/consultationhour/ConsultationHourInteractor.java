package com.example.mobsoft.webkorhaz.interactor.consultationhour;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.GetDepartmentsDataEvent;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.SearchConsultationHourEvent;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.model.dto.DepartmentData;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;
import com.example.mobsoft.webkorhaz.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourInteractor {
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
            List<ConsultationHourDTO> consultationHourDTOList = HttpNetwork.seachConsultationHour(searchParam);
            event.setConsultationHourDTOs(consultationHourDTOList);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getDepartmentsDataFromServer() {
        GetDepartmentsDataEvent event = new GetDepartmentsDataEvent();
        try {
            List<DepartmentData> departmentDataList = HttpNetwork.getDepartmentData();
            event.setDepartmentData(departmentDataList);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

}
