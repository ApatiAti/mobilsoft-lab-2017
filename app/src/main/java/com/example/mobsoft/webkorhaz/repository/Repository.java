package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.model.User;

import java.util.List;


public interface Repository {

    void open(Context context);

    void close();

    List<Appointment> getAppointments(User user);

    void saveAppointment(Appointment appointment);

    void updateAppointment(Appointment appointment);

    void removeAppointment(Appointment appointment);

    Appointment getAppointmentByAppointmentId(Long appointmentId, long userId);

    void deleteAllAppointement(User username);

    void deleteAppointment(Appointment appointment);

    boolean isInDB(Appointment appointment);

    Department getDepartmentByDepartmentId(Long departmentId);

    Department getDepartmentByDepartmentName(String departmentName);

    List<Department> getDepartments();

    List<Todo> getFavourites();

    void saveFavourite(Todo todo);

    void updateFavourites(List<Todo> todos);

    void removeFavourite(Todo todo);

    boolean isInDB(Todo todo);

    User saveUser(User user);

    Department saveDepartment(Department department);

    ConsultationHourType saveConsultationHourType(ConsultationHourType dbConsultationHourType);

    ConsultationHourType getConsultationHourType(Long consultationHouTypeId);

    User getUserByPatientName(String patientName);
}
