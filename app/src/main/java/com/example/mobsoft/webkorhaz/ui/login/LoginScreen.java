package com.example.mobsoft.webkorhaz.ui.login;

import com.example.mobsoft.webkorhaz.model.User;



public interface LoginScreen {
    void loginError();

    void loginSucces(User currentUser);
}
