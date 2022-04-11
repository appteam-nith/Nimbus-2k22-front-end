package com.nith.nimbus2k22;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.nith.nimbus2k22.apis.MemesManiaVolleyHelper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;


public class memePost extends AppCompatActivity {
    ImageView memedisplay;
    ImageView displaypic;
    EditText caption;
    ImageView btnuserpost;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    Uri filepath;
    String encodedimg;
    long sizeOfImage;
    FirebaseAuth auth;
    byte[] bytesofimage;
    Bitmap bitmap;
    String picUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_post);

        auth = FirebaseAuth.getInstance();
        memedisplay=findViewById(R.id.memeDisplay);
        displaypic=findViewById(R.id.profilePic);
        caption=findViewById(R.id.caption);
//        choosebutton=findViewById(R.id.memeChooseButton);
        btnuserpost=findViewById(R.id.btn_user_post_image);
        MemesManiaVolleyHelper m1 = new MemesManiaVolleyHelper(memePost.this);

        memedisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });

        btnuserpost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = caption.getText().toString();
              m1.createMeme(auth.getUid(),picUrl,text,"location","");

                Toast.makeText(memePost.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void openGallery() {

        Dexter.withActivity(memePost.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "browse image"), 1);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }

        }).check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);


                encodeBitmapimage(bitmap);
                sizeOfImage = bytesofimage.length;
                if (sizeOfImage / 1024 > 10000) {
                    Toast.makeText(this, "Image size more than 10MB", Toast.LENGTH_LONG).show();

                } else {

                    memedisplay.setImageBitmap(bitmap);

                    MediaManager.get().upload(filepath).callback(new UploadCallback() {
                        @Override
                        public void onStart(String requestId) {

                        }

                        @Override
                        public void onProgress(String requestId, long bytes, long totalBytes) {

                        }

                        @Override
                        public void onSuccess(String requestId, Map resultData) {
                            picUrl = resultData.get("url").toString();
                            Log.e("Data", picUrl);


                        }

                        @Override
                        public void onError(String requestId, ErrorInfo error) {

                        }

                        @Override
                        public void onReschedule(String requestId, ErrorInfo error) {

                        }
                    }).dispatch();

                }
                //img.setImageBitmap(bitmap);
            } catch (Exception e) {

            }
        } else {
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            memedisplay.setImageURI(imageUri);
            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();

        }


    }

    private void encodeBitmapimage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        bytesofimage = byteArrayOutputStream.toByteArray();

        encodedimg = Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
//            imageUri = data.getData();
//            memedisplay.setImageURI(imageUri);
//            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
//
//        }
//
//    }

}
