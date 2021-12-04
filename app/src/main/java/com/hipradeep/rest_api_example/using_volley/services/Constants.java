package com.hipradeep.rest_api_example.using_volley.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {
    public static  String URL_BASE = "https://jsonplaceholder.typicode.com/";

    public static boolean isNetworkConnectedMainThread(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }
}
