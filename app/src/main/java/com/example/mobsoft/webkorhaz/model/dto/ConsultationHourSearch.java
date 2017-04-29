package com.example.mobsoft.webkorhaz.model.dto;

import java.util.Date;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourSearch {
    private Long departmentId;
    private Long typeId;
    // TODO string
    private Date beginDate;
    private Date endDate;

    public ConsultationHourSearch() {
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
