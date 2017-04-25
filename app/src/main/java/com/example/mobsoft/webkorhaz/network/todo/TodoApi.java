package com.example.mobsoft.webkorhaz.network.todo;


import com.example.mobsoft.webkorhaz.model.Todo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface TodoApi {
  
  /**
   * Create a new instance of the model and persist it into the data source.
   * 
   * @param data Model instance data
   * @return Call<Void>
   */
  
  @POST("Todo")
  Call<Void> todoCreate(
    @Body Todo data
  );

  @GET
  Call<ResponseBody> getVanENetHu(@Url String url);

}