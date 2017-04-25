package com.example.mobsoft.webkorhaz.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourDto implements Serializable {
    // TODO nem kell departmentName de kell ID attr
    //    public String departmentName;
    public String tpye;
    public Date beginDate;
    public Date endDate;
    public int maxNumberOfPatient;
    public int available;

    public ConsultationHourDto() {
    }

//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }

    public String getTpye() {
        return tpye;
    }

    public void setTpye(String tpye) {
        this.tpye = tpye;
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

    public int getMaxNumberOfPatient() {
        return maxNumberOfPatient;
    }

    public void setMaxNumberOfPatient(int maxNumberOfPatient) {
        this.maxNumberOfPatient = maxNumberOfPatient;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}