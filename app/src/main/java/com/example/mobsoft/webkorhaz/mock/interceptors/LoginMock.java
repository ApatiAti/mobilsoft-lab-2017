package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;

import com.example.mobsoft.webkorhaz.mock.NetworkMockMemoryRepository;
import com.example.mobsoft.webkorhaz.model.Appointment;

import java.net.HttpURLConnection;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;

/**
 * Created by Apati on 2017.04.29..
 */

public class LoginMock {
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";

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

        } else if (uri.getPath().equals(LOGOUT_URL )
                && request.method().equals("GET")) {
            responseString = "Succes";
            responseCode = HttpURLConnection.HTTP_OK;

        } else{
            responseString = "ERROR";
            responseCode = HttpURLConnection.HTTP_BAD_REQUEST;

        }


        return makeResponse(request, headers, responseCode, responseString);
    }
}
