package com.mubeen.task3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MealTracking extends AppCompatActivity {

    private TextView signedInAS;
    private TextView fullName;
    User currentCustomUser;
    TextView uploadImage, select1, select2;
    LinearLayout select3, imagesLL;
    CardView select4;
    TextView upload1, upload2, goBack;
    LinearLayout upload3;
    CardView upload4;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    Uri imageUri;
    ScrollView scrollView;
    ArrayList imageList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_tracking);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        imagesLL = findViewById(R.id.mealTrackingLL);
        scrollView = findViewById(R.id.mealTrackingSB);
        select1 = findViewById(R.id.selectImageTVV);
        select2 = findViewById(R.id.selectImageTVv);
        select3 = findViewById(R.id.selectImageLL);
        select4 = findViewById(R.id.selectImageCV);

        upload1 = findViewById(R.id.uploadImageTVV);
        upload2 = findViewById(R.id.uploadImageTVv);
        upload3 = findViewById(R.id.uploadImageLL);
        upload4 = findViewById(R.id.uploadImageCV);

        goBack = findViewById(R.id.goBackToMainFromMealTracking);

        storageReference = FirebaseStorage.getInstance().getReference("Images");
        imageList = new ArrayList();


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        fullName = findViewById(R.id.userFullNameMealTrackingTV);
        signedInAS = findViewById(R.id.loggedInAsMealTrackingAct);
        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");
        signedInAS.setText(currentCustomUser.getUsername());
        fullName.setText(currentCustomUser.getFirstName() + " " + currentCustomUser.getLastName());


        //SELECTION
        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        select3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        select4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MealTracking.this, MainActivity.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (currentCustomUser.getUsername() != null) {
                    Iterable<DataSnapshot> list = snapshot.child(currentCustomUser.getID()).child("images").getChildren();

                    for (Iterator<DataSnapshot> i = list.iterator(); i.hasNext(); ) {
                        DataSnapshot item = i.next();
                        ImageView imageView = new ImageView(MealTracking.this);
                        imageView.setImageBitmap(getBitmapFromURL(item.getValue().toString()));
                        imagesLL.addView(imageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentResolver cr = getContentResolver();
                MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                String uriObject = mimeTypeMap.getExtensionFromMimeType(cr.getType(imageUri));

                final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + uriObject);

                //this code copied from a friend
                ref.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                                databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                                Toast.makeText(getApplicationContext(), "Image upload successful", Toast.LENGTH_LONG).show();
                                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                        new OnCompleteListener<Uri>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                String fileLink = task.getResult().toString();
                                                databaseReference.child(currentCustomUser.getID()).child("images").child(Integer.toString(taskSnapshot.hashCode())).setValue(fileLink);
                                            }
                                        });
                            }
                        });
            }
        });
        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentResolver cr = getContentResolver();
                MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                String uriObject = mimeTypeMap.getExtensionFromMimeType(cr.getType(imageUri));

                final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + uriObject);

                //this code copied from a friend
                ref.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                                databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                                Toast.makeText(getApplicationContext(), "Image upload successful", Toast.LENGTH_LONG).show();
                                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                        new OnCompleteListener<Uri>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                String fileLink = task.getResult().toString();
                                                databaseReference.child(currentCustomUser.getID()).child("images").child(Integer.toString(taskSnapshot.hashCode())).setValue(fileLink);
                                            }
                                        });
                            }
                        });
            }
        });
        upload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentResolver cr = getContentResolver();
                MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                String uriObject = mimeTypeMap.getExtensionFromMimeType(cr.getType(imageUri));

                final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + uriObject);

                //this code copied from a friend
                ref.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                                databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                                Toast.makeText(getApplicationContext(), "Image upload successful", Toast.LENGTH_LONG).show();
                                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                        new OnCompleteListener<Uri>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                String fileLink = task.getResult().toString();
                                                databaseReference.child(currentCustomUser.getID()).child("images").child(Integer.toString(taskSnapshot.hashCode())).setValue(fileLink);
                                            }
                                        });
                            }
                        });
            }
        });
        upload4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentResolver cr = getContentResolver();
                MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                String uriObject = mimeTypeMap.getExtensionFromMimeType(cr.getType(imageUri));

                final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + uriObject);

                //this code copied from a friend
                ref.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                                databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                                Toast.makeText(getApplicationContext(), "Image upload successful", Toast.LENGTH_LONG).show();
                                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                        new OnCompleteListener<Uri>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                String fileLink = task.getResult().toString();
                                                databaseReference.child(currentCustomUser.getID()).child("images").child(Integer.toString(taskSnapshot.hashCode())).setValue(fileLink);
                                            }
                                        });
                            }
                        });
            }
        });
    }

    //SOURCE - YOUTUBE
    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src ------> ",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}