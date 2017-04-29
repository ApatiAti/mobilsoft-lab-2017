package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;

import com.example.mobsoft.webkorhaz.mock.NetworkMockMemoryRepository;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.network.NetworkConfig;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;

public class ConsultationHourMock {
    public static String CONSULTATIO_HOUR_URL = "consultationHourSearch";

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());


        String responseString;
        int responseCode;

        Headers headers = request.headers();
        NetworkMockMemoryRepository memoryRepository = new NetworkMockMemoryRepository();
        memoryRepository.open(null);



        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + CONSULTATIO_HOUR_URL)
                && request.method().equals("POST")) {

            List<ConsultationHourDto> consultationHourDtoList = new ArrayList<>();

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_YEAR, 1);
            Date beginDate = c.getTime();
            c.add(Calendar.HOUR_OF_DAY, 1);
            Date endDate = c.getTime();

            consultationHourDtoList.add(new ConsultationHourDto(11000L, "2", beginDate, endDate, "IB651", "Dokto Róbert", 10, 5));


            c.add(Calendar.DAY_OF_YEAR, 2);
            beginDate = c.getTime();
            c.add(Calendar.HOUR_OF_DAY, 1);
            endDate = c.getTime();

            consultationHourDtoList.add(new ConsultationHourDto(11001L, "1", beginDate, endDate, "IB651", "Dokto Róbert", 10, 5));

            responseString = GsonHelper.getGson().toJson(consultationHourDtoList);
            responseCode = HttpURLConnection.HTTP_OK;
        } else {
            responseString = "ERROR";
            responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
