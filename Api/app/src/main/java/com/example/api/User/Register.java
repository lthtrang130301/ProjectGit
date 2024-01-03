package com.example.api.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.api.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextView signin_txt;
    EditText firstName, lastName, Address, UserEmail, UserPass, PassConfirm;
    Button btnRegister;
    FirebaseAuth auth;
    FirebaseFirestore db;
    String userID;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        UserEmail = findViewById(R.id.UserEmail);
        UserPass = findViewById(R.id.UserPass);
        PassConfirm = findViewById(R.id.PassConfirm);
        Address = findViewById(R.id.Address);
        btnRegister = findViewById(R.id.btnRegister);
        signin_txt = findViewById(R.id.signin_txt);
        progressBar = findViewById(R.id.load);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);

                String email = UserEmail.getText().toString();
                String password = UserPass.getText().toString();
                String Firstname = firstName.getText().toString();
                String Lastname = lastName.getText().toString();
                String address = Address.getText().toString();
                String confirm = PassConfirm.getText().toString();

                if (Firstname.isEmpty()) {
                    firstName.setError("fill first name!");
                    return;
                }
                if (Lastname.isEmpty()) {
                    lastName.setError("fill last name!");
                    return;
                }
                if (email.isEmpty()) {
                    UserEmail.setError("fill email!");
                    return;
                }
                if (password.isEmpty() && password.length() >= 8) {
                    UserPass.setError("fill password!");
                    UserPass.setError("Length pass >= 8 characters!");
                    return;
                }
                if (confirm.isEmpty()) {
                    PassConfirm.setError("confirm password!");
                    return;
                }
                if (!password.equals(confirm)) {
                    PassConfirm.setText("");
                    PassConfirm.setError("incorrect!");
                    return;
                }

                if ( TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
                        || TextUtils.isEmpty(Firstname) || TextUtils.isEmpty(Lastname) || TextUtils.isEmpty(address)) {
                    Toast.makeText(Register.this, "Thong tin trong", Toast.LENGTH_LONG).show();
                } else {

                    progressBar.setVisibility(View.VISIBLE);

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Map<String, Object> user = new HashMap<>();
                                        user.put("First Name", Firstname);
                                        user.put("Last Name", Lastname);
                                        user.put("Address", address);
                                        user.put("Email", email);

                                        userID = auth.getCurrentUser().getUid();

                                        DocumentReference documentReference = db.collection("user").document(userID);
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull @NotNull Exception e) {
                                                Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }

                                        });
                                        Toast.makeText(Register.this, "Account & User Successful", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this, Login.class);
                                        intent.putExtra("email", email);
                                        intent.putExtra("password", password);
                                        startActivity(intent);
                                        progressBar.setVisibility(View.GONE);

                                    } else {
                                        Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }

            }
        });

        signin_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }
}