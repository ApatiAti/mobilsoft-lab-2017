package com.example.mobsoft.webkorhaz.interactor;

import com.example.mobsoft.webkorhaz.interactor.appointment.AppointmentInteractor;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.ConsultationHourInteractor;
import com.example.mobsoft.webkorhaz.interactor.login.LoginInteractor;
import com.example.mobsoft.webkorhaz.interactor.todo.FavouritesInteractor;

import dagger.Module;
import dagger.Provides;


@Module
public class InteractorModule {


	@Provides
	public FavouritesInteractor provideFavourites() {
		return new FavouritesInteractor();
	}

	@Provides
	public AppointmentInteractor provideAppointment() {
		return new AppointmentInteractor();
	}

	@Provides
	public LoginInteractor provideLogin(){
		return new LoginInteractor();
	}

	@Provides
	public ConsultationHourInteractor provideConsultationHour(){
		return new ConsultationHourInteractor();
	}
}
