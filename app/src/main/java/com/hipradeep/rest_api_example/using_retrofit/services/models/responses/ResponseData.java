package com.hipradeep.rest_api_example.using_retrofit.services.models.responses;

import com.google.gson.annotations.SerializedName;

public class ResponseData{

	@SerializedName("response")
	private String response;

	public String getResponse() {
		return response;
	}
}