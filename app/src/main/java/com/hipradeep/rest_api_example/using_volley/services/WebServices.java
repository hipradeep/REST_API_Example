package com.hipradeep.rest_api_example.using_volley.services;



import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServices {
    Context context;


    public WebServices(Context context) {
        this.context = context;
    }
    

    public void getObjectResponse(String feed, JSONObject jsonBody, final WebServiceObjectResponseListener webServiceObjectResponseListener) {

        if(Constants.isNetworkConnectedMainThread(context))
        {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.URL_BASE + feed, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        webServiceObjectResponseListener.onResponse(response);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    webServiceObjectResponseListener.onError(error.getMessage());
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(jsonObjectRequest);
        }
        else
        {
            Toast.makeText(context, "No Internet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getArrayResponse(String feed, JSONArray jsonBody, final WebServiceArrayResponseListener webServiceArrayResponseListener) {

        if(Constants.isNetworkConnectedMainThread(context))
        {
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_BASE + feed, jsonBody, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        webServiceArrayResponseListener.onResponse(response);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    webServiceArrayResponseListener.onError(error.getMessage());
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(jsonObjectRequest);
        }
        else
        {
            Toast.makeText(context, "No Internet!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface WebServiceObjectResponseListener {
        void onResponse(JSONObject response) throws JSONException;
        void onError(String msg);
    }

    public interface WebServiceArrayResponseListener {
        void onResponse(JSONArray response) throws JSONException;
        void onError(String msg);
    }
}

