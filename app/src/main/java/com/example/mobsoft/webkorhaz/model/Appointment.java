package com.example.mobsoft.webkorhaz.model;

import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by Apati on 2017.04.03..
 */
@Table
public class Appointment {
    /* TODO ez nem lesz jó mivel a szerver az consultationOHour-okat és appointment-ekete tárol
    mind a kettőt el kéne tárolni hasonlóan mitn a szerver oldalon.
    Továbbá ameg kell oldani azt hogy a user-enként külön legyenek eltárolva a telóban a dolgok
    */
    private Long id = null;
    private Date beginDate;
    private Date endDate;
    private String room;
    private String doctorsName;

    private String complaint;
    private Long patientId;
    private Long consultationHourId; // ez tényleg kell?

    public Appointment() {
    }

    public Appointment(Long id, Date beginDate, Date endDate, String room, String doctorsName, String complaint, Long patientId, Long consultationHourId) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.room = room;
        this.doctorsName = doctorsName;
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
}
