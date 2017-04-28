package com.example.mobsoft.webkorhaz.mock;

import com.example.mobsoft.webkorhaz.network.NetworkModule;
import com.example.mobsoft.webkorhaz.network.todo.LoginApi;
import com.example.mobsoft.webkorhaz.network.todo.TodoApi;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {
	private NetworkModule networkModule = new NetworkModule();

	@Provides
	@Singleton
	public OkHttpClient.Builder provideOkHttpClientBuilder() {
		return networkModule.provideOkHttpClientBuilder();
	}


	/**
	 *  OKHttpClient-hez interceptort ad mely interceptor elkapja a kérést és segítségével feltudjuk dolgozni
	 * @param builder
	 * @return
	 */
	@Provides
	@Singleton
	public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {

		builder.interceptors().add(3, new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				// TODO ezt kiszedni és helyére már a MockInterceport rakni
				Request request = chain.request();
				return MockHttpServer.call(request);
			}
		});

		return builder.build();
	}

	@Provides
	@Singleton
	public Retrofit provideRetrofit(OkHttpClient client) {
		return networkModule.provideRetrofit(client);
	}

	@Provides
	@Singleton
	public TodoApi provideTodoApi(Retrofit retrofit) {
		return networkModule.provideTodoApi(retrofit);
	}

	@Provides
	@Singleton
	public LoginApi provideLoginApi(Retrofit retrofit) { return networkModule.provideLoginApi(retrofit);}


}