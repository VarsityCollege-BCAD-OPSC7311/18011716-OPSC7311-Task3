package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {

    private TextView signedInAS;
    private TextView fullName;
    User currentCustomUser;
    LinearLayout logout1, update1;
    TextView logout2, update2;
    RadioButton imperial, metric;
    DatabaseReference databaseReference;
    ImageView update3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        signedInAS = findViewById(R.id.loggedInAsDash);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        imperial = findViewById(R.id.imperialRB);
        metric = findViewById(R.id.metricRB);

        update1 = findViewById(R.id.updateSystemLL);
        update2 = findViewById(R.id.updateSystemTV);
        update3 = findViewById(R.id.updateSystemIV);

        fullName = findViewById(R.id.userFullNameSettingsTV);

        signedInAS.setText(currentCustomUser.getUsername());
        fullName.setText(currentCustomUser.getFirstName() + " " + currentCustomUser.getLastName());

        if (currentCustomUser.getSystem().equals("Metric System")) {
            metric.setChecked(true);
            imperial.setChecked(false);
        }

        if (currentCustomUser.getSystem().equals("Imperial System")) {
            imperial.setChecked(true);
            metric.setChecked(false);
        }

        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imperial.isSelected()){
                    databaseReference.child(currentCustomUser.getUsername()).child("system").setValue("Metric System");
                }
                if (metric.isSelected()){
                    databaseReference.child(currentCustomUser.getUsername()).child("system").setValue("Imperial System");
                }

                Toast.makeText(Settings.this, "System default changed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imperial.isSelected()){
                    databaseReference.child(currentCustomUser.getUsername()).child("system").setValue("Metric System");
                }
                if (metric.isSelected()){
                    databaseReference.child(currentCustomUser.getUsername()).child("system").setValue("Imperial System");
                }

                Toast.makeText(Settings.this, "System default changed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        update3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imperial.isSelected()){
                    databaseReference.child(currentCustomUser.getUsername()).child("system").setValue("Metric System");
                }
                if (metric.isSelected()){
                    databaseReference.child(currentCustomUser.getUsername()).child("system").setValue("Imperial System");
                }

                Toast.makeText(Settings.this, "System default changed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
    }
}