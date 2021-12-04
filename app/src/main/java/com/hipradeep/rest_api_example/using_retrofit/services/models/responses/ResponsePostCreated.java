package com.hipradeep.rest_api_example.using_retrofit.services.models.responses;

public class ResponsePostCreated{
	private int id;
	private String title;
	private String body;
	private String userId;

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getBody(){
		return body;
	}

	public String getUserId(){
		return userId;
	}
}
