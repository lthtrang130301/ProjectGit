package com.example.api.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.api.Weather.MainActivity;
import com.example.api.R;
import com.example.api.CityLike.SaveLocation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    ImageView imgBg;
    ShapeableImageView imgAvt;
    TextView txtUname,txtMail;
    Button MyProfile, MyAccount,MyPass, MyWeather, Support, HowUse,Logout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_profile);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        ax();
        bottom_menu();
        Detail();
        button_click();

    }

    private void ax() {
        txtUname = findViewById(R.id.txtUname);
        txtMail = findViewById(R.id.txtMail);

        MyProfile = findViewById(R.id.MyProfile);
        MyAccount = findViewById(R.id.MyAccount);
        MyPass = findViewById(R.id.MyPass);
        MyWeather = findViewById(R.id.MyWeather);
        Support = findViewById(R.id.Support);
        HowUse = findViewById(R.id.HowUse);
        Logout = findViewById(R.id.Logout);

        imgAvt = findViewById(R.id.imgAvt);
        imgBg = findViewById(R.id.imgBg);


    }
    private void Detail() {
        txtMail.setText(Login.auth.getCurrentUser().getEmail());

    }
    private void button_click() {

        MyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MyProfile.class));
                finish();
            }
        });
        MyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MyAccount.class));
                finish();
            }
        });
        MyPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, ChangePass.class));
                finish();

            }
        });
        MyWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MyWeather.class));
                finish();
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                builder.setTitle("logout");
                builder.setMessage("Are you sure want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent( getApplicationContext(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
    }


    private void bottom_menu() {// tao bottom menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_profile){
                return true;
            } else if (id == R.id.bottom_home) {
                startActivity(new Intent(Profile.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                finish();
                return true;
            } else if (id == R.id.bottom_location) {
                startActivity(new Intent(Profile.this, SaveLocation.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }
}