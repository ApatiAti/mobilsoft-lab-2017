package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MemoryRepository implements Repository {

	private static final long MINUTE = 60 * 1000;

	public static List<Appointment> appointmentList;

	@Override
	public void open(Context context) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, +2);

		Date beginDate = c.getTime();
		c.add(Calendar.HOUR_OF_DAY, +2);
		Date endDate = c.getTime();

		Appointment flight1 = new Appointment(1L, beginDate, endDate, "Ib025", "Dr Doktor", "Szemészet", "Fáj fejem", 11L, 12L, "Valami típus");

		c.add(Calendar.DAY_OF_YEAR, +1);
		beginDate = c.getTime();
		c.add(Calendar.HOUR_OF_DAY, +1);
		endDate = c.getTime();

		Appointment flight2 = new Appointment(2L, beginDate, endDate, "EB01", "Dr Doktor", "Ortopédia", "Cucc fejem", 15L, 17L, "láb cucc");

		appointmentList = new ArrayList<>();
		appointmentList.add(flight1);
		appointmentList.add(flight2);
	}

	@Override
	public void close() {

	}


	@Override
	public List<Appointment> getAppointments() {
		return appointmentList;
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		appointmentList.add(appointment);
	}

	@Override
	public void updateAppointment(List<Appointment> appointments) {
		return;
	}

	@Override
	public void removeAppointment(Appointment appointment) {
		appointmentList.remove(appointment);
	}

	@Override
	public boolean isInDB(Appointment appointment) {
		return appointmentList.contains(appointment);
	}


	/**
	 *  Labor miatt
	 */

	public static List<Todo> todos;

	public void openTodoRepo(Context context) {
		Todo flight1 = new Todo(1L,"todo one");
		Todo flight2 = new Todo(3L,"todo two");

		todos = new ArrayList<>();
		todos.add(flight1);
		todos.add(flight2);
	}

	@Override
	public List<Todo> getFavourites() {
		return todos;
	}

	@Override
	public void saveFavourite(Todo todo) {
		todos.add(todo);

	}

	@Override
	public void updateFavourites(List<Todo> todos) {
		for (int i = 0; i < this.todos.size(); i++) {
			Todo favourite = this.todos.get(i);
			for (Todo todo : todos) {
				if (todo.getId().equals(favourite.getId())) {
					this.todos.set(i, todo);
				}
			}
		}
	}

	@Override
	public void removeFavourite(Todo flight) {
		todos.remove(flight);
	}

	@Override
	public boolean isInDB(Todo flight) {
		return todos.contains(flight);
	}

}

