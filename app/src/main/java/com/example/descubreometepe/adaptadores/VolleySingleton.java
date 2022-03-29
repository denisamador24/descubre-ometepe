package com.example.descubreometepe.adaptadores;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton volleyInstacia;
    private RequestQueue requestQueue;
    private static Context mContext;

    private VolleySingleton(Context mContext) {
        this.mContext = mContext;
        requestQueue = getRequetQueue();
    }

    public static synchronized VolleySingleton getVolleyInstacia(Context mContext) {
        if(volleyInstacia == null){
            volleyInstacia = new VolleySingleton(mContext);
        }
        return volleyInstacia;
    }

    private RequestQueue getRequetQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> reg){
        getRequetQueue().add(reg);
    }
}
