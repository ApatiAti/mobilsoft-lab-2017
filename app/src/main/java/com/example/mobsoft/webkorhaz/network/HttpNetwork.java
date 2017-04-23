package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;

import java.util.List;

/**
 * Created by Apati on 2017.04.08..
 */

public class HttpNetwork {
    public static User startLogin(User user) {
        // TODO
        return user;
    }

    public static List<ConsultationHourDTO> seachConsultationHour(ConsultationHourSearch searchParam) {
        return null;
    }

    public static List<Appointment> getAppointmentList(long userId) {
        return null;
    }

    public static boolean saveAppointment(Appointment appointment) {
        return true;
    }

    public static Appointment reloadAppointment(Appointment appointment) {
        return null;
    }

    public static User startLogout() {
        return null;
    }
}
