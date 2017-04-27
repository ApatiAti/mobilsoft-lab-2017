package com.example.mobsoft.webkorhaz.network.interceptors;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Apati on 2017.04.27..
 */

public class JSessionInterceptor implements Interceptor{

    public static final String HEADER_COOKIES = "Cookies";
    public static final String HEADET_SET_COOKIES = "Set-Cookie";
    public static final String JSESSIONID_REGEXP = "(JSESSIONID=)([^;]*)[;|\\s]";
    public static Pattern pattern;
    private String jsessionId = null;

    public JSessionInterceptor() {
        this.pattern= Pattern.compile(JSESSIONID_REGEXP);

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request = includeJSessionId(request);

        Response response = chain.proceed(request);

        extractJSessoinId(response);

        return response;
    }

    private Request includeJSessionId(Request request) {
        String cookies = request.header(HEADER_COOKIES);

        if (cookies != null) {
            Matcher matcher = pattern.matcher(cookies);

            if (!hasJSessionIdCookie(cookies, matcher) && jsessionId != null) {
                request = request.newBuilder().addHeader(HEADER_COOKIES, JSESSIONID_REGEXP + jsessionId + "; " + cookies).build();
            }
        }
        return request;
    }


    private void extractJSessoinId(Response response) {
        String cookies = response.priorResponse().header(HEADET_SET_COOKIES);

        if (cookies != null) {
            Matcher matcher = pattern.matcher(cookies);
            if (hasJSessionIdCookie(cookies, matcher)) {
                jsessionId = matcher.group(2);
            }
        }
    }


    private boolean hasJSessionIdCookie(String cookies, Matcher matcher) {
        if (cookies != null ) {
            return !cookies.trim().isEmpty() && matcher.find();
        }
        return false;
    }

}
