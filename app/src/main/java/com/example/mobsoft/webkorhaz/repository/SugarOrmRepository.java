package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;


public class SugarOrmRepository implements Repository {

    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Appointment> getAppointments() {
        return SugarRecord.listAll(Appointment.class);
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        SugarRecord.saveInTx(appointment);
    }

    @Override
    public void updateAppointment(List<Appointment> appointments) {
        return;
    }

    @Override
    public void removeAppointment(Appointment appointment) {
        SugarRecord.deleteInTx(appointment);
    }

    @Override
    public boolean isInDB(Appointment appointment) {
        return SugarRecord.findById(Appointment.class, appointment.getId()) != null;
    }

    /**
     *  Labor miatt
     */

    @Override
    public List<Todo> getFavourites() {
        return SugarRecord.listAll(Todo.class);
    }

    @Override
    public void saveFavourite(Todo flight) {
        SugarRecord.saveInTx(flight);
    }

    @Override
    public void updateFavourites(List<Todo> todos) {
        List<Todo> favourites = getFavourites();
        List<Todo> toUpdate = new ArrayList<>(favourites.size());
        for (Todo favourite : favourites) {
            for (Todo todo : todos) {
                if (todo.getId().equals(favourite.getId())) {
                    toUpdate.add(todo);
                }
            }
        }
        SugarRecord.saveInTx(toUpdate);
    }

    @Override
    public void removeFavourite(Todo flight) {
        SugarRecord.deleteInTx(flight);
    }

    @Override
    public boolean isInDB(Todo flight) {
        return SugarRecord.findById(Todo.class, flight.getId()) != null;
    }

}
