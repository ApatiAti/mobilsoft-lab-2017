package com.example.mobsoft.webkorhaz.interactor.appointment;


import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentsFromDbEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentsFromServerEvents;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class AppointmentInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public AppointmentInteractor() {
    }

    public void loadAppointmentsFromDb() {
        LoadAppointmentsFromDbEvent event = new LoadAppointmentsFromDbEvent();
        try {
            List<Appointment> appointments = repository.getAppointments();
            event.setAppointments(appointments);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void reloadAppoinmentsFromServer() {
        LoadAppointmentsFromServerEvents event = new LoadAppointmentsFromServerEvents();
        try {
//            List<Appointment> appointments = network.getAppointments();
            List<Appointment> appointments = new ArrayList<>();
            event.setAppointments(appointments);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }


/*
    public void getFavourites() {
        GetFavouritesEvent event = new GetFavouritesEvent();
        try {
            List<Todo> todos = repository.getFavourites();
            event.setTodos(todos);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveFavourites(Todo todos) {

        SaveFavouriteEvent event = new SaveFavouriteEvent();
        event.setTodo(todos);
        try {
            repository.saveFavourite(todos);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void updateFavourites(List<Todo> todo) {
        try {
            repository.updateFavourites(todo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFavourites(Todo todos) {
        RemoveFavouriteEvent event = new RemoveFavouriteEvent();
        event.setTodos(todos);
        try {
            repository.removeFavourite(todos);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    */
}
