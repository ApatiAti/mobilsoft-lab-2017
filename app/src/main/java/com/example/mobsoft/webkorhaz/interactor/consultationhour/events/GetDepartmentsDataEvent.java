package com.example.mobsoft.webkorhaz.interactor.consultationhour.events;

import com.example.mobsoft.webkorhaz.model.Department;

import java.util.List;

/**
 * Created by Apati on 2017.04.05..
 */

public class GetDepartmentsDataEvent {
    private int code;
    private List<Department> Department;
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

    public List<Department> getDepartment() {
        return Department;
    }

    public void setDepartment(List<Department> department) {
        Department = department;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

//</editor-fold>
}
