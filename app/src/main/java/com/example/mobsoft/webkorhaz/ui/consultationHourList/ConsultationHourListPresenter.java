package com.example.mobsoft.webkorhaz.ui.consultationHourList;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.ui.Presenter;

import java.util.List;

/**
 * Created by mobsoft on 2017. 03. 31..
 *
 */
public class ConsultationHourListPresenter extends Presenter<ConsultationHourListScreen> {

    public ConsultationHourListPresenter() {
    }

    @Override
    public void attachScreen(ConsultationHourListScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showConsultationHourList(List<ConsultationHourDto> consultationHourList){

    }
}
