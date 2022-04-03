package com.nith.nimbus2k22.screens.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nith.nimbus2k22.R;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail,inputPassword;
    private Button btnSignIn;
    private TextView lginAcc,forgotPassword;
    private FirebaseAuth auth;
    private ProgressDialog progrssDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.etEmail);
        inputPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.signin);
        lginAcc = findViewById(R.id.lginAcc);
        forgotPassword = findViewById(R.id.forgotPassword);
        auth = FirebaseAuth.getInstance();
        progrssDialog = new ProgressDialog(this);


        lginAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = inputEmail.getText().toString();
                String txt_password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    if (TextUtils.isEmpty(txt_email)) {
                        inputEmail.setError("Empty Email");
                    } else {
                        inputEmail.setError("Empty Password");
                    }
                }else{
                    progrssDialog.setMessage("Please wait while Login..");
                    progrssDialog.setTitle("Login ");
                    progrssDialog.setCanceledOnTouchOutside(false);
                    progrssDialog.show();

                    auth.signInWithEmailAndPassword(txt_email,txt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progrssDialog.dismiss();
                            FirebaseUser user = auth.getCurrentUser();

                            if (user.isEmailVerified()) {
                               startActivity(new Intent(LoginActivity.this, EditProfileActivity.class));
                               finish();
                            } else {
                                inputEmail.setError("Email not Verified");
                                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(LoginActivity.this, "Email not verified Confirm your email", Toast.LENGTH_LONG).show();
                                    }
                                });
                                auth.signOut();
                            }
                        }
                    });
                    }
                }
        });
    }
}