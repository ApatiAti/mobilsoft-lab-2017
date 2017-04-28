package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MemoryRepository implements Repository {

	private static final long MINUTE = 60 * 1000;

	public static List<Appointment> appointmentList;
	public static List<Department> departmentList;
	public static List<User> userList;
	public static List<ConsultationHourType> consultiConsultationHourTypeList;

	@Override
	public void open(Context context) {
		createDepartmentsAndCHTypes();
		createUser();
		createConsultationHourTypes();
		createAppointment();

		openTodoRepo(context);
	}

	private void createAppointment() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, +2);

		Date beginDate = c.getTime();
		c.add(Calendar.HOUR_OF_DAY, +2);
		Date endDate = c.getTime();

		Appointment flight1 = new Appointment(beginDate, endDate, "Ib025", "Dr Doktor"
				, departmentList.get(0), "Fáj fejem"
				, userList.get(0), 12L
				, consultiConsultationHourTypeList.get(0));
		flight1.setId(11L);

		c.add(Calendar.DAY_OF_YEAR, +1);
		beginDate = c.getTime();
		c.add(Calendar.HOUR_OF_DAY, +1);
		endDate = c.getTime();

		Appointment flight2 = new Appointment(beginDate, endDate, "EB01", "Dr Doktor"
				, departmentList.get(1), "Szem baj"
				, userList.get(1), 17L
				, consultiConsultationHourTypeList.get(1));
		flight2.setId(12L);

		appointmentList = new ArrayList<>();
		appointmentList.add(flight1);
		appointmentList.add(flight2);
	}

	private void createUser() {
		userList.add(new User(1l, "beteg1", ""));
		userList.add(new User(2l, "Károly", ""));
	}

	private void createDepartmentsAndCHTypes() {

		List<ConsultationHourType> chtypeList = new ArrayList<>();
		chtypeList.add(consultiConsultationHourTypeList.get(0));
		chtypeList.add(consultiConsultationHourTypeList.get(1));

		departmentList.add(new Department(100L, "Ortopédia", chtypeList));

		List<ConsultationHourType> chtypeList2 = new ArrayList<>();
		chtypeList2.add(consultiConsultationHourTypeList.get(0));
		chtypeList2.add(consultiConsultationHourTypeList.get(2));

		departmentList.add(new Department(101L, "Szemészet", chtypeList2));
	}

	private void createConsultationHourTypes() {
		ConsultationHourType chtype1 = new ConsultationHourType(1000L, 1L, "Valami típus");
		ConsultationHourType chtype2 = new ConsultationHourType(1002L, 2L, "láb cucc");
		ConsultationHourType chtype3 = new ConsultationHourType(1002L, 3L, "Szem cucc");

		consultiConsultationHourTypeList.add(chtype1);
		consultiConsultationHourTypeList.add(chtype2);
		consultiConsultationHourTypeList.add(chtype3);
	}

	@Override
	public void close() {

	}

	@Override
	public List<Appointment> getAppointments(User currentUser) {
		List<Appointment> result = new ArrayList<>();
		for (Appointment appointment : appointmentList) {
			if (appointment.getPatient().getUsername().equals(currentUser.getUsername())){
				result.add(appointment);
			}
		}

		return result;
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		appointmentList.add(appointment);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		return;
	}

	@Override
	public void removeAppointment(Appointment appointment) {
		appointmentList.remove(appointment);
	}

	@Override
	public Appointment getAppointmentByAppointmentId(Long appointmentId, long userId) {
		for (Appointment appointment: appointmentList) {
			if (appointment.getAppointmentId().equals(appointmentId)){
				return appointment;
			}
		}
		return null;
	}


	@Override
	public void deleteAppointment(Appointment appointment) {
		Appointment dbAppointment = getAppointmentByAppointmentId(appointment.getAppointmentId() , 1L);
		appointmentList.remove(dbAppointment);
	}

	@Override
	public void deleteAllAppointement(Long id) {
		appointmentList.clear();
	}

	@Override
	public boolean isInDB(Appointment appointment) {
		return appointmentList.contains(appointment);
	}

	@Override
	public Department getDepartmentByDepartmentId(Long departmentId) {
		for (Department department : departmentList){
			if (department.getDepartmentId().equals(departmentId)){
				return department;
			}
		}

		return null;
	}

	@Override
	public Department getDepartmentByDepartmentName(String departmentName) {
		for (Department department : departmentList){
			if (department.getDepartmentName().equals(department)){
				return department;
			}
		}

		return null;
	}

	@Override
	public List<Department> getDepartments() {
		return departmentList;
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

	@Override
	public User saveUser(User user) {
		return null;
	}

	@Override
	public Department saveDepartment(Department department) {
		return null;
	}
}

