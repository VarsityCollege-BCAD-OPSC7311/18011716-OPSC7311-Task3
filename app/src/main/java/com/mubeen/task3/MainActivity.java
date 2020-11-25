package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mubeen.task3.R;

public class MainActivity extends AppCompatActivity {

    private TextView signedInAS;
    private TextView fullName;
    User currentCustomUser;
    LinearLayout logout1;
    TextView logout2;
    DatabaseReference databaseReference;

    CardView cardView1, cardView2, cardView3, cardView4, cardView5;
    TextView textView1, textView2, textView3, textView4, textView5;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5;

    LinearLayout logoutMain1;
    TextView logoutMain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.userFullNameTV);

        cardView1 = findViewById(R.id.goalsCV);
        textView1 = findViewById(R.id.goalsTV);
        imageView1 = findViewById(R.id.goalsIV);
        linearLayout1 = findViewById(R.id.goalsLL);

        cardView2 = findViewById(R.id.logWeightCV);
        textView2 = findViewById(R.id.logWeightTV);
        imageView2 = findViewById(R.id.logWeightIV);
        linearLayout2 = findViewById(R.id.logWeightLL);

        cardView3 = findViewById(R.id.profileCV);
        textView3 = findViewById(R.id.profileTV);
        imageView3 = findViewById(R.id.profileIV);
        linearLayout3 = findViewById(R.id.profileLL);

        cardView4 = findViewById(R.id.trackMealsCV);
        textView4 = findViewById(R.id.trackMealsTV);
        imageView4 = findViewById(R.id.trackMealsIV);
        linearLayout4 = findViewById(R.id.trackMealsLL);

        cardView5 = findViewById(R.id.settingsCV);
        textView5 = findViewById(R.id.settingsTV);
        imageView5 = findViewById(R.id.settingsIV);
        linearLayout5 = findViewById(R.id.settingsLL);

        logout1 = findViewById(R.id.logOutLLDash);
        logout2 = findViewById(R.id.logOutTVDash);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        signedInAS = findViewById(R.id.loggedInAsDash);
        logoutMain1 = findViewById(R.id.logOutLLDash);
        logoutMain2 = findViewById(R.id.logOutTVDash);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");
        signedInAS.setText(currentCustomUser.getUsername());
        fullName.setText(currentCustomUser.getFirstName() + " " + currentCustomUser.getLastName());
        //firstName.setText(currentCustomUser.getFirstName().charAt(0) + ".");
        //lastName.setText(currentCustomUser.getLastName());

        //GOALS
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Goals.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Goals.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Goals.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        //LOG WEIGHT
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogWeight.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogWeight.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogWeight.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogWeight.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        //PROFILE
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        //MEAL TRACKING
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MealTracking.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MealTracking.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MealTracking.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MealTracking.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });



        //LOGOUT
        logoutMain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        logoutMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
