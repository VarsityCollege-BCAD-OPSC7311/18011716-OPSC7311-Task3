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
public class RegProcessIII extends AppCompatActivity {

    private TextView signedInAS;
    private EditText currentHeightET;
    private EditText currentWeightET;
    User currentCustomUser;
    LinearLayout next;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_process_i_i_i);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        signedInAS = findViewById(R.id.loggedInAsTV3);
        next = findViewById(R.id.continueThreeLL);
        currentHeightET = findViewById(R.id.userCurrentHeightRegTV);
        currentWeightET = findViewById(R.id.userCurrentWeightRegTV);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAS.setText(currentCustomUser.getUsername());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentHeightET.getText().toString().equals("") || currentWeightET.getText().toString().equals("")){
                    Toast.makeText(RegProcessIII.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    currentCustomUser.setCurrentHeight(currentHeightET.getText().toString());
                    currentCustomUser.setCurrentWeight(currentWeightET.getText().toString());

                    databaseReference.child("Users").child(currentCustomUser.getID()).child("currentHeight").setValue(currentHeightET.getText().toString());
                    databaseReference.child("Users").child(currentCustomUser.getID()).child("currentWeight").setValue(currentWeightET.getText().toString());

                    Intent intent = new Intent(RegProcessIII.this, RegProcessIV.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            }
        });
    }
}