package com.example.mobsoft.webkorhaz;

import com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch.ConsultationHourSearchActivity;
import com.example.mobsoft.webkorhaz.ui.UIModule;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.consultationHourList.ConsultationHourListActivity;
import com.example.mobsoft.webkorhaz.ui.login.LoginActivity;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

@Singleton
@Component(modules = {UIModule.class})
public interface MobSoftApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(AppointmentActivity loginActivity);

    void inject(ConsultationHourSearchActivity consultationHourSearchActivity);

    void inject(ConsultationHourListActivity consultationHourListActivity);
}