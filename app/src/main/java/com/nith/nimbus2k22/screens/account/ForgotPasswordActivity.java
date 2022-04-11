package com.nith.nimbus2k22.screens.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.nith.nimbus2k22.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText inputEmail;
    private Button btnNext;
    FirebaseAuth auth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.etEmail);
        btnNext = findViewById(R.id.next);
        progressDialog=new ProgressDialog(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String txt_email = inputEmail.getText().toString().trim();
                if(TextUtils.isEmpty(txt_email)){
//                    progressDialog.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this,"Enter Email",Toast.LENGTH_LONG).show();

                }
                else{
                    progressDialog.setMessage("Sending email to reset password");
                    progressDialog.setTitle("Forgot Password");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    auth.sendPasswordResetEmail(txt_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(ForgotPasswordActivity.this,"Email sent to your email to reset Password",Toast.LENGTH_LONG).show();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(ForgotPasswordActivity.this,"Try Again",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }

            }
    });
}
}