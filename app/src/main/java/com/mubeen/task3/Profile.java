package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private TextView signedInAS, fullName, currentWeight, currentHeight, targetWeight, calorieIntake, goBack;
    User currentCustomUser;
    LinearLayout logout1;
    TextView logout2;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.userFullNameProfileTV);
        signedInAS = findViewById(R.id.loggedInAsGoalAct);
        currentWeight = findViewById(R.id.currentWeightProfileTV);
        currentHeight = findViewById(R.id.currentHeightProfileTV);
        targetWeight = findViewById(R.id.targetWeightProfileTV);
        calorieIntake = findViewById(R.id.calorieIntakeProfileTV);

        goBack = findViewById(R.id.goBackToMainFromProfile);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        signedInAS = findViewById(R.id.loggedInAsProfileAct);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");
        signedInAS.setText(currentCustomUser.getUsername());
        fullName.setText(currentCustomUser.getFirstName() + " " + currentCustomUser.getLastName());

        currentWeight.setText(currentCustomUser.getCurrentWeight());
        currentHeight.setText(currentCustomUser.getCurrentHeight());
        targetWeight.setText(currentCustomUser.getWeightGoal());
        calorieIntake.setText(currentCustomUser.getCalorieIntakeGoal());

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Profile.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
    }
}