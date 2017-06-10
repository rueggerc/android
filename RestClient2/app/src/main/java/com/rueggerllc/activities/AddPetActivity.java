package com.rueggerllc.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.rueggerllc.client.RetroClient;
import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.rueggerllc.client.FileUploadService;

public class AddPetActivity extends BaseActivity {

    private Logger logger = new Logger(Constants.APPLICATION_NAME);

    private Button buttonChooseImage;
    private Button buttonUploadImage;
    private ImageView petImageView;
    private EditText petImageName;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    // private String UPLOAD_URL = "http://rueggerconsultingllc.com/RestWeb/uploadServlet";
    private String UPLOAD_URL = "http://192.168.1.167:8080/RestWeb/uploadServlet";
    // private String KEY_IMAGE = "image";
    // private String KEY_NAME = "name";
    private Uri imageURI;

    private String KEY_IMAGE = "file";
    private String KEY_NAME = "description";

    private static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.debug("AddPetActivity Startup BEGIN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet);
        initToolBar();

        buttonChooseImage = (Button) findViewById(R.id.buttonChoosePetImage);
        buttonUploadImage = (Button) findViewById(R.id.buttonAddPet);
        petImageName = (EditText) findViewById(R.id.petImageName);
        petImageView  = (ImageView) findViewById(R.id.imagePet);


        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);



        logger.debug("AddPetActivity Startup END");
    }



    public void callChoosePetImage(View view) {
        logger.debug("Add Pet Submit BEGIN");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        logger.info("== ON ACTIVITY RESULT ====");
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == PICK_IMAGE_REQUEST) {
                    logger.info("REQUEST CODE = PICK IMAGE REQUESST");
                    imageURI = data.getData();
                    logger.info("IMAGE URI =" + imageURI);
                    logger.info("FILE PATH=" + this.getRealPathFromURI(imageURI));
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageURI);
                    petImageView.setImageBitmap(bitmap);
                }
            }
        } catch (Exception e) {
            logger.debug("ERROR=\n" + e);
        }
    }

//    private byte[] getImageData(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        return imageBytes;
//        // String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        // return encodedImage;
//    }

    public void callUploadImage(View view) {
        logger.info("==== Upload Image Begin===");
        try {
            logger.info("IMAGE URI=" + imageURI);
            logger.info(imageURI.getPath());
            File imageFile = new File(getRealPathFromURI(imageURI));
            logger.info("GOT FILE=" + imageFile.getAbsolutePath());

            String petName = this.petImageName.getText().toString();
            logger.info("PET NAME=" + petName);
            logger.info("PetImageName=" + this.petImageName.getText());

            // final ProgressDialog progressDialog;
            // progressDialog = new ProgressDialog(AddPetActivity.this);
            // progressDialog.setMessage("Ruegger Uploading Image");
            // progressDialog.show();

            FileUploadService service = RetroClient.getApiService();

            // Description
            String descriptionString = "File from LG Phone:" + petName;
            RequestBody description = RequestBody.create(MultipartBody.FORM, descriptionString);

            // File
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageFile.getName(), requestFile);


            logger.info("HERE WE GO MAKING CALL");

            Call<ResponseBody> responseCall = service.uploadImage(description, body);

            responseCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    // progressDialog.dismiss();

                    // Response Success or Fail
                    if (response.isSuccessful()) {
                        logger.info("===== UPLOADED OK ========");

                    } else {
                        logger.info("=== ON RESPONSE BAD");
                    }

                    /**
                     * Update Views
                     */
                    // imagePath = "";
                    // textView.setVisibility(View.VISIBLE);
                    // imageView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    logger.info("UPLOAD FAILED");
                    logger.info("T=\n" + t);
                    // progressDialog.dismiss();
                }
            });



        } catch (Exception e) {
            logger.debug("ERROR:\n" + e);
        }

    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getContentResolver().query(contentUri, proj, null, null,
                    null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }



//    public void callUploadImage(View view){
//        //Showing the progress dialog
//        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        //Disimissing the progress dialog
//                        loading.dismiss();
//                        //Showing toast message of the response
//                        Toast.makeText(AddPetActivity.this, s , Toast.LENGTH_LONG).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        //Dismissing the progress dialog
//                        loading.dismiss();
//
//                        //Showing toast
//                        Toast.makeText(AddPetActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // Converting Bitmap to String
//                String image = getStringImage(bitmap);
//
//                // Getting Image Name
//                String name = petImageName.getText().toString().trim();
//
//                //Creating parameters
//                Map<String,String> params = new Hashtable<String, String>();
//
//                //Adding parameters
//                params.put(KEY_IMAGE, image);
//                params.put(KEY_NAME, name);
//
//                //returning parameters
//                return params;
//            }
//        };
//
//        //Creating a Request Queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //Adding request to the queue
//        requestQueue.add(stringRequest);
//    }

}

