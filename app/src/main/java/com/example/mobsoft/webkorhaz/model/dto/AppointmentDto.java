package com.example.mobsoft.webkorhaz.model.dto;

import com.example.mobsoft.webkorhaz.model.Appointment;

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
    private String complaint;
    private String patientName;
    private Long consultationHourId;
    private Long departmentId;
    private Long consultationHouTypeId;

    public AppointmentDto() {
    }

    public AppointmentDto(Appointment appointment) {
        this.appointmentId = appointment.getAppointmentId();
        this.beginDate = appointment.getBeginDate();
        this.endDate = appointment.getEndDate();
        this.room = appointment.getRoom();
        this.doctorsName = appointment.getDoctorsName();
        this.complaint = appointment.getComplaint();
        this.patientName = appointment.getPatient().getUsername();
        this.consultationHourId = appointment.getConsultationHourId();
        this.departmentId = appointment.getDepartment().getDepartmentId();
        this.consultationHouTypeId = appointment.getConsultationHourType().getConsultationHourTypeId();
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

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
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

    public Long getConsultationHouTypeId() {
        return consultationHouTypeId;
    }

    public void setConsultationHouTypeId(Long consultationHouTypeId) {
        this.consultationHouTypeId = consultationHouTypeId;
    }
}
