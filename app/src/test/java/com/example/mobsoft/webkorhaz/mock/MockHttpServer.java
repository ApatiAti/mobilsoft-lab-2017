package com.example.mobsoft.webkorhaz.mock;


import com.example.mobsoft.webkorhaz.mock.interceptors.MockInterceptor;

import okhttp3.Request;
import okhttp3.Response;

public class MockHttpServer {

	/**
	 * Az OkHttpClient-hez hozzáadott interceptor és az általunk létrehozott interceptor közötti bridge
	 * @param request
	 * @return
	 */
	public static Response call(Request request) {
		MockInterceptor mockInterceptor = new MockInterceptor();
		return mockInterceptor.process(request);
	}
}