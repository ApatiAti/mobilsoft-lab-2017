package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.network.NetworkConfig;
import com.example.mobsoft.webkorhaz.repository.MemoryRepository;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;

import java.util.List;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;


import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

public class AppointmentMock {
    public static String APPOINTMENT_URL = "appointment";
    public static String APPOINTMENT_LIST_URL = "appointment/list";

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        Appointment appointment = null;
        String responseString;
        int responseCode;

        Headers headers = request.headers();
        MemoryRepository memoryRepository = new MemoryRepository();
        memoryRepository.open(null);



        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + APPOINTMENT_URL)) {
            switch (request.method()){
                case "POST":
                    responseString = "Succes";
                    responseCode = 200;
                    break;
                case "GET":
                    Long appointmentId = null;
                    Appointment appointmentByAppointmentId = memoryRepository.getAppointmentByAppointmentId(appointmentId, 1L);
                    responseString = GsonHelper.getGson().toJson(appointmentByAppointmentId);
                    responseCode = 200;
                    break;
                case "PUT":
//                    appointment = null;
                    memoryRepository.updateAppointment(appointment);
                    responseString = "Succes";
                    responseCode = 200;
                    break;
                case "DELETE":
//                    appointment =
                    memoryRepository.deleteAppointment(appointment);
                    responseString = "Succes";
                    responseCode = 200;
                    break;
                default:
                    responseString = "ERROR";
                    responseCode = 403;
                    break;
            }
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + APPOINTMENT_LIST_URL)
                    && request.method().equals("POST")) {
            User currentUser = null;
            List<Appointment> appointments = memoryRepository.getAppointments(currentUser);
            responseString = GsonHelper.getGson().toJson(appointments);
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 403;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}