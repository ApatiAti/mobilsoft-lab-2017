package com.example.mobsoft.webkorhaz.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Apati on 2017.04.05..
 */

public class ConsultationHourDto implements Serializable {
//      "room": "string",
//      "doctorsName": "string",
    public Long consultationHourId;
    public String tpyeId;
    // STring
    public Date beginDate;
    public Date endDate;
    public int maxNumberOfPatient;
    public int currentPatientCount;


//   ap   "appointmentId": 0,
//   ok   "beginDate": "2017-04-29T19:57:22.029Z",
//   ok   "endDate": "2017-04-29T19:57:22.029Z",
//      "room": "string",
//      "doctorsName": "string",
//   ap   "complaints": "string",
//   ap   "patientName": "string",
//   ok   "consultationHourId": 0,
//   dp   "departmentId": 0,
//   dp   "consultationTypeId": 0


    public ConsultationHourDto() {
    }

    public ConsultationHourDto(Long consultationHourId, String tpyeId, Date beginDate, Date endDate, int maxNumberOfPatient, int currentPatientCount) {
        this.consultationHourId = consultationHourId;
        this.tpyeId = tpyeId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.maxNumberOfPatient = maxNumberOfPatient;
        this.currentPatientCount = currentPatientCount;
    }

    //    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }


    public Long getConsultationHourId() {
        return consultationHourId;
    }

    public void setConsultationHourId(Long consultationHourId) {
        this.consultationHourId = consultationHourId;
    }

    public String getTpyeId() {
        return tpyeId;
    }

    public void setTpyeId(String tpyeId) {
        this.tpyeId = tpyeId;
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

    public int getCurrentPatientCount() {
        return currentPatientCount;
    }

    public void setCurrentPatientCount(int currentPatientCount) {
        this.currentPatientCount = currentPatientCount;
    }
}