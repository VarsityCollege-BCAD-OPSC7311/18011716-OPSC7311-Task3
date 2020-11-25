package com.mubeen.task3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mubeen.task3.R;

public class Login extends AppCompatActivity {

    TextView registerTextView, newUserTextView;
    private FirebaseAuth firebaseAuth;
    private EditText username, password;
    DatabaseReference databaseReference;
    User activeUser;
    LinearLayout loginLL;
    FirebaseUser authUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        registerTextView = findViewById(R.id.loginScreenewUserRegisterTV);
        newUserTextView = findViewById(R.id.loginScreenNewUserTV);
        firebaseAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.userEmailLoginET);
        password = findViewById(R.id.userPasswordLoginET);
        loginLL = findViewById(R.id.loginLL);

        loginLL.setVisibility(View.VISIBLE);

        loginLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("") && password.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Email field can not be empty", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Password field can not be empty", Toast.LENGTH_SHORT).show();
                } else{
                    loginUser(username.getText().toString(), password.getText().toString());
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(String username, String password){
        Toast.makeText(Login.this, "Please wait...", Toast.LENGTH_SHORT).show();
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loginLL.setVisibility(View.INVISIBLE);
                            newUserTextView.setVisibility(View.INVISIBLE);
                            registerTextView.setVisibility(View.INVISIBLE);
                            Log.i("USER LOGIN ---> ", "signInWithCustomToken:success");
                            authUser = firebaseAuth.getCurrentUser();

                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    finish();
                                    activeUser = snapshot.child(authUser.getUid()).getValue(User.class);
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtra("CurrentCustomUser", activeUser);
                                    Log.i("DATAAAA---", "-------> " + activeUser.getCalorieIntakeGoal() + " " + activeUser.getWeightGoal());
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }else{
                            Log.i("USER LOGIN ---> ", "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}