package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mubeen.task3.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogWeight extends AppCompatActivity {

    private TextView signedInAS, goBack, fullName;
    User currentCustomUser;
    LinearLayout logWeight, viewRecord;
    EditText weight;
    DatabaseReference databaseReference;
    long size, count;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_weight);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        goBack = findViewById(R.id.goBackToMainFromWeightLog);
        signedInAS = findViewById(R.id.loggedInAsLogWeightAct);
        weight = findViewById(R.id.targetWeightETGoalsAct);
        logWeight = findViewById(R.id.logWeightLL);
        viewRecord = findViewById(R.id.viewWeightRecordLL);
        signedInAS.setText(currentCustomUser.getUsername());
        fullName = findViewById(R.id.userFullNameLogWeightActTV);
        fullName.setText(currentCustomUser.getFirstName() + " " + currentCustomUser.getLastName());
        size = 0;

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LogWeight.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                size = dataSnapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        logWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
                databaseReference.child("Users").child(currentCustomUser.getID()).child("deltaWeight").child(Long.toString(size)).setValue("Your weight was " + weight.getText().toString() + " on the " + timeStamp);

                Intent intent = new Intent(LogWeight.this, ViewRecord.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        viewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogWeight.this, ViewRecord.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
    }
}