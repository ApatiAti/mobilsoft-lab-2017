package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.network.NetworkConfig;
import com.example.mobsoft.webkorhaz.repository.MemoryRepository;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;

import java.util.List;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;

public class ConsultationHourMock {
    public static String CONSULTATIO_HOUR_URL = "consultationHourSearch";

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());


        String responseString;
        int responseCode;

        Headers headers = request.headers();
        MemoryRepository memoryRepository = new MemoryRepository();
        memoryRepository.open(null);



        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + CONSULTATIO_HOUR_URL
                && request.method().equals("POST"))) {

//            responseString = GsonHelper.getGson().toJson(departments);
            responseCode = 200;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
}
