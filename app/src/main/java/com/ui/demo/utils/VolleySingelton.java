package com.ui.demo.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingelton {

    private static VolleySingelton instance;
    private final RequestQueue requestQueue;

    private VolleySingelton(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static VolleySingelton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingelton(context);
        }
        return  instance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
