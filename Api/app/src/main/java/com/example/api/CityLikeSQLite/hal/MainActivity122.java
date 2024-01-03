package com.example.api.CityLikeSQLite.hal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.api.R;
import com.example.api.User.Profile;
import com.example.api.Weather.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity122 extends AppCompatActivity {
    FloatingActionButton addlocation;
    EditText editdiadiem;
    AppCompatButton btLayThongTin;
    TextView Clear ;
    ListView lvDanhSach;
    AdapterCityLike adapterCityLike;
    //int index = -1;
    ArrayList<CityModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        editdiadiem = findViewById(R.id.editdiadiem);
        btLayThongTin = findViewById(R.id.btLayThongTin);
        Clear = findViewById(R.id.Clear);
        addlocation = findViewById(R.id.addlocation);
        lvDanhSach = findViewById(R.id.lvDanhSach);

        HienThiDL();

        btLayThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tim kiem
                String TenCity = editdiadiem.getText().toString();

                DbCityLike dbCityLike = new DbCityLike(MainActivity122.this);
                dbCityLike.Timkiem(TenCity);
                data = dbCityLike.LayDL();
                adapterCityLike = new AdapterCityLike(MainActivity122.this, R.layout.item_list_location, data);
                lvDanhSach.setAdapter(adapterCityLike);
                Toast.makeText(MainActivity122.this, "ok", Toast.LENGTH_SHORT).show();

            }
        });


        addlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity122.this);
                dialog.setContentView(R.layout.add_list_location);

                Button ok = dialog.findViewById(R.id.buttonOk);
                EditText insertCity = dialog.findViewById(R.id.insertCity);
                EditText insertNote = dialog.findViewById(R.id.insertNote);

                dialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DbCityLike dbCityLike = new DbCityLike(MainActivity122.this);
                        CityModel cityModel = new CityModel();

                        String strCity = insertCity.getText().toString().trim();
                        String strNote = insertNote.getText().toString().trim();
                        if (TextUtils.isEmpty(strCity) || TextUtils.isEmpty(strNote)) {
                            return;
                        }
                        cityModel.setTenCity(strCity);
                        cityModel.setMota(strNote);

                        dbCityLike.Them(cityModel);
                        data = dbCityLike.LayDL();
                        adapterCityLike = new AdapterCityLike(MainActivity122.this, R.layout.item_list_location, data);
                        lvDanhSach.setAdapter(adapterCityLike);
                        Toast.makeText(MainActivity122.this, "ok", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity122.this);
                builder.setTitle("DELETE");
                builder.setMessage("Are you sure want to delete all?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete all
                        DbCityLike dbCityLike = new DbCityLike(getApplicationContext());
                        CityModel cityModel = new CityModel();
                        dbCityLike.XoaHet(cityModel);
                        adapterCityLike.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Delete All", Toast.LENGTH_SHORT).show();
                        HienThiDL();
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


        // tao bottom menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_home) {
                return true;
            } else if (id == R.id.bottom_location) {
                startActivity(new Intent(MainActivity122.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (id == R.id.bottom_profile) {
                startActivity(new Intent(MainActivity122.this, Profile.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }
    private void HienThiDL() {
        DbCityLike dbCityLike = new DbCityLike(this);
        data = dbCityLike.LayDL();
        adapterCityLike = new AdapterCityLike(this,R.layout.item_list_location,data);
        lvDanhSach.setAdapter(adapterCityLike);
    }


}