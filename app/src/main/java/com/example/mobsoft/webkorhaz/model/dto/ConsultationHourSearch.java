package com.example.mobsoft.webkorhaz.model.dto;

import java.util.Date;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourSearch {
    private String department;
    private String type;
    private Date beginDate;
    private Date endDate;

    public ConsultationHourSearch() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
