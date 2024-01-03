package com.example.api.User;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.api.Weather.MainActivity;
import com.example.api.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;

public class ChangePass extends AppCompatActivity {
    Button OK;
    ImageView btnBack;
    TextView email;
    EditText currentp, newp, confirmp;
    LinearProgressIndicator lpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_change_pass);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        OK = findViewById(R.id.buttonOk);
        btnBack = findViewById(R.id.btnBack);
        email = findViewById(R.id.textView9);
        currentp = findViewById(R.id.currentpass);
        newp = findViewById(R.id.newpass);
        confirmp = findViewById(R.id.confirmpass);
        lpi = findViewById(R.id.linearProgress2);

        email.setText(Login.auth.getCurrentUser().getEmail());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
            }
        });

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentp.getText().toString().isEmpty()) {
                    currentp.setError("Fill password!");
                    return;
                }
                if (newp.getText().toString().isEmpty()) {
                    newp.setError("Fill new password!");
                    return;
                }
                if (confirmp.getText().toString().isEmpty()) {
                    confirmp.setError("Fill confirm password!");
                    return;
                }
                if (!newp.getText().toString().equals(confirmp.getText().toString())) {
                    confirmp.setText("");
                    confirmp.setError("incorrect");
                    return;
                }
                lpi.setVisibility(View.VISIBLE);

                // re-authenticating user
                AuthCredential ac = EmailAuthProvider.getCredential(Login.auth.getCurrentUser().getEmail(), currentp.getText().toString());
                Login.auth.getCurrentUser()
                        .reauthenticate(ac)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                } else {
                                    currentp.setError("incorrect");
                                }
                            }
                        });
                // updating password
                Login.auth.getCurrentUser()
                        .updatePassword(newp.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // LOGOUT FROM OTHER DEVICES, add a re-authenticate in onStart() of MainActivity
                                    Toast.makeText(ChangePass.this, "success", Toast.LENGTH_SHORT).show();
                                    Login.auth.signOut();
                                    Intent i = new Intent(ChangePass.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    lpi.setVisibility(View.GONE);
                                    Toast.makeText(ChangePass.this, "something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}