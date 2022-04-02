package com.nith.nimbus2k22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class memePost extends AppCompatActivity {
ImageView memedisplay;
ImageView displaypic;
EditText caption;
ImageView btnuserpost;
private static final int PICK_IMAGE = 100;
Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_post);
        memedisplay=findViewById(R.id.memeDisplay);
        displaypic=findViewById(R.id.profilePic);
        caption=findViewById(R.id.caption);
//        choosebutton=findViewById(R.id.memeChooseButton);
        btnuserpost=findViewById(R.id.btn_user_post_image);
        memedisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });

        btnuserpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(memePost.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            memedisplay.setImageURI(imageUri);
            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();

        }

    }
}