package com.nith.nimbus2k22;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.nith.nimbus2k22.Models.User_List;
import com.nith.nimbus2k22.apis.UserVolleyHelper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    Button btnsave;
    EditText etName,etRN,etPhoneNumber,etInstaID,etEmailId;
    ImageView image;
    FirebaseAuth auth;
    Uri filepath;
    long sizeOfImage;
    Bitmap bitmap;
    byte[] bytesofimage;
    String encodedimg;
    String picUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnsave=findViewById(R.id.btnsave);
        etName=findViewById(R.id.etName);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        etRN=findViewById(R.id.etRN);
        etInstaID=findViewById(R.id.etInstaId);
        etEmailId=findViewById(R.id.etemailId);
        auth=FirebaseAuth.getInstance();
        image=findViewById(R.id.image);
        btnsave=findViewById(R.id.btnsave);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(EditProfileActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent =new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"browse image"),1);
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
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString();
                String phoneNumber=etPhoneNumber.getText().toString();
                String rollNo=etRN.getText().toString();
                String instaID=etInstaID.getText().toString();
                String emailAdd=etEmailId.getText().toString();

//                User_List user=new User_List("6",)
                User_List user=new User_List("5",name,phoneNumber,emailAdd,name,"",0,true,"",false,"NITH");
                UserVolleyHelper User=new UserVolleyHelper(EditProfileActivity.this);
                User.createUser(user, "5");

                startActivity(new Intent(EditProfileActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==RESULT_OK){
            filepath = data.getData();
            try{
                InputStream inputStream =getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);


                encodeBitmapimage(bitmap);
                sizeOfImage = bytesofimage.length;
                if(sizeOfImage/1024 > 10000){
                    Toast.makeText(this, "Image size more than 10MB", Toast.LENGTH_LONG).show();

                }else{
                    image.setImageBitmap(bitmap);
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
                            Log.e("Data",picUrl);
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
            }
            catch (Exception e){

            }
        }
        else{
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapimage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        bytesofimage =byteArrayOutputStream.toByteArray();

        encodedimg = Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

}