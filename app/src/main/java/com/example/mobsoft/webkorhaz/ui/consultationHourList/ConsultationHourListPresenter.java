package com.example.mobsoft.webkorhaz.ui.consultationHourList;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static com.example.mobsoft.webkorhaz.MobSoftApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 31..
 *
 */
@Deprecated
public class ConsultationHourListPresenter extends Presenter<ConsultationHourListScreen> {

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public ConsultationHourListPresenter() {
    }

    @Override
    public void attachScreen(ConsultationHourListScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
//        bus.register(this);
    }

    @Override
    public void detachScreen() {
//        bus.unregister(this);
        super.detachScreen();
    }

}
