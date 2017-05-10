package com.example.mobsoft.webkorhaz.interactor.appointment.events;

import com.example.mobsoft.webkorhaz.model.Appointment;

import java.util.List;


public class LoadAppointmentListFromDbEvent {
	private AppointmentsEventCode code;
	private List<Appointment> appointments;
	private Throwable throwable;

	public LoadAppointmentListFromDbEvent() {
	}

	public AppointmentsEventCode getCode() {
		return code;
	}

	public void setCode(AppointmentsEventCode code) {
		this.code = code;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
