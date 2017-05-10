package com.example.mobsoft.webkorhaz.ui.main;

import com.example.mobsoft.webkorhaz.model.Appointment;

import java.util.List;



public interface MainScreen {

    void showAppointments(List<Appointment> appointments);

    void showMessage(String errorMessage);
}