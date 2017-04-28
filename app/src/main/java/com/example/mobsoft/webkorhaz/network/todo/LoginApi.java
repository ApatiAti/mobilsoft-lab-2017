package com.example.mobsoft.webkorhaz.network.todo;

import dagger.producers.Produces;
import retrofit2.Call;
import retrofit2.http.*;

public interface LoginApi {

    /**
     * Login method
     * Through the Login endpoint the user can login in the application.
     * @param url Whole login url
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<Void> loginPost( @Url String url,@Field("username") String username, @Field("password") String password, @Field("mobile-api") boolean mobileApi);

}
