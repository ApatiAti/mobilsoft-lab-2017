package com.example.mobsoft.webkorhaz.ui.main;

import com.example.mobsoft.webkorhaz.model.Appointment;

import java.util.List;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public interface MainScreen {

    void showAppointments(List<Appointment> appointments);

    void showErrorAtLoad(String message);

    void showErrorAtRefreash(String message);
}