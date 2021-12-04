package com.hipradeep.rest_api_example.using_retrofit.services;



import com.hipradeep.rest_api_example.using_retrofit.services.models.requests.RequestCreatePost;
import com.hipradeep.rest_api_example.using_retrofit.services.models.requests.RequestPostUpdate;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponseComments;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponseDeletePost;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponsePostCreated;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponsePostUpdated;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponsePosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServices {



    @GET("posts")
    Call<List<ResponsePosts>> getPostsList();

    @GET("posts/{id}/")
    Call<ResponsePosts> getSinglePosts(@Path("id") String id);

    @GET("posts/{id}/comments")
    Call<List<ResponseComments>> getComments(@Path("id") String id);


    @POST("posts")
    Call<ResponsePostCreated> createPost(@Body RequestCreatePost body);

    @PUT("posts/{id}/")
    Call<ResponsePostUpdated> updatePost(@Body RequestPostUpdate body, @Path("id") String id );

    @DELETE("posts/{id}/")
    Call<ResponseDeletePost> deletePost(@Path("id") String id );
}
