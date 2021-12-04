package com.hipradeep.rest_api_example.using_retrofit.services.models.responses.posts;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetPostsList{

	@SerializedName("ResponseGetPostsList")
	private List<PostsModel> responseGetPostsList;

	public List<PostsModel> getResponseGetPostsList(){
		return responseGetPostsList;
	}
}