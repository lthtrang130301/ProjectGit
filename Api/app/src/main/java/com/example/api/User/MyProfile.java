package com.example.api.User;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.api.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyProfile extends AppCompatActivity {

    ImageView btnBack;
    TextView Email, FirstName, LastName, Address;
    FirebaseAuth auth;
    FirebaseFirestore db;
    DocumentReference docUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_my_profile);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));
        AX();
        Detail();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyProfile.this, Profile.class));
                finish();
            }
        });
    }

    private void AX() {
        Email = findViewById(R.id.Email);
        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Address = findViewById(R.id.Address);
        btnBack = findViewById(R.id.btnBack);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Email.setText(Login.auth.getCurrentUser().getEmail());
    }
    private void Detail() {
        String userID = Login.auth.getCurrentUser().getUid();
        docUser = db.collection("user").document(userID);
        docUser.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String Firstn = documentSnapshot.getString("First Name");
                            String Lastn = documentSnapshot.getString("Last Name");
                            String Adrr = documentSnapshot.getString("Address");

                            FirstName.setText(Firstn);
                            LastName.setText(Lastn);
                            Address.setText(Adrr);

                        }
                        else {
                            Toast.makeText(MyProfile.this, "user does not ex" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MyProfile.this, "Error!" ,Toast.LENGTH_SHORT).show();

                    }
                });

    }
}