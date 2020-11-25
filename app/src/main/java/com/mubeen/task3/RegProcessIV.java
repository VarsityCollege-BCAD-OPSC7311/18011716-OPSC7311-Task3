package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mubeen.task3.R;
public class RegProcessIV extends AppCompatActivity {

    private TextView signedInAS;
    private RadioGroup rg;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    User currentCustomUser;
    LinearLayout next;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_process_i_v);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        signedInAS = findViewById(R.id.loggedInAsTV4);
        next = findViewById(R.id.continueFourLL);
        radioButton1 = findViewById(R.id.metricRB);
        radioButton2 = findViewById(R.id.imperialRB);
        rg = findViewById(R.id.mainRG);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAS.setText(currentCustomUser.getUsername());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rg.getCheckedRadioButtonId() == radioButton1.getId()){
                    currentCustomUser.setSystem("Metric System");
                    databaseReference.child("Users").child(currentCustomUser.getID()).child("system").setValue("Metric System");

                    finish();
                    Intent intent = new Intent(RegProcessIV.this, MainActivity.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    startActivity(intent);
                }
                else if (rg.getCheckedRadioButtonId() == radioButton2.getId()){
                    currentCustomUser.setSystem("Imperial System");
                    databaseReference.child("Users").child(currentCustomUser.getID()).child("system").setValue("Imperial System");

                    finish();
                    Intent intent = new Intent(RegProcessIV.this, MainActivity.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    startActivity(intent);
                }
            }
        });
    }
}