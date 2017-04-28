package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;

import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.network.NetworkConfig;
import com.example.mobsoft.webkorhaz.repository.MemoryRepository;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;

import java.util.List;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;

package com.example.mobsoft.webkorhaz.mock.interceptors;


import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

public class DepartmentMock {
    public static String DEPARTMENT_URL = "getDepartmentsAndTypes";

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());


        String responseString;
        int responseCode;

        Headers headers = request.headers();
        MemoryRepository memoryRepository = new MemoryRepository();
        memoryRepository.open(null);



        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + DEPARTMENT_URL
                && request.method().equals("GET"))) {
            List<Department> departments = memoryRepository.getDepartments();
            responseString = GsonHelper.getGson().toJson(departments);
            responseCode = 200;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}