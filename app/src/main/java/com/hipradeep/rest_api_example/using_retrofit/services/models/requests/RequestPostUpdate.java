package com.hipradeep.rest_api_example.using_retrofit.services.models.requests;

import com.google.gson.annotations.SerializedName;

public class RequestPostUpdate {

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private String userId;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}
}