package com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;

import java.util.List;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public interface ConsultationHourSearchScreen {
    void showErrorMessage(String error);

    void showSearchResults(List<ConsultationHourDTO> consultationHourDTOs);
}
