package com.example.api.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.Weather.MainActivity;
import com.example.api.R;
import com.google.android.material.button.MaterialButton;

public class MyWeather extends AppCompatActivity {
    MaterialButton btnunits;
    TextView txtunits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_weather);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));


        btnunits = findViewById(R.id.btnunits);
        txtunits = findViewById(R.id.txtunits);

        btnunits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MyWeather.this, btnunits);
                popupMenu.getMenuInflater().inflate(R.menu.units_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();
                        if(id== R.id.metric){
                            txtunits.setText("°C");
                            Toast.makeText(MyWeather.this,"Đã chuyển đổi °C", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else if(id == R.id.imperial) {
                            txtunits.setText("°F");
                            Toast.makeText(MyWeather.this,"Đã chuyển đổi °F", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });
    }
}