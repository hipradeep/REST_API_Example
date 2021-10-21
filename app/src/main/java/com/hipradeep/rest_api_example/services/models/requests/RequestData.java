package com.hipradeep.rest_api_example.services.models.requests;

import com.google.gson.annotations.SerializedName;

public class RequestData{

	@SerializedName("email")
	private String email;

	public String getEmail(){
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}