package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.network.interceptors.JSessionInterceptor;
import com.example.mobsoft.webkorhaz.network.todo.AppointmentApi;
import com.example.mobsoft.webkorhaz.network.todo.DepartmentApi;
import com.example.mobsoft.webkorhaz.network.todo.LoginApi;
import com.example.mobsoft.webkorhaz.network.todo.TodoApi;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder
                .addInterceptor(new JSessionInterceptor())  // Application interceptor
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()))
                .build();
    }

    @Provides
    @Singleton
    public TodoApi provideTodoApi(Retrofit retrofit) {
        return retrofit.create(TodoApi.class);
    }

    @Provides
    @Singleton
    public LoginApi provideLoginApi(Retrofit retrofit) { return retrofit.create(LoginApi.class);}

    @Provides
    @Singleton
    public DepartmentApi provideDepartmentApi(Retrofit retrofit) { return retrofit.create(DepartmentApi.class);}

    @Provides
    @Singleton
    public AppointmentApi provideAppointmentApi(Retrofit retrofit) { return retrofit.create(AppointmentApi.class);}

}