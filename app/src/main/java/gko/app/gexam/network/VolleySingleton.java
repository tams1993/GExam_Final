package gko.app.gexam.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import gko.app.gexam.MyApplication;

/**
 * Created by MR.T on 4/2/2015.
 */
public class VolleySingleton {


    private static VolleySingleton sInstance = null;


    private RequestQueue mRequestQueue;

    private VolleySingleton() {

        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());


    }

    public static VolleySingleton getsInstance() {


        if (sInstance == null) {


            sInstance = new VolleySingleton();

        }

        return sInstance;
    }

    public RequestQueue getRequestQueue() {

        return mRequestQueue;
    }

}

