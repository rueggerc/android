package com.rueggerllc.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;

public abstract class BaseActivity extends AppCompatActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);


    protected void initToolBar() {
        logger.info("BaseActivity " +
                "initToolBar");
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        logger.info("BaseActivity creating Options Menu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = null;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId()) {

            case R.id.action_home:
                logger.debug("CHOSEN: HOME");
                intent = new Intent(BaseActivity.this, MainActivity.class);
                // theIntent.putExtra("Foo", "Bar");
                startActivity(intent);
                return true;

            case R.id.action_favorite:
                logger.debug("CHOSEN: Favorite");
                intent = new Intent(BaseActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_folder:
                logger.debug("CHOSEN: Folder");
                intent = new Intent(BaseActivity.this, GetPetsActivity.class);
                intent.putExtra("Foo", "Bar");
                startActivity(intent);
                return true;

            case R.id.action_search:
                logger.debug("CHOSEN: Find");
                intent = new Intent(BaseActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                logger.debug("CHOSEN: Settings");
                intent = new Intent(BaseActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                logger.debug("UNKNOWN ACTION");
                return super.onOptionsItemSelected(item);

        }

    }

    public boolean superOnOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }



}
