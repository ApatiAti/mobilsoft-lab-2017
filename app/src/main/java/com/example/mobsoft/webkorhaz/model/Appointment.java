package com.example.mobsoft.webkorhaz.model;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Apati on 2017.04.03..
 */
@Table
public class Appointment implements Serializable {
    private Long id = null;
    private Long appointmentId;
    private Date beginDate;
    private Date endDate;
    private String room;
    private String doctorsName;
    private String complaint;
    private User patient;
    private Long consultationHourId;

    private Department department;
    private ConsultationHourType consultationHourType;

    public Appointment() {
    }

    public Appointment(Long appointmentId, Date beginDate, Date endDate, String room, String doctorsName, Department department, String complaint, User patient, Long consultationHourId, ConsultationHourType consultationHourType) {
        this.appointmentId = appointmentId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.room = room;
        this.doctorsName = doctorsName;
        this.department = department;
        this.complaint = complaint;
        this.patient = patient;
        this.consultationHourId = consultationHourId;
        this.consultationHourType = consultationHourType;
    }

    public Appointment(Appointment oldAppointment) {
        this.id = oldAppointment.getId();
        this.beginDate = oldAppointment.getBeginDate();
        this.endDate = oldAppointment.getEndDate();
        this.room = oldAppointment.getRoom();
        this.doctorsName = oldAppointment.getDoctorsName();
        this.department = oldAppointment.getDepartment();
        this.complaint = oldAppointment.getComplaint();
        this.patient = oldAppointment.getPatient();
        this.consultationHourId = oldAppointment.getConsultationHourId();
        this.consultationHourType = oldAppointment.getConsultationHourType();
    }

    public Appointment(ConsultationHourDto item) {
        this.beginDate = item.getBeginDate();
        this.endDate = item.getEndDate();
        this.room = item.getRoom();
        this.doctorsName = item.getDoctorsName();
//        this.department = new Department(1l, "Valami", null);
//        this.complaint = "";
//        this.patient = new User("Kovács valaki", "password");
        this.consultationHourId = item.getConsultationHourId();
//        this.consultationHourType = new ConsultationHourType(20l, 0L, "lábcuc");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Long getConsultationHourId() {
        return consultationHourId;
    }

    public void setConsultationHourId(Long consultationHourId) {
        this.consultationHourId = consultationHourId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ConsultationHourType getConsultationHourType() {
        return consultationHourType;
    }

    public void setConsultationHourType(ConsultationHourType consultationHourType) {
        this.consultationHourType = consultationHourType;
    }
}
