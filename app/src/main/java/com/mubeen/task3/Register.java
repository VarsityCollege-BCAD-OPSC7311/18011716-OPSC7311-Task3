package com.mubeen.task3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mubeen.task3.R;

public class Register extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private TextView alreadyRegistered;
    private LinearLayout register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        alreadyRegistered = findViewById(R.id.alreadyRegisteredTV);
        username = (EditText) findViewById(R.id.userEmailRegET);
        password = (EditText) findViewById(R.id.userPasswordRegET);
        register =  findViewById(R.id.registerLL);

        register.setVisibility(View.VISIBLE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(username.getText().toString(), password.getText().toString());
                }
            }
        });

        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }

    public void registerUser(String email, String password){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("USER REG ---> ", "createUserWithEmail:success");
                            final FirebaseUser activeFirebaseUser = firebaseAuth.getCurrentUser();
                            Toast.makeText(Register.this, "Registered!", Toast.LENGTH_SHORT).show();

                            final User user = new User();
                            if (activeFirebaseUser != null) {
                                user.setID(activeFirebaseUser.getUid());
                            }
                            user.setUsername(activeFirebaseUser.getEmail());

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                    Intent intent = new Intent(Register.this, RegProcessII.class);
                                    intent.putExtra("CurrentFirebaseUser", activeFirebaseUser);
                                    intent.putExtra("CurrentCustomUser", user);
                                    register.setVisibility(View.INVISIBLE);
                                    startActivity(intent);
                                }
                            }, 2000);
                            //updateUI(user);
                        } else {
                            Log.i("USER REG ---> ", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}