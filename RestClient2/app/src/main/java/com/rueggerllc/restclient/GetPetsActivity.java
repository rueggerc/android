package com.rueggerllc.restclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.rueggerllc.tasks.AsyncRestTask;
import com.rueggerllc.tasks.DownloadImageTask;
import com.rueggerllc.util.Constants;
import com.rueggerllc.util.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Here is a comment
 */
public class GetPetsActivity extends AppCompatActivity {

    private Logger logger = new Logger();
    private List<String> bookList = new ArrayList<String>();
    private ListView bookListView = null;
    private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.debug("GetPetsActivity Startup BEGIN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent theIntent = getIntent();
        String value = theIntent.getStringExtra("Foo");
        logger.debug("VALUE=" + value);

        logger.debug("GetPetsActivity Startup END");
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

    public void callGetPets(View view) {
        logger.debug("GET PETS SERVICE CALL");
    }

}
