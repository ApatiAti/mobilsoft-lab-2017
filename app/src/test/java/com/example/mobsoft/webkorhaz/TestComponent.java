package com.example.mobsoft.webkorhaz;

import com.example.mobsoft.webkorhaz.interactor.InteractorModule;
import com.example.mobsoft.webkorhaz.mock.MockNetworkModule;
import com.example.mobsoft.webkorhaz.repository.TestRepositoryModule;
import com.example.mobsoft.webkorhaz.test.AppointmentPresenterTest;
import com.example.mobsoft.webkorhaz.test.MainPresenterTest;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class
        , InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends MobSoftApplicationComponent {

    void inject(AppointmentPresenterTest appointmentPresenterTest);

    void inject(MainPresenterTest mainPresenterTest);

}