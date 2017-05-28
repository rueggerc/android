package com.rueggerllc.util;


import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rueggerllc.activities.R;
import com.rueggerllc.restlib.util.Logger;

public class ToolBar {

    private static Logger logger = new Logger(Constants.APPLICATION_NAME);

    public boolean onOptionsItemSelected(AppCompatActivity activity, MenuItem item) {

        int id = item.getItemId();

        switch(item.getItemId()) {

            case R.id.action_home:
                logger.debug("CHOSEN: Home");
                return true;

            case R.id.action_favorite:
                logger.debug("CHOSEN: Favorite");
                return true;

            case R.id.action_folder:
                logger.debug("CHOSEN: Folder");
                return true;

            case R.id.action_search:
                logger.debug("CHOSEN: Find");
                return true;

            case R.id.action_settings:
                logger.debug("CHOSEN: Settings");
                return true;

            default:
                logger.debug("UNKNOWN ACTION");
                return activity.onOptionsItemSelected(item);

        }

    }
}
