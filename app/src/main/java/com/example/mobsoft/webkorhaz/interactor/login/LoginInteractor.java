package com.example.mobsoft.webkorhaz.interactor.login;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.login.events.LoginEvent;
import com.example.mobsoft.webkorhaz.interactor.login.events.LogoutEvent;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.network.NetworkConfig;
import com.example.mobsoft.webkorhaz.network.todo.LoginApi;
import com.example.mobsoft.webkorhaz.repository.Repository;


import java.net.HttpURLConnection;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Apati on 2017.04.04..
 */

public class LoginInteractor {

    @Inject
    LoginApi loginApi;
    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public LoginInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void login(User user){
        Call<Void> activeUser = loginApi.loginPost(NetworkConfig.LOGIN_URL, user.getUsername(), user.getPassword(), true);
        LoginEvent event = new LoginEvent();
        try {
            Response<Void> response = activeUser.execute();
            int responseCode = response.code();

            if (responseCode == HttpURLConnection.HTTP_OK ||
                    responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
                user = repository.saveUser(user);

                event.setUser(user);
                bus.post(event);
            } else {
                throw new Exception("Result code is not 200 or 301");
            }
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void logout(){
        Call<Void> voidCall = loginApi.logoutGet(NetworkConfig.LOGOUT_URL);
        LogoutEvent event = new LogoutEvent();
        try {
            Response<Void> execute = voidCall.execute();
            int code = execute.code();
            if (HttpURLConnection.HTTP_OK == code){
                bus.post(event);
            } else {
                throw new RuntimeException("Hiba történt a kijelentkezés során!");
            }
        } catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
