package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.Todo;

import java.util.List;


public interface Repository {

    void open(Context context);

    void close();

    List<Appointment> getAppointments();

    void saveAppointment(Appointment appointment);

    void updateAppointment(List<Appointment> appointments);

    void removeAppointment(Appointment appointment);

    boolean isInDB(Appointment appointment);


    List<Todo> getFavourites();

    void saveFavourite(Todo todo);

    void updateFavourites(List<Todo> todos);

    void removeFavourite(Todo todo);

    boolean isInDB(Todo todo);
}
