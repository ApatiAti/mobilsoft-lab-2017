package com.example.mobsoft.webkorhaz.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;
import com.example.mobsoft.webkorhaz.ui.main.MainScreen;

import javax.inject.Inject;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class LoginActivity  extends AppCompatActivity implements LoginScreen {

    Button btnLogin;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUsername = (EditText) findViewById(R.id.loginEditUsername);
                EditText editPassword= (EditText) findViewById(R.id.loginEditUsername);

                User user = new User(editTextUsername.getText().toString(), editPassword.getText().toString());
//                loginSucces(user);
                loginPresenter.startLogin(user);
            }
        });

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
    public void loginSucces(User currentUser) {
        ((MobSoftApplication) getApplication()).setCurrentUser(currentUser);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
