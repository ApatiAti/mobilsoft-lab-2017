package com.example.mobsoft.webkorhaz.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Apati on 2017.04.24..
 */
@Table
public class Department implements Serializable {
    public Long id;
    public Long departmentId;
    public String departmentName;
    public List<ConsultationHourType> consultationHourTypeList;

    public Department() {
    }

    public Department(Long departmentId, String departmentName, List<ConsultationHourType> consultationHourTypeList) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.consultationHourTypeList = consultationHourTypeList;
    }

    @Override
    public String toString() {
        return departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<ConsultationHourType> getConsultationHourTypeList() {
        return consultationHourTypeList;
    }

    public void setConsultationHourTypeList(List<ConsultationHourType> consultationHourTypeList) {
        this.consultationHourTypeList = consultationHourTypeList;
    }

}
