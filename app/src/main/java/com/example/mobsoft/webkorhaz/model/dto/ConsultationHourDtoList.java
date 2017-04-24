package com.example.mobsoft.webkorhaz.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Apati on 2017.04.24..
 */

public class ConsultationHourDtoList implements Serializable{
    List<ConsultationHourDTO> list;

    public ConsultationHourDtoList(List<ConsultationHourDTO> list) {
        this.list = list;
    }

    public List<ConsultationHourDTO> getList() {
        return list;
    }

    public void setList(List<ConsultationHourDTO> list) {
        this.list = list;
    }
}
