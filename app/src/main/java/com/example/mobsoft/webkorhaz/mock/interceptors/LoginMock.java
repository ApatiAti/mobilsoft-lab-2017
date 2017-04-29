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

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;

/**
 * Created by Apati on 2017.04.29..
 */

public class LoginMock {
    public static final String LOGIN_URL = "/login";

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        Appointment appointment = null;
        String responseString;
        int responseCode;

        Headers headers = request.headers();
        NetworkMockMemoryRepository memoryRepository = new NetworkMockMemoryRepository();
        memoryRepository.open(null);



        if (uri.getPath().equals(LOGIN_URL)
                && request.method().equals("POST")) {
            responseString = "Succes";
            responseCode = HttpURLConnection.HTTP_OK;
        }  else {
            responseString = "ERROR";
            responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
        }


        return makeResponse(request, headers, responseCode, responseString);
    }
}
