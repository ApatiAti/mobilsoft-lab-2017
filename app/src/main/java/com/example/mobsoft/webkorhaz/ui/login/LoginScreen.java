package com.example.mobsoft.webkorhaz.ui.login;

import com.example.mobsoft.webkorhaz.model.User;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public interface LoginScreen {
    void loginError();

    void loginSucces(User currentUser);
}
