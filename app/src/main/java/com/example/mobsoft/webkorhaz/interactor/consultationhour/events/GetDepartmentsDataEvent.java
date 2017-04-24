package com.example.mobsoft.webkorhaz.interactor.consultationhour.events;

import com.example.mobsoft.webkorhaz.model.dto.DepartmentData;

import java.util.List;

/**
 * Created by Apati on 2017.04.05..
 */

public class GetDepartmentsDataEvent {
    private int code;
    private List<DepartmentData> DepartmentData;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public GetDepartmentsDataEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DepartmentData> getDepartmentData() {
        return DepartmentData;
    }

    public void setDepartmentData(List<DepartmentData> departmentData) {
        DepartmentData = departmentData;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

//</editor-fold>
}
