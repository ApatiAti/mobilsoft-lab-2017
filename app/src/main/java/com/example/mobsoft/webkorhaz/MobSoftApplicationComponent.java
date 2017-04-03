package com.example.mobsoft.webkorhaz;


import com.example.mobsoft.webkorhaz.interactor.InteractorModule;
import com.example.mobsoft.webkorhaz.interactor.todo.FavouritesInteractor;
import com.example.mobsoft.webkorhaz.repository.RepositoryModule;
import com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch.ConsultationHourSearchActivity;
import com.example.mobsoft.webkorhaz.ui.UIModule;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.consultationHourList.ConsultationHourListActivity;
import com.example.mobsoft.webkorhaz.ui.login.LoginActivity;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;
import com.example.mobsoft.webkorhaz.ui.main.MainPresenter;
import com.example.mobsoft.webkorhaz.ui.navigation.NavigationActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class})
public interface MobSoftApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(AppointmentActivity loginActivity);

    void inject(ConsultationHourSearchActivity consultationHourSearchActivity);

    void inject(ConsultationHourListActivity consultationHourListActivity);

    void inject(NavigationActivity navigationActivity);

    void inject(FavouritesInteractor favouritesInteractor);

    void inject(MainPresenter mainPresenter);

    void inject(MobSoftApplication mobSoftApplication);

}