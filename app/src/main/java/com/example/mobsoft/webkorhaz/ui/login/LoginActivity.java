package com.example.mobsoft.webkorhaz.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;



public class LoginActivity  extends AppCompatActivity implements LoginScreen {
    private static final String TAG = "LoginActivity";
    Tracker mTracker;

    Button btnLogin;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MobSoftApplication application = (MobSoftApplication) getApplication();
        mTracker = application.getDefaultTracker();

        EditText editTextUsername = (EditText) findViewById(R.id.loginEditUsername);
        EditText editPassword= (EditText) findViewById(R.id.loginEditPassword);

        editTextUsername.setText("beteg1");
        editPassword.setText("admin");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUsername = (EditText) findViewById(R.id.loginEditUsername);
                EditText editPassword= (EditText) findViewById(R.id.loginEditPassword);

                User user = new User(editTextUsername.getText().toString(), editPassword.getText().toString());

                loginPresenter.startLogin(user);
            }
        });

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();


        Log.i(TAG, "OnStart activity name: " + TAG);
        mTracker.setScreenName("Activity~" + TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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
