package com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch;

import android.util.Log;

import com.example.mobsoft.webkorhaz.interactor.consultationhour.ConsultationHourInteractor;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.GetDepartmentsDataEvent;
import com.example.mobsoft.webkorhaz.interactor.consultationhour.events.SearchConsultationHourEvent;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public class ConsultationHourSearchPresenter extends Presenter<ConsultationHourSearchScreen> {

    @Inject
    ConsultationHourInteractor consultationHourInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public ConsultationHourSearchPresenter() {
    }


    @Override
    public void attachScreen(ConsultationHourSearchScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }

    public void search(final ConsultationHourSearch consultationHourSearch){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                consultationHourInteractor.searchConsultationHour(consultationHourSearch);
            }
        });
    }

    public void getDepartmentsDataFromServer(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                consultationHourInteractor.getDepartmentsDataFromServer();
            }
        });
    }


    /**
     * {@link SearchConsultationHourEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(SearchConsultationHourEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showErrorMessage("error");
            }
            Log.e("Networking", "Error at login in", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showSearchResults(event.getConsultationHourDTOs());
            }
        }
    }

    /**
     * {@link GetDepartmentsDataEvent} eventeket a {@link EventBus}-ról feldolgozó metódus. Android UI szálát hasznélja a feldolgozásra
     * @param event
     */
    public void onEventMainThread(GetDepartmentsDataEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showErrorMessage("error");
            }
            Log.e("Networking", "Error at login in", event.getThrowable());
        } else {
            if (screen != null) {
                screen.loadDepartmentData(event.getDepartmentData());
            }
        }
    }
}
