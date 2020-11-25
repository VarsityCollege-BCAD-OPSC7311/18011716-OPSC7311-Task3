package com.mubeen.task3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mubeen.task3.R;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewRecord extends AppCompatActivity {

    private TextView signedInAS, goBack, fullName;
    User currentCustomUser;
    LinearLayout logWeight, viewRecord;
    EditText weight;
    DatabaseReference databaseReference;
    long size, count;
    ArrayAdapter arrayAdapter;

    ArrayList weightArray;
    ListView weightLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        goBack = findViewById(R.id.goBackToMainFromWeighRecordTV);
        weightArray = new ArrayList();
        weightLV = findViewById(R.id.weightListRecord);
        signedInAS = findViewById(R.id.loggedInAsLogWeightRecAct);
        weight = findViewById(R.id.targetWeightETGoalsAct);
        logWeight = findViewById(R.id.logWeightLL);
        viewRecord = findViewById(R.id.viewWeightRecordLL);
        signedInAS.setText(currentCustomUser.getUsername());
        fullName = findViewById(R.id.userFullNameViewDataActTV);
        fullName.setText(currentCustomUser.getFirstName() + " " + currentCustomUser.getLastName());
        size = 0;

        arrayAdapter = new ArrayAdapter(ViewRecord.this, android.R.layout.simple_list_item_1, weightArray);
        weightLV.setAdapter(arrayAdapter);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ViewRecord.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //this code was directly copied from a friend
                Iterable<DataSnapshot> list = snapshot.child(currentCustomUser.getID()).child("deltaWeight").getChildren();
                count = snapshot.child(currentCustomUser.getID()).child("deltaWeight").getChildrenCount();

                Log.i("Child Count", "--------------> " + count);
                for (Iterator<DataSnapshot> i = list.iterator(); i.hasNext();) {
                    DataSnapshot item = i.next();
                    weightArray.add(item.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}