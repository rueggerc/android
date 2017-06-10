package com.rueggerllc.activities;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.tasks.AsyncTaskAddBook;
import com.rueggerllc.util.Constants;

import java.util.Date;

public class AddBookActivity extends BaseActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.debug("AddBookActivity Startup BEGIN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        initToolBar();
        logger.debug("AddBookActivity Startup END");
    }



    public void addBook(View view) {
        try {
            logger.debug("Add Book Submit BEGIN");
            EditText titleText = (EditText) findViewById(R.id.title);
            String title = titleText.getText().toString();

            EditText numberOfPagesText = (EditText) findViewById(R.id.numberOfPages);
            String numberOfPages = numberOfPagesText.getText().toString();

            EditText publishDateText = (EditText) findViewById(R.id.publishDate);
            String publishDateValue = publishDateText.getText().toString();
            Date publishDate = dateFormat.parse(publishDateValue);


            logger.info("title=" + title);
            logger.info("numberOfPages=" + numberOfPages);
            logger.info("publishDate=" + publishDate);

            Book book = new Book();
            book.setTitle(title);
            book.setNumberOfPages(Integer.valueOf(numberOfPages));
            book.setPublicationDate(publishDate);

            AsyncTaskAddBook task = new AsyncTaskAddBook(this, book);
            task.execute(new String[]{Constants.GET_BOOKS_URL});

        } catch (Exception e) {
            logger.debug("ERROR\n" + e);
        }

    }

    public void gotoListBooks() {
        Intent theIntent = new Intent(this, ListBooksActivity.class);
        startActivity(theIntent);
    }




}
