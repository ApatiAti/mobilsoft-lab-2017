package com.example.mobsoft.webkorhaz;


import com.example.mobsoft.webkorhaz.interactor.InteractorModule;
import com.example.mobsoft.webkorhaz.interactor.appointment.AppointmentInteractor;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.ConsultationHourInteractor;
import com.example.mobsoft.webkorhaz.interactor.login.LoginInteractor;
import com.example.mobsoft.webkorhaz.interactor.todo.FavouritesInteractor;
import com.example.mobsoft.webkorhaz.repository.RepositoryModule;
import com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch.ConsultationHourSearchActivity;
import com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch.ConsultationHourSearchPresenter;
import com.example.mobsoft.webkorhaz.ui.UIModule;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentPresenter;
import com.example.mobsoft.webkorhaz.ui.consultationHourList.ConsultationHourListActivity;
import com.example.mobsoft.webkorhaz.ui.login.LoginActivity;
import com.example.mobsoft.webkorhaz.ui.login.LoginPresenter;
import com.example.mobsoft.webkorhaz.ui.main.MainActivity;
import com.example.mobsoft.webkorhaz.ui.main.MainPresenter;
import com.example.mobsoft.webkorhaz.ui.navigation.NavigationActivity;
import com.example.mobsoft.webkorhaz.ui.navigation.NavigationPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class})
public interface MobSoftApplicationComponent {

    /**
     * Activitys
     */
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(AppointmentActivity loginActivity);

    void inject(ConsultationHourSearchActivity consultationHourSearchActivity);

    void inject(ConsultationHourListActivity consultationHourListActivity);

    void inject(NavigationActivity navigationActivity);

    /**
     * Presenters
     */

    void inject(MainPresenter mainPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(AppointmentPresenter appointmentPresenter);

    void inject(ConsultationHourSearchPresenter consultationHourSearchPresenter);

    void inject(NavigationPresenter navigationPresenter);

    /**
     * Interractors
     */

    void inject(AppointmentInteractor appointmentInteractor);

    void inject(ConsultationHourInteractor consultationHourInteractor);

    void inject(LoginInteractor loginInteractor);

    // Test
    void inject(FavouritesInteractor favouritesInteractor);

    /**
     * Others
     */

    void inject(MobSoftApplication mobSoftApplication);

}