package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.model.User;

import java.util.List;


public interface Repository {

    void open(Context context);

    void close();

    List<Appointment> getAppointments(User currentUserId);

    void saveAppointment(Appointment appointment);

    void updateAppointment(List<Appointment> appointments);

    void removeAppointment(Appointment appointment);

    Appointment getAppointmentById(Long appointmentId, long userId);

    void deleteAllAppointement(Long id);

    boolean isInDB(Appointment appointment);

    Department getDepartmentByName(Long departmentId);

    List<Todo> getFavourites();

    void saveFavourite(Todo todo);

    void updateFavourites(List<Todo> todos);

    void removeFavourite(Todo todo);

    boolean isInDB(Todo todo);

    User saveUser(User user);

    Department saveDepartment(Department department);
}
