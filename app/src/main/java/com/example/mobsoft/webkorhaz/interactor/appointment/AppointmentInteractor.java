package com.example.mobsoft.webkorhaz.interactor.appointment;


import android.support.annotation.NonNull;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.DeleteAppointmentEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromDbEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromServerEvents;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.ReloadAppoinmentFromServerEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.SaveAppointmentsEvent;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.model.dto.AppointmentDto;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;
import com.example.mobsoft.webkorhaz.network.todo.AppointmentApi;
import com.example.mobsoft.webkorhaz.repository.Repository;

import java.io.EOFException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Response;

public class AppointmentInteractor {

    @Inject
    AppointmentApi appointmentApi;
    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public AppointmentInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void deleteAppointment(Appointment appointment){
        Call<Void> deleteCall = appointmentApi.appointmentDelete(appointment.getAppointmentId());
        DeleteAppointmentEvent event = new DeleteAppointmentEvent();
        try {
            Response<Void> execute = deleteCall.execute();
            int responseCode = execute.code();

            if(HttpURLConnection.HTTP_OK == responseCode) {
                repository.deleteAppointment(appointment);
                event.setSucces(true);
                bus.post(event);
            } else {
                throw  new RuntimeException("Hiba a lekérdezés során. Hibakód HTTP: " + responseCode);
            }
        } catch (Exception e){
            event.setThrowable(e);
            event.setSucces(false);
            bus.post(event);
        }
    }

    /**
     * Appointmentek betöltése db-ből
     * @param user
     */
    public void loadAppointmentsFromDb(User user) {
        LoadAppointmentListFromDbEvent event = new LoadAppointmentListFromDbEvent();
        try {
            List<Appointment> appointments = repository.getAppointments(user);
            event.setAppointments(appointments);
            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }



    /**
     * Appointmentek frissítése szerverről
     */
    public void reloadAppoinmentListFromServer(User user) {
        Call<List<AppointmentDto>> listCall = appointmentApi.appointmentListGet(user.getId());
        LoadAppointmentListFromServerEvents event = new LoadAppointmentListFromServerEvents();
        try {

            Response<List<AppointmentDto>> execute = listCall.execute();
            int responseCode = execute.code();

            if (HttpURLConnection.HTTP_OK == responseCode) {
                List<AppointmentDto> body = execute.body();


                List<Appointment> appointmentList = loadAppoinmentDtoList(body, user);

                event.setAppointments(appointmentList);
                bus.post(event);
            } else {
                throw new RuntimeException("Hiba a lekérdezés során. Hibakód HTTP: " + responseCode);
            }
        } catch (EOFException e){
            RuntimeException tmp = new RuntimeException("Nincs foglalt időpontja!");
            tmp.initCause(e);
            event.setThrowable(tmp);
            bus.post(event);
        }catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    private List<Appointment> loadAppoinmentDtoList(List<AppointmentDto> appointmentDtoList, User user) {
        List<Appointment> newAppointmentList = new ArrayList<>();

        repository.deleteAllAppointement(user);

        for (AppointmentDto appointmentDto : appointmentDtoList) {
            Appointment appointment = mapAndSaveAppointment(appointmentDto);
            newAppointmentList.add(appointment);
        }

        return newAppointmentList;
    }

    @NonNull
    private Appointment mapAndSaveAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = mapDtoToAppointment(appointmentDto);

        appointment.setDepartment(
                mapDepartmentFromDto(appointmentDto.getDepartmentId()));
        appointment.setConsultationHourType(
                mapConsultationHourTypeFromDto(appointmentDto.getConsultationHourTypeId()));
        appointment.setPatient(
                mapPatientFromDto(appointmentDto.getPatientName()));

        repository.saveAppointment(appointment);
        return appointment;
    }

    private User mapPatientFromDto(String patientName) {
        User dbUser = repository.getUserByPatientName(patientName);
        if (dbUser == null){
            throw new RuntimeException("Hiba a lekérdezés során! Felhasználó");
        }
        return dbUser;
    }

    private ConsultationHourType mapConsultationHourTypeFromDto(Long consultationHouTypeId) {
        ConsultationHourType chType = repository.getConsultationHourType(consultationHouTypeId);
        if (chType == null){
            throw new RuntimeException("Nincs megfelelő időpont típus leírás a készüléken. Kérjük frissítsen");
        }
        return chType;
    }

    private Department mapDepartmentFromDto(Long departmentId) {
        Department departmentByDepartmentId = repository.getDepartmentByDepartmentId(departmentId);
        if (departmentByDepartmentId == null){
            throw new RuntimeException("Nincs megfelelő korházi osztály leírás a készüléken. Kérjük frissítsen");
        }
        return  departmentByDepartmentId;
    }

    private Appointment mapDtoToAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentDto.getAppointmentId());
        appointment.setBeginDate(appointmentDto.getBeginDate());
        appointment.setEndDate(appointmentDto.getEndDate());
        appointment.setRoom(appointmentDto.getRoom());
        appointment.setDoctorsName(appointmentDto.getDoctorsName());
        appointment.setComplaints(appointmentDto.getComplaints());
        appointment.setConsultationHourId(appointmentDto.getConsultationHourId());

        return appointment;
    }


    /**
     * Felületen megadott Appointment elküldése a szerverhez és lokális mentése
     * @param appointment
     */
    public void saveOrUpdateAppointent(Appointment appointment) {
        if (appointment.getId() != null){
            updateAppointment(appointment);
        } else {
            saveAppointment(appointment);
        }
    }

    private void saveAppointment(Appointment appointment) {
        Call<AppointmentDto> saveAppointment = appointmentApi.appointmentPost(new AppointmentDto(appointment));
        SaveAppointmentsEvent event = new SaveAppointmentsEvent();
        try {
            Response<AppointmentDto> execute = saveAppointment.execute();
            int responseCode = execute.code();

            if (HttpURLConnection.HTTP_OK == responseCode){
                AppointmentDto responseAppointmentDto = execute.body();
                Appointment savedAppointment = mapAndSaveAppointment(responseAppointmentDto);

                event.setAppointment(savedAppointment );
                bus.post(event);
            } else {
                throw new RuntimeException("Hálózati hiba történt a mentés során!");
            }

        } catch (Exception e){
            event.setThrowable(e);
            event.setAppointment(appointment);
            bus.post(event);
        }
    }

    private void updateAppointment(Appointment appointment) {
        AppointmentDto request = new AppointmentDto(appointment);
        Call<Void> updateAppointment = appointmentApi.appointmentPut(request);
        SaveAppointmentsEvent event = new SaveAppointmentsEvent();
        try {
            Response<Void> execute = updateAppointment.execute();
            int responseCode = execute.code();

            if (HttpURLConnection.HTTP_OK == responseCode){
                repository.saveAppointment(appointment);
                event.setAppointment(appointment);
                bus.post(event);
            } else {
                throw new RuntimeException("Hálózati hiba történt a mentés során!");
            }

        } catch (Exception e){
            event.setThrowable(e);
            event.setAppointment(appointment);
            bus.post(event);
        }
    }

    /**
     * Egy frissítése esetén ha nincs department vagy chType akkor hibát kell dobni, és frissítetni kell
     * @param appointment
     */
    @Deprecated
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
}
