package com.example.mobsoft.webkorhaz.interactor.appointment;


import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromDbEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromServerEvents;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.ReloadAppoinmentFromServerEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.SaveAppointmentsEvents;
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
        LoadAppointmentListFromDbEvent event = new LoadAppointmentListFromDbEvent();
        try {
            List<Appointment> appointments = repository.getAppointments();
            event.setAppointments(appointments);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void reloadAppoinmentListFromServer() {
        LoadAppointmentListFromServerEvents event = new LoadAppointmentListFromServerEvents();
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

    public void saveAppointent(Appointment appointment) {
        SaveAppointmentsEvents event = new SaveAppointmentsEvents();
        try {
//            boolean succes = network.saveAppointment(appointemnt);
            boolean succes = true;
            if (succes){
                repository.saveAppointment(appointment);
                event.setSucces(true);
            } else {
                event.setSucces(false);
            }

            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            event.setSucces(false);
            bus.post(event);
        }
    }

    public void reloadAppoinmentFromServer(Appointment appointment) {
        ReloadAppoinmentFromServerEvent event = new ReloadAppoinmentFromServerEvent();
        try {
//            Appointment reloadedAppointent= network.reloadAppointment(appointemnt);
            Appointment reloadedAppointent = null;
            event.setAppointment(reloadedAppointent);
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
