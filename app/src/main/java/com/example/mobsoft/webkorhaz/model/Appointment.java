package com.example.mobsoft.webkorhaz.model;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Apati on 2017.04.03..
 */
@Table
public class Appointment implements Serializable {
    /* TODO ez nem lesz jó mivel a szerver az consultationOHour-okat és appointment-ekete tárol
    mind a kettőt el kéne tárolni hasonlóan mitn a szerver oldalon.
    Továbbá ameg kell oldani azt hogy a user-enként külön legyenek eltárolva a telóban a dolgok
    */
    private Long id = null;
    private Date beginDate;
    private Date endDate;
    private String room;
    private String doctorsName;
    private String departmentName;
    private String chType;

    private String complaint;
    private Long patientId;
    private Long consultationHourId; // ez tényleg kell?

    public Appointment() {
    }

    public Appointment(Long id, Date beginDate, Date endDate, String room, String doctorsName, String departmentName, String complaint, Long patientId, Long consultationHourId, String chType) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.room = room;
        this.doctorsName = doctorsName;
        this.departmentName = departmentName;
        this.complaint = complaint;
        this.patientId = patientId;
        this.consultationHourId = consultationHourId;
        this.chType = chType;
    }

    public Appointment(Appointment oldAppointment) {
        this.id = oldAppointment.getId();
        this.beginDate = oldAppointment.getBeginDate();
        this.endDate = oldAppointment.getEndDate();
        this.room = oldAppointment.getRoom();
        this.doctorsName = oldAppointment.getDoctorsName();
        this.departmentName = oldAppointment.getDepartmentName();
        this.complaint = oldAppointment.getComplaint();
        this.patientId = oldAppointment.getPatientId();
        this.consultationHourId = oldAppointment.getConsultationHourId();
        this.chType = oldAppointment.getChType();
    }

    public Appointment(ConsultationHourDTO item) {
//        this.id = oldAppointment.getId();
        this.beginDate = item.getBeginDate();
        this.endDate = item.getEndDate();
        this.room = "Ib025";
        this.doctorsName = "Doktor";
        this.departmentName = "department";
        this.complaint = "";
        this.patientId = 123l;
        this.consultationHourId = 1l;
        this.chType = item.getTpye();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getConsultationHourId() {
        return consultationHourId;
    }

    public void setConsultationHourId(Long consultationHourId) {
        this.consultationHourId = consultationHourId;
    }

    public String getChType() {
        return chType;
    }

    public void setChType(String chType) {
        this.chType = chType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
