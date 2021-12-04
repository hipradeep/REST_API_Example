package com.hipradeep.rest_api_example.using_retrofit.services;



import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.comments.ResponseComments;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.posts.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {


//    @POST("interview/fetchdata.php")
//    Call<ResponseData> getTestService(@Body RequestData requestObject);

    @GET("{path}")
    Call<List<PostsModel>> getPostsList(@Path("path") String path);

    @GET("{path}")
    Call<PostsModel> getSinglePosts(@Path("path") String path);

    @GET("{path}")
    Call<List<ResponseComments>> getComments(@Path("path") String path);
}
