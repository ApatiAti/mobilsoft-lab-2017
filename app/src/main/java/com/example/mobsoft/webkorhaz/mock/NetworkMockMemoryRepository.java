package com.example.mobsoft.webkorhaz.mock;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Apati on 2017.04.28..
 */

public class NetworkMockMemoryRepository {

    private static final long MINUTE = 60 * 1000;

    public static List<Appointment> appointmentList;
    public static List<Department> departmentList;
    public static List<User> userList;
    public static List<ConsultationHourType> consultiConsultationHourTypeList;


    public void open(Context context) {
        createUser();
        createConsultationHourTypes();
        createDepartments();
        createAppointment();

        openTodoRepo(context);
    }

    public void createAppointment() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, +2);

        Date beginDate = c.getTime();
        c.add(Calendar.HOUR_OF_DAY, +2);
        Date endDate = c.getTime();

        Appointment flight1 = new Appointment(0L, beginDate, endDate, "Ib025", "Dr Doktor"
                , departmentList.get(0), "Fáj fejem"
                , userList.get(0), 12L
                , consultiConsultationHourTypeList.get(0));
        flight1.setId(11L);

        c.add(Calendar.DAY_OF_YEAR, +1);
        beginDate = c.getTime();
        c.add(Calendar.HOUR_OF_DAY, +1);
        endDate = c.getTime();

        Appointment flight2 = new Appointment(1L, beginDate, endDate, "EB01", "Dr Doktor"
                , departmentList.get(1), "Szem baj"
                , userList.get(1), 17L
                , consultiConsultationHourTypeList.get(1));
        flight2.setId(12L);

        appointmentList = new ArrayList<>();
        appointmentList.add(flight1);
        appointmentList.add(flight2);
    }

    public void createUser() {
        userList = new ArrayList<>();
        userList.add(getUseOne());
        userList.add(new User(2L, "Károly", ""));
    }

    @NonNull
    public static User getUseOne() {
        return new User(1L, "beteg1", "");
    }

    public void createDepartments() {
        departmentList  = new ArrayList<>();

        ConsultationHourType chtype0 = consultiConsultationHourTypeList.get(0);
        ConsultationHourType chtype1 = consultiConsultationHourTypeList.get(1);
        ConsultationHourType chtype2 = consultiConsultationHourTypeList.get(2);
        ConsultationHourType chtype3 = consultiConsultationHourTypeList.get(3);

        Department department1 = new Department(100L, "Ortopédia", null);
        List<ConsultationHourType> chtypeList = new ArrayList<>();
        chtypeList.add(chtype0);
        chtypeList.add(chtype1);
        chtype0.setDepartment(department1);
        chtype1.setDepartment(department1);

        department1.setConsultationHourTypeList(chtypeList);
        departmentList.add(department1);

        Department department2 = new Department(101L, "Szemészet", null);
        List<ConsultationHourType> chtypeList2 = new ArrayList<>();
        chtypeList2.add(chtype2);
        chtypeList2.add(chtype3);
        chtype2.setDepartment(department1);
        chtype3.setDepartment(department1);

        department2.setConsultationHourTypeList(chtypeList2);
        departmentList.add(department2);
    }

    public void createConsultationHourTypes() {
        consultiConsultationHourTypeList = new ArrayList<>();

        ConsultationHourType chtype1 = new ConsultationHourType(1000L, 1L, "Gerinc vizsgálat");
        ConsultationHourType chtype2 = new ConsultationHourType(1001L, 2L, "láb cucc");
        ConsultationHourType chtype3 = new ConsultationHourType(1002L, 3L, "Szem cucc");
        ConsultationHourType chtype4 = new ConsultationHourType(1003L, 4L, "szürke hályog");

        consultiConsultationHourTypeList.add(chtype1);
        consultiConsultationHourTypeList.add(chtype2);
        consultiConsultationHourTypeList.add(chtype3);
        consultiConsultationHourTypeList.add(chtype4);
    }

    public List<Appointment> getAppointments(User currentUser) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatient().getUsername().equals(currentUser.getUsername())){
                result.add(appointment);
            }
        }

        return result;
    }


    public void saveAppointment(Appointment appointment) {
        appointmentList.add(appointment);
    }


    public void updateAppointment(Appointment appointment) {
        return;
    }


    public void removeAppointment(Appointment appointment) {
        appointmentList.remove(appointment);
    }


    public Appointment getAppointmentByAppointmentId(Long appointmentId, long userId) {
        for (Appointment appointment: appointmentList) {
            if (appointment.getAppointmentId().equals(appointmentId)){
                return appointment;
            }
        }
        return null;
    }



    public void deleteAppointment(Appointment appointment) {
        Appointment dbAppointment = getAppointmentByAppointmentId(appointment.getAppointmentId() , 1L);
        appointmentList.remove(dbAppointment);
    }


    public void deleteAllAppointement(Long id) {
        appointmentList.clear();
    }


    public boolean isInDB(Appointment appointment) {
        return appointmentList.contains(appointment);
    }


    public Department getDepartmentByDepartmentId(Long departmentId) {
        for (Department department : departmentList){
            if (department.getDepartmentId().equals(departmentId)){
                return department;
            }
        }

        return null;
    }


    public Department getDepartmentByDepartmentName(String departmentName) {
        for (Department department : departmentList){
            if (department.getDepartmentName().equals(department)){
                return department;
            }
        }

        return null;
    }


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


    public List<Todo> getFavourites() {
        return todos;
    }


    public void saveFavourite(Todo todo) {
        todos.add(todo);

    }


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


    public void removeFavourite(Todo flight) {
        todos.remove(flight);
    }


    public boolean isInDB(Todo flight) {
        return todos.contains(flight);
    }


    public User saveUser(User user) {
        return null;
    }


    public Department saveDepartment(Department department) {
        for (int i = 0 ; i <= departmentList.size() ; i++) {
            if (departmentList.get(i).getDepartmentId().equals(department.getDepartmentId())) {
                departmentList.set(i, department);
                return department;
            }
        }
        departmentList.add(department);
        return department;
    }


    public ConsultationHourType saveConsultationHourType(ConsultationHourType dbConsultationHourType) {
        return null;
    }

    public User getUserByUserId(Long userId){
        for (User user : userList){
            if (user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }
}
