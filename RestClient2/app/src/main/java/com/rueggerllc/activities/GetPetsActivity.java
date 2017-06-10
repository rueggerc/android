package com.rueggerllc.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;


public class GetPetsActivity extends BaseActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);
    private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.debug("GetPetsActivity Startup BEGIN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        initToolBar();


        // Intent theIntent = getIntent();
        // String value = theIntent.getStringExtra("Foo");
        // logger.debug("VALUE=" + value);

        logger.debug("GetPetsActivity Startup END");
    }



    public void callGetPets(View view) {
        logger.debug("GET PETS SERVICE CALL");
    }

}
