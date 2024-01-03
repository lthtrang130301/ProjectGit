package com.example.api.User;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import com.codebyashish.autoimageslider.AutoImageSlider;
import com.codebyashish.autoimageslider.Enums.ImageAnimationTypes;
import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.ExceptionsClass;
import com.codebyashish.autoimageslider.Models.ImageSlidesModel;
import com.example.api.Weather.MainActivity;
import com.example.api.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    TextView signup_txt, changePass_txt;
    EditText editMail, editPass;
    Button btnSingIn;
    String email, pass;
    static FirebaseAuth auth;
    FirebaseFirestore db;
    ProgressBar progressBar;
    static final String TAG = "Read Data Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        slide();

        editMail = findViewById(R.id.login_usermail);
        editPass = findViewById(R.id.login_password);
        btnSingIn = findViewById(R.id.login_btn);
        signup_txt = findViewById(R.id.signup_txt);
        changePass_txt = findViewById(R.id.changePass_txt);
        progressBar = findViewById(R.id.load);

        Intent intent = getIntent();
        String userMail = intent.getStringExtra("email");
        String userPass = intent.getStringExtra("password");

        editMail.setText(userMail);
        editPass.setText(userPass);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);

                String email = editMail.getText().toString();
                String pass = editPass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    editMail.setError("Fill email!");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    editPass.setError("Fill pass!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Login.this, "Login is success", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error, Email or Password wrong!!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        changePass_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(Login.this);
                dialog.setContentView(R.layout.forgot_password);

                Button ok = dialog.findViewById(R.id.buttonOk);
                EditText et = dialog.findViewById(R.id.editTextTextEmailAddress);
                dialog.show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(et.getText().toString().isEmpty()) {
                            et.setError("fill email");
                            return;
                        }
                        auth.sendPasswordResetEmail(et.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Check your mail!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    et.setError("Are you sure the email is correct?");
                                }
                            }
                        });
                    }
                });
            }
        });

        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    private void slide() {
        //Tao slide auto
        AutoImageSlider autoImageSlider = findViewById(R.id.autoImageSlider);
        // create an imageArrayList which extend ImageSlideModel class
        ArrayList<ImageSlidesModel> autoImageList = new ArrayList<>();
        // add some images or titles (text) inside the imagesArrayList
        try {
            autoImageList.add(new ImageSlidesModel(R.drawable.sea, ""));
            autoImageList.add(new ImageSlidesModel(R.drawable.tuyet, ""));
            autoImageList.add(new ImageSlidesModel(R.drawable.hoa, ""));
            autoImageList.add(new ImageSlidesModel(R.drawable.mua, ""));
            autoImageList.add(new ImageSlidesModel(R.drawable.bao, ""));
            // set the added images inside the AutoImageSlider
            autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT);
            autoImageSlider.setSlideAnimation(ImageAnimationTypes.ZOOM_IN);
        } catch (ExceptionsClass e) {
            throw new RuntimeException(e);
        }
    }

}

