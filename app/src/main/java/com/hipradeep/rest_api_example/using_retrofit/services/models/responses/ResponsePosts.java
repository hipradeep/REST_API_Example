package com.hipradeep.rest_api_example.using_retrofit.services.models.responses;

import com.google.gson.annotations.SerializedName;

public class ResponsePosts {

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getBody(){
		return body;
	}

	public int getUserId(){
		return userId;
	}
}