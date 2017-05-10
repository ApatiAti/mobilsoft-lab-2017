package com.example.mobsoft.webkorhaz.ui.ConsultationHourSearch;

import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;

import java.util.List;



public interface ConsultationHourSearchScreen {
    void showErrorMessage(String error);

    void navigateAndShowSearchResults(List<ConsultationHourDto> consultationHourDTOs);

    void loadDepartmentData(List<Department> department);
}
