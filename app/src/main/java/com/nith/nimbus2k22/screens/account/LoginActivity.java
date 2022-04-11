package com.nith.nimbus2k22.screens.account;

import static com.nith.nimbus2k22.apis.UserVolleyHelper.user_check;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import com.nith.nimbus2k22.MainActivity;
import com.nith.nimbus2k22.Models.Check_User;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.apis.UserVolleyHelper;

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



        forgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                finish();
            }
        });
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
//                Log.d("world",auth.getCurrentUser().getEmail());

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
<

                    auth.signInWithEmailAndPassword(txt_email,txt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            saveToken();


                            FirebaseUser user = auth.getCurrentUser();
                            Log.d("login user", "onSuccess: "+user);
                            UserVolleyHelper User=new UserVolleyHelper(LoginActivity.this);
                            UserVolleyHelper UserPresent=new UserVolleyHelper(LoginActivity.this);
                            UserPresent.check_User(txt_email);


                                final androidx.lifecycle.Observer<Check_User>ch1 = new androidx.lifecycle.Observer<Check_User>() {
                                    @Override
                                    public void onChanged(Check_User check_user) {

                                        if (user.isEmailVerified()) {

                                            if (check_user.getUser_present() == "true") {
                                                progrssDialog.dismiss();
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                finish();
                                            } else {
                                                progrssDialog.dismiss();
                                                startActivity(new Intent(LoginActivity.this, EditProfileActivity.class));
                                                finish();
                                            }
//                                            startActivity(new Intent(LoginActivity.this,EditProfileActivity.class));
//                                            finish();
                                        } else {
                                            progrssDialog.dismiss();
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
                                    };
                                user_check.observe(LoginActivity.this,ch1);

                        }
                    });
                    }
                }
        });
    }

    private void saveToken() {

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        mUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                Log.d("something", "valled");
                if (task.isSuccessful()) {
                    final SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    Context context = LoginActivity.this;

                    String idToken = task.getResult().getToken();
                    Log.e("NoToken", idToken);
                    Log.e("Uidfirebase", auth.getUid());
                    sharedPreferences = context.getSharedPreferences("Token", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("idToken", idToken);


                    editor.commit();

                } else {
                    task.getException();
                    Log.e("String Exception", String.valueOf(task.getException()));
                }
            }
        });
    }

}