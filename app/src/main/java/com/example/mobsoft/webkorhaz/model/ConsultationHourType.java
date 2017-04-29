package com.example.mobsoft.webkorhaz.model;

import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by Apati on 2017.04.24..
 */
@Table
public class ConsultationHourType implements Serializable{
    private Long id;
    private Long consultationHourTypeId;
    private String type;

    private Department department;

    public ConsultationHourType() {
    }

    public ConsultationHourType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public ConsultationHourType(Long id, Long consultationHourTypeId, String type) {
        this.id = id;
        this.consultationHourTypeId = consultationHourTypeId;
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsultationHourTypeId() {
        return consultationHourTypeId;
    }

    public void setConsultationHourTypeId(Long consultationHourTypeId) {
        this.consultationHourTypeId = consultationHourTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
