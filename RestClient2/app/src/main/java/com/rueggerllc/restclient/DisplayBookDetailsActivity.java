package com.rueggerllc.restclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.rueggerllc.tasks.AsyncRestTask;
import com.rueggerllc.util.Constants;
import com.rueggerllc.util.Logger;

public class DisplayBookDetailsActivity extends AppCompatActivity {

    private Logger logger = new Logger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        logger.debug("DisplayBookActivity onCreate()");
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar_details);
        setSupportActionBar(toolbar);

        // Get the book we were called with
        Intent intent = getIntent();
        String bookName = intent.getStringExtra(Constants.SELECTED_BOOK_MESSAGE);

        //
        GridView gridView = (GridView) findViewById(R.id.gridView1);

        final String[] data = new String[] {
                "Title:", bookName,
                "later", "much"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        gridView.setAdapter(adapter);


        // Toast.makeText(getApplicationContext(), bookName, Toast.LENGTH_SHORT).show();
    }

    public boolean superOnOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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


}
