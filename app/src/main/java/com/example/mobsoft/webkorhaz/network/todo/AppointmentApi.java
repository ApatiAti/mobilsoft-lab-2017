package com.example.mobsoft.webkorhaz.network.todo;


import com.example.mobsoft.webkorhaz.model.dto.AppointmentDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface AppointmentApi {
  /**
   * Delete appointment
   * With this endpoint user can delete an existing appointment 
   * @param request Appointment (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("appointment")
  Call<Void> appointmentDelete(
          @Body AppointmentDto request
  );

  /**
   * Get appointment
   * With this endpoint user can get an existing appointment with it&#39;s id 
   * @param appointmentId Appointment (required)
   * @return Call&lt;Appointment&gt;
   */
  
  @GET("appointment")
  Call<AppointmentDto> appointmentGet(
          @Query("appointmentId") Long appointmentId
  );

  /**
   * Get appointmentList
   * This endpoint returns information aboutthe user&#39;s appointment 
   * @return Call&lt;List<Appointment>&gt;
   */
  
  @GET("appointment/list")
  Call<List<AppointmentDto>> appointmentListGet(
          @Query("userId") Long userId
  );

  /**
   * Post appointment
   * With this endpoint user can create a new appointment 
   * @param request Appointment (required)
   * @return Call&lt;Void&gt;
   */
  
  @POST("appointment")
  Call<Void> appointmentPost(
          @Body AppointmentDto request
  );

  /**
   * Put appointment
   * With this endpoint user can update an existing appointment 
   * @param request Appointment (required)
   * @return Call&lt;Void&gt;
   */
  
  @PUT("appointment")
  Call<Void> appointmentPut(
          @Body AppointmentDto request
  );

}
