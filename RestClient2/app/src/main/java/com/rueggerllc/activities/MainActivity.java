package com.rueggerllc.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// Main Activity
// public class MainActivity extends AppCompatActivity {
   public class MainActivity extends BaseActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);
    private List<String> bookList = new ArrayList<String>();
    private ListView bookListView = null;
    private ImageView imageView = null;

    // this is the action code we use in our intent,
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("===== MainActivity.onCreate() =====");

        setContentView(R.layout.activity_main);
        initToolBar();

        // Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        // setSupportActionBar(toolbar);

        // Create Image View
        imageView = new ImageView(this);
        String imageURL = "http://rueggerconsultingllc.com/RestWeb/downloadServlet?name=oscar.jpg";
        Picasso.with(this)
                .load(imageURL)
                .resize(300, 300)
                .centerCrop()
                .into(imageView);

        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
        // imageView.setLayoutParams(lp);

        // final float scale = getResources().getDisplayMetrics().density;
        // int dpWidth = (int)(90*scale);
        // int dpHeight = (int)(120*scale);
        //  LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpWidth, dpHeight);
        // imageView.setLayoutParams(lp);



        // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.activity_main);
        linearLayout.addView(imageView);


        // String foo = "foobar";
        // boolean isBlank = Util.isBlank(foo);


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


    public void callGetBooks(View view) {
        logger.debug("Get Books Begin");
        // bookList.clear();
        // AsyncTaskBookClient task = new AsyncTaskBookClient(this);
        // task.execute(new String[] {Constants.GET_BOOKS_URL});
        // Toast.makeText(getApplicationContext(), "Service Called", Toast.LENGTH_SHORT).show();

        Intent theIntent = new Intent(this, ListBooksActivity.class);
        startActivity(theIntent);
    }


    public void callGetPets(View view) {
        logger.info("Get Pets Begin");
        Intent theIntent = new Intent(this, ListPetsActivity.class);
        startActivity(theIntent);
        // Toast.makeText(getApplicationContext(), "Get Pets", Toast.LENGTH_SHORT).show();
    }


    public void callAddBook(View view) {
        logger.debug("Add Book Begin");
        Toast.makeText(getApplicationContext(), "Add Book", Toast.LENGTH_SHORT).show();
        Intent theIntent = new Intent(this, AddBookActivity.class);
        theIntent.putExtra("Parm1", "Captain");
        startActivity(theIntent);
    }

    public void callAddPet(View view) {
        Intent theIntent = new Intent(this, AddPetActivity.class);
        startActivity(theIntent);
    }

    public void callGetImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        logger.info("== ON ACTIVITY RESULT ====");
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                logger.info("REQUEST CODE = SELECT PICTURE");
                Uri selectedImageUri = data.getData();
                // selectedImagePath = getPath(selectedImageUri);
                // logger.info("PATH=" + selectedImagePath);
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        logger.info("==GETTING IMAGE PATH");
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

    public void buildError() {
        logger.debug("Main Activity build Error");
        Toast.makeText(getApplicationContext(), "Error Retrieving Data", Toast.LENGTH_SHORT).show();
    }



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
            Toast.makeText(getApplicationContext(), "Book List Populated", Toast.LENGTH_SHORT).show();

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
