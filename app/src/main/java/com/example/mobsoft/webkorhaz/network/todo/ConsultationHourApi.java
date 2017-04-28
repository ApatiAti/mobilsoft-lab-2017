package com.example.mobsoft.webkorhaz.network.todo;

import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ConsultationHourApi {
  /**
   * Search for ConsultationHour
   * The consultationHourSearch endpoint returns information about the searched consultationHours 
   * @param request Search data (required)
   * @return Call&lt;List<ConsultationHour>&gt;
   */
  @POST("consultationHourSearch")
  Call<List<ConsultationHourDto>> consultationHourSearchPost(
          @Body ConsultationHourSearch request
  );

}
