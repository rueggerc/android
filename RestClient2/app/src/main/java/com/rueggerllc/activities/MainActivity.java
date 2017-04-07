package com.rueggerllc.activities;

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

import com.rueggerllc.restclient.util.Util;
import com.rueggerllc.restlib.beans.Book;
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
public class MainActivity extends AppCompatActivity {

    private Logger logger = new Logger();
    private List<String> bookList = new ArrayList<String>();
    private ListView bookListView = null;
    private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("===== MainActivity.onCreate() =====");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Create Image View
        imageView = new ImageView(this);
        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
        // imageView.setLayoutParams(lp);

        final float scale = getResources().getDisplayMetrics().density;
        int dpWidth = (int)(90*scale);
        int dpHeight = (int)(120*scale);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpWidth, dpHeight);
        imageView.setLayoutParams(lp);



        // imageView.getLayoutParams().width = 100;
        // imageView.getLayoutParams().height = 80;


        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.activity_main);
        linearLayout.addView(imageView);

        // DownloadImageTask task = new DownloadImageTask(this);
        // String imageURL = "http://rueggerconsultingllc.com/struts1WAR/downloadServlet?type=pet&id=ff808181579482a40157aafe03970000";
        // task.execute(new String[] {imageURL});

        String foo = "foobar";
        boolean isBlank = Util.isBlank(foo);


        logger.debug("Main Activity Startup");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.debug("===== MainActivity.onStart() =====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.debug("===== MainActivity.onResume() =====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logger.debug("===== MainActivity.onPause() =====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logger.debug("===== MainActivity.onStop() =====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logger.debug("===== MainActivity.onDestroy() =====");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logger.debug("===== MainActivity.onRestart() =====");
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
                Intent theIntent = new Intent(MainActivity.this, GetPetsActivity.class);
                theIntent.putExtra("Foo", "Bar");
                startActivity(theIntent);
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


    public void callService(View view) {
        Log.d("RestClient", "Get Books Begin");
        bookList.clear();
        AsyncRestTask task = new AsyncRestTask(this);
        task.execute(new String[] {Constants.GET_BOOKS_URL});
        // Toast.makeText(getApplicationContext(), "Service Called", Toast.LENGTH_SHORT).show();
    }

    public void buildError() {
        logger.debug("Main Activity build Error");
        Toast.makeText(getApplicationContext(), "Error Retrieving Data", Toast.LENGTH_SHORT).show();
    }

    /*
    private void parseResponse(String response) {
        try {
            JSONArray books = new JSONArray(response);
            for (int i = 0; i < books.length(); i++) {
                JSONObject book = books.getJSONObject(i);
                String title = book.getString("title");
                getBookList().add(title);
                logger.debug("Added=" + title);
            }
        } catch (Exception e) {

        }
    }
    */


    public void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public void buildList(List<Book> books) {
        try {
            logger.debug("Building List After Call");
            for (Book book :  books) {
                String title = book.getTitle();
                getBookList().add(title);
            }

            // parseResponse(response);
            ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);
            logger.debug("CHILD COUNT=" + layout.getChildCount());
            if (bookListView != null) {
                logger.debug("===== REMOVE LIST VIEW ====");
                layout.removeView(bookListView);
            }
            bookListView = new ListView(this);

            String[] bookListArray = bookList.toArray(new String[bookList.size()]);
            ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, bookListArray);
            bookListView.setAdapter(modeAdapter);

            bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String)parent.getAdapter().getItem(position);
                    logger.debug("ITEM=" + item);
                    Intent intent = createIntentForBookDetails();
                    intent.putExtra(Constants.SELECTED_BOOK_MESSAGE, item);
                    startActivity(intent);
                }
            });

            layout.addView(bookListView);
            Toast.makeText(getApplicationContext(), "Cool!!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            logger.debug("Error:\n" + e);
        }
    }


    public Intent createIntentForBookDetails() {
        Intent intent = new Intent(this, DisplayBookDetailsActivity.class);
        return intent;
    }

    public List<String> getBookList() {
        return bookList;
    }

}
