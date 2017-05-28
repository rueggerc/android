package com.rueggerllc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;

public class AddPetActivity extends AppCompatActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.debug("AddPetActivity Startup BEGIN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent theIntent = getIntent();

        logger.debug("AddPetActivity Startup END");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId()) {

            case R.id.action_home:
                logger.debug("CHOSEN: HOME");
                Intent theIntent = new Intent(this, MainActivity.class);
                theIntent.putExtra("Later", "Much");
                startActivity(theIntent);
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
                return super.onOptionsItemSelected(item);

        }

    }

    public boolean superOnOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void callAddPet(View view) {
        logger.debug("Add Pet Submit BEGIN");
    }

}
