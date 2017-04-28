package com.example.mobsoft.webkorhaz.network.todo;

import com.example.mobsoft.webkorhaz.model.Department;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface DepartmentApi {
  /**
   * Get Departments and department&#39;s consultationHour&#39;s Types
   * The DepartmentsAndTypes endpoint returns information about the hospital&#39;s departments and about the consultation Hour&#39;s tpyes 
   * @return Call&lt;List<Department>&gt;
   */
  @GET("getDepartmentsAndTypes")
  Call<List<Department>> getDepartmentsAndTypesGet();
    

}
