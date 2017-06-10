package com.rueggerllc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.tasks.AsyncTaskBookClient;
import com.rueggerllc.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ListBooksActivity extends BaseActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);
    private List<String> bookList = new ArrayList<String>();
    private ListView bookListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("===== List Book Activity.onCreate() BEGIN  =====");

        setContentView(R.layout.activity_listbooks);
        initToolBar();

        AsyncTaskBookClient task = new AsyncTaskBookClient(this);
        String endpoint = this.getResources().getString(R.string.book_client_endpoint_local);
        // task.execute(new String[] {Constants.GET_BOOKS_URL});
        task.execute(new String[] {endpoint});


        logger.debug("===== List Book Activity.onCreate() END  =====");
    }


    @Override
    protected void onStart() {
        super.onStart();
        logger.debug("===== ListBooksActivity.onStart() =====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.debug("===== ListBooksActivity.onResume() =====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logger.debug("===== ListBooksActivity.onPause() =====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logger.debug("===== ListBooksActivity.onStop() =====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logger.debug("===== ListBooksActivity.onDestroy() =====");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logger.debug("===== ListBooksActivity.onRestart() =====");
    }




    public void buildList(List<Book> books) {
        try {
            logger.debug("Building List After Call");
            for (Book book :  books) {
                String title = book.getTitle();
                bookList.add(title);
            }

            ViewGroup layout = (ViewGroup) findViewById(R.id.activity_listbooks);
            // logger.debug("CHILD COUNT=" + layout.getChildCount());
            if (bookListView != null) {
                logger.debug("===== REMOVE LIST VIEW ====");
                layout.removeView(bookListView);
            }
            bookListView = new ListView(this);

            String[] bookListArray = bookList.toArray(new String[bookList.size()]);
            ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, bookListArray);
            bookListView.setAdapter(modeAdapter);

//            bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String item = (String)parent.getAdapter().getItem(position);
//                    logger.debug("ITEM=" + item);
//                    Intent intent = createIntentForBookDetails();
//                    intent.putExtra(Constants.SELECTED_BOOK_MESSAGE, item);
//                    startActivity(intent);
//                }
//            });

            layout.addView(bookListView);
            // Toast.makeText(getApplicationContext(), "Book List Populated", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            logger.debug("Error:\n" + e);
        }
    }


    public Intent createIntentForBookDetails() {
        Intent intent = new Intent(this, DisplayBookDetailsActivity.class);
        return intent;
    }

    public void buildError() {
        logger.debug("Main Activity build Error");
        Toast.makeText(getApplicationContext(), "Error Retrieving Books", Toast.LENGTH_SHORT).show();
    }


}
