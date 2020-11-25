package com.mubeen.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mubeen.task3.R;
public class ListActivity extends AppCompatActivity {

    private LinearLayout lay_one , lay_two , lay_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lay_one = findViewById(R.id.lay_one);
        lay_two = findViewById(R.id.lay_two);
        lay_three = findViewById(R.id.lay_three);

        lay_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

        lay_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),PaymentscreenTwo.class));

            }
        });

        lay_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),PaymentScreenThree.class));

            }
        });
    }
}
