package com.example.mobsoft.webkorhaz.repository;

import android.content.Context;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.model.User;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

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
    public List<Appointment> getAppointments(User currentUserId) {
        return Select.from(Appointment.class)
                .where(Condition.prop("patient").eq(currentUserId.getId())).orderBy("begin_Date").list();
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        SugarRecord.save(appointment);
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
    public Appointment getAppointmentById(Long appointmentId, long userId) {
        return Select.from(Appointment.class)
                .where(Condition.prop("appointmentId").eq(appointmentId))
                    .and(Condition.prop("parient.id").eq(userId)).first();
    }

    @Override
    public void deleteAllAppointement(Long userId) {
        SugarRecord.deleteAll(Appointment.class, "patient= ?", userId.toString());
    }

    @Override
    public boolean isInDB(Appointment appointment) {
        return SugarRecord.findById(Appointment.class, appointment.getId()) != null;
    }

    @Override
    public Department getDepartmentByName(Long departmentId) {
        return Select.from(Department.class)
                //.where(Condition.prop("departmentId").eq(departmentId))
                .first();
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

    @Override
    public User saveUser(User user) {
        User dbUser = Select.from(User.class).where(Condition.prop("username").eq(user.getUsername())).first();
        if (dbUser == null){
            long id = SugarRecord.save(user);
            user.setId(id);
            return user;
        }
        return dbUser;
    }

    @Override
    public Department saveDepartment(Department department) {
        long saveId = SugarRecord.save(department);
        department.setId(saveId);
        return department;
    }
}
