package com.example.mobsoft.webkorhaz.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Apati on 2017.04.24..
 */

public class DepartmentData implements Serializable {
    public Long departmentId;
    public String departmentName;
    public List<ConsultationHourType> consultationHourTypeList;

    public DepartmentData() {
    }

    public DepartmentData(Long departmentId, String departmentName, List<ConsultationHourType> consultationHourTypeList) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.consultationHourTypeList = consultationHourTypeList;
    }

    @Override
    public String toString() {
        return departmentName;
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
