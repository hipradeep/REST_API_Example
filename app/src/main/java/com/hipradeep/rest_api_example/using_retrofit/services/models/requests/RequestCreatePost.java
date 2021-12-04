package com.hipradeep.rest_api_example.using_retrofit.services.models.requests;

import com.google.gson.annotations.SerializedName;

public class RequestCreatePost{

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private String userId;

	public void setTitle(String title){
		this.title = title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}
}