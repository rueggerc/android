package com.rueggerllc.activities;

import android.os.Bundle;

import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;

public class ListPetsActivity extends BaseActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("===== ListPetsActivity.onCreate() BEGIN  =====");
        setContentView(R.layout.activity_listpets);

        initToolBar();


        logger.debug("===== ListPetsActivity.onCreate() END  =====");
    }


    @Override
    protected void onStart() {
        super.onStart();
        logger.debug("===== ListPetsActivity.onStart() =====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.debug("===== ListPetsActivity.onResume() =====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logger.debug("===== ListPetsActivity.onPause() =====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logger.debug("===== ListPetsActivity.onStop() =====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logger.debug("===== ListPetsActivity.onDestroy() =====");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logger.debug("===== ListPetsActivity.onRestart() =====");
    }



}
