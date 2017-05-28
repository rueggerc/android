package com.rueggerllc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.debug("AddBookActivity Startup BEGIN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent theIntent = getIntent();

        logger.debug("AddBookActivity Startup END");
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

    public void addBook(View view) {
        logger.debug("Add Book Submit BEGIN");
        EditText titleText = (EditText) findViewById(R.id.title);
        String title = titleText.getText().toString();

        EditText numberOfPagesText = (EditText) findViewById(R.id.numberOfPages);
        String numberOfPages = numberOfPagesText.getText().toString();

        EditText publishDateText = (EditText) findViewById(R.id.publishDate);
        String publishDate = publishDateText.getText().toString();

        logger.info("title=" + title);
        logger.info("numberOfPages=" + numberOfPages);
        logger.info("publishDate=" + publishDate);


    }

}
