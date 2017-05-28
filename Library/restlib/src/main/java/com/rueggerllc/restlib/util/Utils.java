package com.rueggerllc.restlib.util;



public class Utils {
    public static boolean isBlank(String value) {
        return value == null || value.trim().equals("");
    }
}
