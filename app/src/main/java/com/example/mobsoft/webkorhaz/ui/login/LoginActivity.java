package com.example.mobsoft.webkorhaz.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.ui.main.MainScreen;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class LoginActivity  extends AppCompatActivity implements LoginScreen {

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }

    @Override
    public void loginError() {
        Toast.makeText(this, "Hiba a bejelentkezés közben", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSucces() {
        Toast.makeText(this, "Sikeres login", Toast.LENGTH_SHORT).show();
    }
}
