package com.example.api.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.api.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MyAccount extends AppCompatActivity {
    TextView email, firstName, lastName, address;
    ImageView btnBack;
    Button deleteButton, CHANGE;
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser USER;
    DocumentReference docUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        email = findViewById(R.id.Email);
        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        address = findViewById(R.id.Address);
        deleteButton = findViewById(R.id.deleteButton);
        CHANGE = findViewById(R.id.Change);
        btnBack = findViewById(R.id.btnBack);

        auth = FirebaseAuth.getInstance();
        USER = auth.getCurrentUser();

        db = FirebaseFirestore.getInstance();
        email.setText(Login.auth.getCurrentUser().getEmail());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        Detail();
        CHANGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAcc();

    }

    private void DeleteAcc() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MyAccount.this);
        dialog.setTitle("Are you sure delete account?");
        dialog.setMessage("Your account will no longer exist on the weather app");
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                USER.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MyAccount.this, "Delete account!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        } else {
                            Toast.makeText(MyAccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
        });
    }

    private void Update() {
        String newfname = firstName.getText().toString();
        String newlname = lastName.getText().toString();
        String newaddr = address.getText().toString();

        if (newfname.isEmpty()) {
            firstName.setError("fill first name!");
            return;
        }
        if (newlname.isEmpty()) {
            lastName.setError("fill last name!");
            return;
        }
        if (newaddr.isEmpty()) {
            address.setError("fill email!");
            return;
        }
        if (TextUtils.isEmpty(newfname) || TextUtils.isEmpty(newlname) || TextUtils.isEmpty(newaddr)) {
            Toast.makeText(MyAccount.this, "Thong tin trong", Toast.LENGTH_LONG).show();
        }

        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String userID = Login.auth.getCurrentUser().getUid();
                            docUser = db.collection("user").document(userID);

                            Map<String,Object> DetailUserNew = new HashMap<>();
                            DetailUserNew.put("First Name", newfname);
                            DetailUserNew.put("Last Name", newlname);
                            DetailUserNew.put("Address", newaddr);

                            docUser.update(DetailUserNew )
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(MyAccount.this, "Updated!" ,Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MyAccount.this, "Error!" ,Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MyAccount.this, "Error!" ,Toast.LENGTH_SHORT).show();
                    }
                });
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

                            firstName.setText(Firstn);
                            lastName.setText(Lastn);
                            address.setText(Adrr);

                        }
                        else {
                            Toast.makeText(MyAccount.this, "user does not ex" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MyAccount.this, "Error!" ,Toast.LENGTH_SHORT).show();

                    }
                });
    }
}