package com.rueggerllc.util;


import android.util.Log;

public class Logger {

    private static final String APPLICATION = "RestClient";

    public void debug(String msg) {
        Log.d(APPLICATION, msg);
    }
}
