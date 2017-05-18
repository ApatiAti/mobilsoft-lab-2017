package com.example.mobsoft.webkorhaz.interactor.appointment.events;

import com.example.mobsoft.webkorhaz.R;

public enum AppointmentsEventCode {
    UPDATE(R.string.appointment_event_update),
    SAVE(R.string.appointment_event_save),
    DELETE(R.string.appointment_event_save);

    private final int messageStringId;

    AppointmentsEventCode(int message) {
        this.messageStringId = message;
    }

    public int getMessageStringId() {
        return messageStringId;
    }
}