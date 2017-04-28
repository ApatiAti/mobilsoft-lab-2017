package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;


import com.example.mobsoft.webkorhaz.network.NetworkConfig;
import com.example.mobsoft.webkorhaz.repository.MemoryRepository;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;

import java.net.HttpURLConnection;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;

public class TodoMock {
	public static Response process(Request request) {
		Uri uri = Uri.parse(request.url().toString());

		String responseString;
		int responseCode;
		Headers headers = request.headers();


		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "Todos") && request.method().equals("POST")) {
			responseString = "";
			responseCode = HttpURLConnection.HTTP_OK;

		} else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "Todos") && request.method().equals("Get")) {
			MemoryRepository memoryRepository = new MemoryRepository();
			memoryRepository.open(null);
			responseString = GsonHelper.getGson().toJson(memoryRepository.getFavourites());
			responseCode = HttpURLConnection.HTTP_OK;

		} else {
			responseString = "ERROR";
			responseCode = HttpURLConnection.HTTP_UNAVAILABLE;
		}

		return makeResponse(request, headers, responseCode, responseString);
	}
}