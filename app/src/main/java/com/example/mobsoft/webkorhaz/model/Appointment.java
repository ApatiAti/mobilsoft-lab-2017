package com.example.mobsoft.webkorhaz.model;

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

    private String complaint;
    private Long patientId;
    private Long consultationHourId; // ez tényleg kell?

    public Appointment() {
    }

    public Appointment(Long id, Date beginDate, Date endDate, String room, String doctorsName, String departmentName, String complaint, Long patientId, Long consultationHourId) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.room = room;
        this.doctorsName = doctorsName;
        this.departmentName = departmentName;
        this.complaint = complaint;
        this.patientId = patientId;
        this.consultationHourId = consultationHourId;
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
