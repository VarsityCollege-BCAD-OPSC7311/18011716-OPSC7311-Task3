package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mubeen.task3.R;
public class RegProcessII extends AppCompatActivity {

    LinearLayout next;
    TextView signedInAs;
    EditText firstNameET;
    EditText lastNameET;
    User currentCustomUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_process_i_i);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        next = findViewById(R.id.continueTwoLL);
        signedInAs = findViewById(R.id.loggedInAsTV2);
        firstNameET = findViewById(R.id.userFirstNameRegTV);
        lastNameET = findViewById(R.id.userLastNameRegTV);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAs.setText(currentCustomUser.getUsername());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameET.getText().toString().equals("") && lastNameET.getText().toString().equals("")){
                    Toast.makeText(RegProcessII.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                } else if (firstNameET.getText().toString().equals("")){
                    Toast.makeText(RegProcessII.this, "First name can not be empty", Toast.LENGTH_SHORT).show();
                } else if (lastNameET.getText().toString().equals("")){
                    Toast.makeText(RegProcessII.this, "Last name can not be empty", Toast.LENGTH_SHORT).show();
                } else{
                    currentCustomUser.setFirstName(firstNameET.getText().toString());
                    currentCustomUser.setLastName(lastNameET.getText().toString());

                    databaseReference.child("Users").child(currentCustomUser.getID()).setValue(currentCustomUser);

                    Intent intent = new Intent(RegProcessII.this, RegProcessIII.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    startActivity(intent);

                }
            }
        });
    }
}