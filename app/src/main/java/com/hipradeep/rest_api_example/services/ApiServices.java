package com.hipradeep.rest_api_example.services;



import com.hipradeep.rest_api_example.services.models.requests.RequestData;
import com.hipradeep.rest_api_example.services.models.responses.ResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {


    @POST("interview/fetchdata.php")
    Call<ResponseData> getTestService(@Body RequestData requestObject);
}
