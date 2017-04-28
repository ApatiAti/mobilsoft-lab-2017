package com.example.mobsoft.webkorhaz.interactor.consultationhour;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.SearchConsultationHourEvent;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourInteractor {
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
}
