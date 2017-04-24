package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.dto.DepartmentData;

import java.util.ArrayList;
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


    public static List<DepartmentData> getDepartmentData() {
        List<DepartmentData> test = new ArrayList<>();
        DepartmentData data = new DepartmentData(1l, "Szemészet", null);
        DepartmentData data2 = new DepartmentData(2l, "Ortopéd", null);

        List<ConsultationHourType> typeList = new ArrayList<>();
        ConsultationHourType test1 = new ConsultationHourType(3l, "Vizsgálat");
        ConsultationHourType test2 = new ConsultationHourType(4l, "Szem cucc");
        ConsultationHourType test3 = new ConsultationHourType(5l, "Szem műtár");
        typeList.add(test1);
        typeList.add(test2);
        typeList.add(test3);

        data.setConsultationHourTypeList(typeList);

        List<ConsultationHourType> typeList2 = new ArrayList<>();
        ConsultationHourType test4 = new ConsultationHourType(6l, "Láb cucc");
        typeList2.add(test1);
        typeList2.add(test4);

        data2.setConsultationHourTypeList(typeList2);

        test.add(data);
        test.add(data2);
        return test;
    }
}
