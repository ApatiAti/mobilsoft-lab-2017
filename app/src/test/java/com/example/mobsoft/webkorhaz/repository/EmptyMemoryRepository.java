package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apati on 2017.04.28..
 */

public class EmptyMemoryRepository extends MemoryRepository {

    @Override
    public void open(Context context) {
        appointmentList = new ArrayList<>();
        departmentList = new ArrayList<>();
        userList = new ArrayList<>();
        consultiConsultationHourTypeList = new ArrayList<>();
    }
}
