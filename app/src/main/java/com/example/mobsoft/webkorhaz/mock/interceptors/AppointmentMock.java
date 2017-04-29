package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;

import com.example.mobsoft.webkorhaz.mock.NetworkMockMemoryRepository;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.model.dto.AppointmentDto;
import com.example.mobsoft.webkorhaz.network.NetworkConfig;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;

import java.net.HttpURLConnection;
import java.util.ArrayList;
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
        NetworkMockMemoryRepository memoryRepository = new NetworkMockMemoryRepository();
        memoryRepository.open(null);



        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + APPOINTMENT_LIST_URL)
                && request.method().equals("GET")) {
            Long userId = Long.valueOf(request.url().queryParameter("userId"));
            userId = userId == null ? 1L : userId;
            User currentUser = memoryRepository.getUserByUserId(userId);

            List<AppointmentDto> dtoList = new ArrayList<>();
            for (Appointment dbAppointment : memoryRepository.getAppointments(currentUser)){
                AppointmentDto dto = new AppointmentDto(dbAppointment);
                dtoList.add(dto);
            }

            responseString = GsonHelper.getGson().toJson(dtoList);
            responseCode = HttpURLConnection.HTTP_OK;

        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + APPOINTMENT_URL)) {
            switch (request.method()){
                case "POST":
                    responseString = "Succes";
                    responseCode = HttpURLConnection.HTTP_OK;
                    break;
                case "GET":
                    Long appointmentId = Long.valueOf(request.url().queryParameter("userId"));
                    Appointment appointmentByAppointmentId = memoryRepository.getAppointmentByAppointmentId(appointmentId, 1L);
                    responseString = GsonHelper.getGson().toJson(appointmentByAppointmentId);
                    responseCode = HttpURLConnection.HTTP_OK;
                    break;
                case "PUT":
                    responseString = "Succes";
                    responseCode = HttpURLConnection.HTTP_OK;
                    break;
                case "DELETE":
                    responseString = "Succes";
                    responseCode = HttpURLConnection.HTTP_OK;
                    break;
                default:
                    responseString = "ERROR";
                    responseCode = HttpURLConnection.HTTP_OK;
                    break;
            }
        } else {
            responseString = "ERROR";
            responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}