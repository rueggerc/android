package com.rueggerllc.restlib.util;

import android.util.Log;


public class Logger {

    private String application;

    public Logger(String application){
        this.application = application;
    }


    public void info(String msg) {
        Log.i(application, msg);
    }

    public void debug(String msg) {
        Log.d(application, msg);
    }

}
