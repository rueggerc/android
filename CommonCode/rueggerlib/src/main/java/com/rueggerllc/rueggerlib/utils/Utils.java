package com.rueggerllc.rueggerlib.utils;



public class Utils {

        public static boolean isBlank(String value) {
            if (value == null || value.trim().equals("")) {
                return true;
            }
            return false;
        }
}
