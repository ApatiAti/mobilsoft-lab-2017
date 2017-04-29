package com.example.mobsoft.webkorhaz.ui.appointment;

import com.example.mobsoft.webkorhaz.model.Appointment;

/**
 * Created by mobsoft on 2017. 03. 31..
 */
public interface AppointmentScreen {

    void showMessage(String message);

    void appointmentSaved(Appointment appointment);
}
