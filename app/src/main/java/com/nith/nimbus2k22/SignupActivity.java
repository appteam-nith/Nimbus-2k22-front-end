package com.nith.nimbus2k22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText inputEmail,inputPassword,inputConfirmPassword;
    private Button btnSignUp;
    private FirebaseAuth auth;
    private ProgressDialog progrssDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputEmail = findViewById(R.id.etEmail);
        inputPassword = findViewById(R.id.etPassword);
        inputConfirmPassword = findViewById(R.id.etReEnterPassword);
        btnSignUp = findViewById(R.id.signup);
        auth = FirebaseAuth.getInstance();
        progrssDialog=new ProgressDialog(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email=inputEmail.getText().toString();
                String txt_password=inputPassword.getText().toString().trim();
                String txt_ConfirmPassword=inputConfirmPassword.getText().toString().trim();
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){

                    Toast.makeText(SignupActivity.this,"Empty Credentials" , Toast.LENGTH_SHORT).show();
                }else if(txt_password.length() <6){
                    inputPassword.setError("Password too short");
                    Toast.makeText(SignupActivity.this,"Password too short" ,Toast.LENGTH_SHORT).show();
                }else if(!txt_password.matches(txt_ConfirmPassword)){
                    inputConfirmPassword.setError("Password not matching in both fields");
                    Toast.makeText(SignupActivity.this,"Password not matching in both fields" ,Toast.LENGTH_SHORT).show();
                }
                else{
                    int len=txt_email.length();
                    if(len>=11) {
                        boolean isFound=txt_email.contains("@nith.ac.in");
                        if(isFound) {
//                            registerUser(txt_email,txt_password);

                            progrssDialog.setMessage("Please wait while Registering...");
                            progrssDialog.setTitle("Registration");
                            progrssDialog.setCanceledOnTouchOutside(false);
                            progrssDialog.show();


                            auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){

                                        FirebaseUser user=auth.getCurrentUser();
                                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(SignupActivity.this,"Verification Email Has Been Sent,Confirm your Email",Toast.LENGTH_LONG).show();
                                            }
                                        });
                                        progrssDialog.dismiss();
//                                        Toast.makeText(SignupActivity.this,"Registering User Successful" ,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        progrssDialog.dismiss();

                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(SignupActivity.this,"Provided Email Is Already Registered!", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(SignupActivity.this,"Registeration Failed" ,Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });

                        }
                        else {
                            inputEmail.setError("Use NITH Email ID");
                            Toast.makeText(SignupActivity.this, "Use NITH email ID", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}