package com.example.mobsoft.webkorhaz.model.dto;

import java.util.Date;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourSearch {
    private String departmentName;
    private Long typeId;
    // TODO string
    private Date beginDate;
    private Date endDate;

    public ConsultationHourSearch() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
