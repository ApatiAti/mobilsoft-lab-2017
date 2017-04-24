package com.example.mobsoft.webkorhaz.interactor.appointment;


import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromDbEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromServerEvents;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.ReloadAppoinmentFromServerEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.SaveAppointmentsEvents;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;
import com.example.mobsoft.webkorhaz.repository.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class AppointmentInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public AppointmentInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void loadAppointmentsFromDb() {
        LoadAppointmentListFromDbEvent event = new LoadAppointmentListFromDbEvent();
        try {
//            List<Appointment> appointments = repository.getAppointments();
            List<Appointment> appointments = new ArrayList<>();
            Appointment appointment = new Appointment(
                    1l, new Date(), new Date(), "IB028", "Dr Kovács","Szemészet", "", 10l, 10l, "Valami típus"
            );
            Appointment appointment2 = new Appointment(
                    1l, new Date(), new Date(), "IB028", "Dr Kovács", "Ortopédia", "", 10l, 10l, "Valami típus"
            );

            appointments.add(appointment);
            appointments.add(appointment2);

            event.setAppointments(appointments);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void reloadAppoinmentListFromServer(long userId) {
        LoadAppointmentListFromServerEvents event = new LoadAppointmentListFromServerEvents();
        try {
            List<Appointment> appointments = HttpNetwork.getAppointmentList(0);
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
            Appointment savedAppointment = HttpNetwork.saveAppointment(appointment);

            repository.saveAppointment(savedAppointment);
            event.setAppointment(savedAppointment);

            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void reloadAppoinmentFromServer(Appointment appointment) {
        ReloadAppoinmentFromServerEvent event = new ReloadAppoinmentFromServerEvent();
        try {
            Appointment reloadedAppointent = HttpNetwork.reloadAppointment(appointment);
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
