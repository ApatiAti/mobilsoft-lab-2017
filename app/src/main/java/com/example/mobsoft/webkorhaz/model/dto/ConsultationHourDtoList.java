package com.example.mobsoft.webkorhaz.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Apati on 2017.04.24..
 */

public class ConsultationHourDtoList implements Serializable{
    List<ConsultationHourDto> list;

    public ConsultationHourDtoList(List<ConsultationHourDto> list) {
        this.list = list;
    }

    public List<ConsultationHourDto> getList() {
        return list;
    }

    public void setList(List<ConsultationHourDto> list) {
        this.list = list;
    }
}
