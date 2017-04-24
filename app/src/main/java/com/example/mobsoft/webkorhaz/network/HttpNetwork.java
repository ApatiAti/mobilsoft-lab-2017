package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.dto.DepartmentData;

import java.util.ArrayList;
import java.util.Date;
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
        List<ConsultationHourDTO> list = new ArrayList<>();
        ConsultationHourDTO dto = new ConsultationHourDTO();
        Date date = new Date();
        dto.setBeginDate(date);
        dto.setEndDate(new Date(date.getTime()+ 1000*60*60));
        dto.setAvailable(10);
        dto.setMaxNumberOfPatient(12);
        dto.setTpye("Vállon veregetés");

        list.add(dto);
        return list;
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
