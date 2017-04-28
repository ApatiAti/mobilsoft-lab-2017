package com.example.mobsoft.webkorhaz.interactor.consultationhour.events;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;

import java.util.List;

/**
 * Created by Apati on 2017.04.05..
 */

public class SearchConsultationHourEvent {
    private int code;
    private List<ConsultationHourDto> consultationHourDTOs;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public SearchConsultationHourEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ConsultationHourDto> getConsultationHourDtos() {
        return consultationHourDTOs;
    }

    public void setConsultationHourDtos(List<ConsultationHourDto> consultationHourDTOs) {
        this.consultationHourDTOs = consultationHourDTOs;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

//</editor-fold>
}
