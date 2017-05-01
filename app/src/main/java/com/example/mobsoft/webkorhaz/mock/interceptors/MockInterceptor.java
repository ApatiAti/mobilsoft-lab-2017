package com.example.mobsoft.webkorhaz.mock.interceptors;

import android.net.Uri;
import android.util.Log;

import com.example.mobsoft.webkorhaz.network.NetworkConfig;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mobsoft.webkorhaz.mock.interceptors.MockHelper.makeResponse;


public class MockInterceptor implements Interceptor {

	/**
	 * Elkapott Http kérések ide érkeznek be és itt tudjuk eldönteni hogy mockoljuk ki őket.
	 * @param chain
	 * @return
	 * @throws IOException
	 */
	@Override
	public Response intercept(Chain chain) throws IOException {
		return process(chain.request());
	}

	public Response process(Request request) {

		Uri uri = Uri.parse(request.url().toString());

		Log.d("Test Http Client", "URL call: " + uri.toString());
		Headers headers = request.headers();


		String path = uri.getPath();
		if (path.startsWith(NetworkConfig.ENDPOINT_PREFIX + AppointmentMock.APPOINTMENT_URL)) {
			return AppointmentMock.process(request);
		} else if (path.startsWith(NetworkConfig.ENDPOINT_PREFIX + DepartmentMock.DEPARTMENT_URL)){
			return DepartmentMock.process(request);
		} else if (path.startsWith(NetworkConfig.ENDPOINT_PREFIX + ConsultationHourMock.CONSULTATIO_HOUR_URL)) {
			return ConsultationHourMock.process(request);
		} else if (path.startsWith(LoginMock.LOGIN_URL) || path.startsWith(LoginMock.LOGOUT_URL)){
			return LoginMock.process(request);
		}

		return makeResponse(request, headers, HttpURLConnection.HTTP_NOT_FOUND, "Unknown");

	}

}