package com.rueggerllc.util;


import android.util.Log;

import com.rueggerllc.activities.BuildConfig;

public class Logger {

    private static final String CATEGORY = "com.rueggerllc.rest2";

    public void debug(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(CATEGORY, msg);
        }
    }
}
