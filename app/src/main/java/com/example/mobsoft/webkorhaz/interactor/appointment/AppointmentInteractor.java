package com.example.mobsoft.webkorhaz.interactor.appointment;


import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromDbEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.LoadAppointmentListFromServerEvents;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.ReloadAppoinmentFromServerEvent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.SaveAppointmentsEvents;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.model.dto.AppointmentDto;
import com.example.mobsoft.webkorhaz.network.HttpNetwork;
import com.example.mobsoft.webkorhaz.network.todo.AppointmentApi;
import com.example.mobsoft.webkorhaz.repository.Repository;

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

            if(HttpURLConnection.HTTP_OK == responseCode) {
                List<AppointmentDto> body = execute.body();


                List<Appointment> appointmentList = loadAppoinmentDtoList(body, user.getUsername());

                event.setAppointments(appointmentList);
                bus.post(event);
            } else {
                throw  new RuntimeException("Hiba a lekérdezés során. Hibakód HTTP: " + responseCode);
            }
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    private List<Appointment> loadAppoinmentDtoList(List<AppointmentDto> appointmentDtoList, String username) {
        List<Appointment> newAppointmentList = new ArrayList<>();

        repository.deleteAllAppointement(username);

        for (AppointmentDto appointmentDto : appointmentDtoList) {
            Appointment appointment = mapDtoToAppointment(appointmentDto);

            appointment.setDepartment(
                    mapDepartmentFromDto(appointmentDto.getDepartmentId()));
            appointment.setConsultationHourType(
                    mapConsultationHourTypeFromDto(appointmentDto.getConsultationHouTypeId()));
            appointment.setPatient(
                    mapPatientFromDto(appointmentDto.getPatientId()));

            repository.saveAppointment(appointment);
            newAppointmentList.add(appointment);
        }

        return newAppointmentList;
    }

    private User mapPatientFromDto(Long patientId) {
        User dbUser = repository.getUserByPatientId(patientId);
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
        appointment.setComplaint(appointmentDto.getComplaint());
        appointment.setConsultationHourId(appointmentDto.getConsultationHourId());

        return appointment;
    }


    /**
     * Felületen megadott Appointment elküldése a szerverhez és lokális mentése
     * @param appointment
     */
    public void saveAppointent(Appointment appointment) {
        SaveAppointmentsEvents event = new SaveAppointmentsEvents();
        try {
            boolean succes = HttpNetwork.saveAppointment(appointment);
            if (succes){
                repository.saveAppointment(appointment);
                event.setAppointment(appointment);
            } else {
                event.setAppointment(null);
            }

            bus.post(event);
        } catch (Exception e){
            event.setThrowable(e);
            event.setAppointment(null);
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
