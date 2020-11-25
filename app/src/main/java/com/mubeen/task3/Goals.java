package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mubeen.task3.R;
public class Goals extends AppCompatActivity {
    LinearLayout next;
    TextView signedInAs;
    TextView fullName;
    User currentCustomUser;
    DatabaseReference databaseReference;
    EditText tCalories;
    EditText tWeight;
    TextView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        next = findViewById(R.id.saveGoalsLL);
        signedInAs = findViewById(R.id.loggedInAsGoalAct);
        fullName = findViewById(R.id.userFullNameGoalsActTV);
        goBack = findViewById(R.id.goBackToMainFromGoal);

        tCalories = findViewById(R.id.calorieIntakeETGoalsAct);
        tWeight = findViewById(R.id.targetWeightETGoalsAct);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAs.setText(currentCustomUser.getUsername());
        fullName.setText(currentCustomUser.getFirstName() + " " + currentCustomUser.getLastName());

        if (currentCustomUser.getWeightGoal() == null || currentCustomUser.getWeightGoal().equals("")){ }
        else{
            tWeight.setText(currentCustomUser.getWeightGoal());
        }

        if (currentCustomUser.getCalorieIntakeGoal() == null || currentCustomUser.getCalorieIntakeGoal().equals("")){ }
        else{
            tCalories.setText(currentCustomUser.getCalorieIntakeGoal());
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Goals.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentCustomUser.setWeightGoal(tWeight.getText().toString());
                currentCustomUser.setCalorieIntakeGoal(tCalories.getText().toString());

                databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                databaseReference.child(currentCustomUser.getID()).child("weightGoal").setValue(tWeight.getText().toString());
                databaseReference.child(currentCustomUser.getID()).child("calorieIntakeGoal").setValue(tCalories.getText().toString());

                finish();
                Intent intent = new Intent(Goals.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
    }
}