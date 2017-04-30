package com.example.mobsoft.webkorhaz.model.dto;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Apati on 2017.04.03..
 */
public class AppointmentDto {
    private Long appointmentId;
  // TODO String
    private Date beginDate;
    private Date endDate;
    private String room;
    private String doctorsName;
    private String complaints;
    private String patientName;
    private Long consultationHourId;
    private Long departmentId;
    @SerializedName(value = "consultationTypeId")
    private Long consultationHourTypeId;

    public AppointmentDto() {
    }

    public AppointmentDto(Appointment appointment) {
        this.appointmentId = appointment.getAppointmentId();
        this.beginDate = appointment.getBeginDate();
        this.endDate = appointment.getEndDate();
        this.room = appointment.getRoom();
        this.doctorsName = appointment.getDoctorsName();
        this.complaints = appointment.getComplaints();
        this.patientName = appointment.getPatient().getUsername();
        this.consultationHourId = appointment.getConsultationHourId();
        this.departmentId = appointment.getDepartment().getDepartmentId();
        this.consultationHourTypeId = appointment.getConsultationHourType().getConsultationHourTypeId();
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getConsultationHourId() {
        return consultationHourId;
    }

    public void setConsultationHourId(Long consultationHourId) {
        this.consultationHourId = consultationHourId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getConsultationHourTypeId() {
        return consultationHourTypeId;
    }

    public void setConsultationHourTypeId(Long consultationHourTypeId) {
        this.consultationHourTypeId = consultationHourTypeId;
    }
}
