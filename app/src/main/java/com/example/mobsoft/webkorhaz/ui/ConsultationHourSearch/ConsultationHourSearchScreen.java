package com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.model.dto.DepartmentData;

import java.util.List;

/**
 * Created by mobsoft on 2017. 03. 31..
 */

public interface ConsultationHourSearchScreen {
    void showErrorMessage(String error);

    void showSearchResults(List<ConsultationHourDto> consultationHourDtoList);

    void loadDepartmentData(List<DepartmentData> data);
}
