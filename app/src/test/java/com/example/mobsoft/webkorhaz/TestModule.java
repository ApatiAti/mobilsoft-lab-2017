package com.example.mobsoft.webkorhaz;


import android.content.Context;

import com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch.ConsultationHourSearchPresenter;
import com.example.mobsoft.webkorhaz.ui.UIModule;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentPresenter;
import com.example.mobsoft.webkorhaz.ui.consultationHourList.ConsultationHourListPresenter;
import com.example.mobsoft.webkorhaz.ui.login.LoginPresenter;
import com.example.mobsoft.webkorhaz.ui.main.MainPresenter;
import com.example.mobsoft.webkorhaz.ui.navigation.NavigationPresenter;
import com.example.mobsoft.webkorhaz.utils.UiExecutor;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Module
public class TestModule {

	private final UIModule UIModule;

	public TestModule(Context context) {
		this.UIModule = new UIModule(context);
	}

	@Provides
	public Context provideContext() {
		return UIModule.provideContext();
	}


    /**
     * Preseneters
     * @return
     */
	@Provides
	public MainPresenter provideMainPresenter() {
		return UIModule.provideMainPresenter();
	}

    @Provides
    public LoginPresenter provideLoginPresenter() {
        return UIModule.provideLoginPresenter();
    }


    @Provides
    public AppointmentPresenter provideAppointmentPresenter() {
        return new AppointmentPresenter();
    }

    @Provides
    public ConsultationHourSearchPresenter provideConsultationHourSearchPresenter() {
        return new ConsultationHourSearchPresenter();
    }

    @Provides
    public ConsultationHourListPresenter provideConsultationHourListPresenter() {
        return new ConsultationHourListPresenter();
    }

    @Provides
    public NavigationPresenter provideNavigationPresenter() {
        return new NavigationPresenter();
    }




    @Provides
	@Singleton
	public EventBus provideEventBus() {
		return EventBus.getDefault();
	}

	@Provides
	@Singleton
	public Executor provideNetworkExecutor() {
		return new UiExecutor();
	}


}