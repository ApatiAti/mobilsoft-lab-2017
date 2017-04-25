package com.example.mobsoft.webkorhaz.interactor.consultationhour.events;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;

import java.util.List;

/**
 * Created by Apati on 2017.04.05..
 */

public class SearchConsultationHourEvent {
    private int code;
    private List<ConsultationHourDto> consultationHourDtos;
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

    public List<ConsultationHourDto> getConsultationHourDTOs() {
        return consultationHourDtos;
    }

    public void setConsultationHourDTOs(List<ConsultationHourDto> consultationHourDtos) {
        this.consultationHourDtos = consultationHourDtos;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

//</editor-fold>
}
