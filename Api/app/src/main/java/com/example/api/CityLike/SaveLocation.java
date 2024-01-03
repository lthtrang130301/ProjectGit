package com.example.api.CityLike;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Weather.MainActivity;
import com.example.api.R;
import com.example.api.User.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SaveLocation extends AppCompatActivity {
    EditText editdiadiem;
    ImageView Anh;
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerview;
    Button btLayThongTin;
    FloatingActionButton addlocation;
    TextView Clear, txtNdo, note;
    private CityAdapter cityAdapter;
    private List<CityModel> mListCityModel;
    String apikey="e0ea7c2430957c0b90c7a6375a5f8cba";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savelocation);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        Init();

        cityAdapter = new CityAdapter(new CityAdapter.IClick() {
            @Override
            public void deleteCity(CityModel cityModel) {
                DeleteItem(cityModel);
            }
        });

        mListCityModel = new ArrayList<>();
        cityAdapter.setData(mListCityModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        recyclerview.setAdapter(cityAdapter);
        addlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(SaveLocation.this);
                dialog.setContentView(R.layout.add_list_location);
                Button ok = dialog.findViewById(R.id.buttonOk);
                EditText insertCity = dialog.findViewById(R.id.insertCity);
                EditText insertNote = dialog.findViewById(R.id.insertNote);

                dialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String strCity = insertCity.getText().toString().trim();
                        String strNote = insertNote.getText().toString().trim();
                        if(TextUtils.isEmpty(strCity) || TextUtils.isEmpty(strNote)) {
                            return;
                        }
                        CityModel cityModel = new CityModel(strCity, strNote);
                        if(isCityExist(cityModel)){
                            Toast.makeText(SaveLocation.this, "City exist!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        CityDatabase.getInstance(SaveLocation.this).cityDAO().insertCity(cityModel);
                        Toast.makeText(SaveLocation.this, "ok",Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    }
                });
            }
        });

        loadData();

        btLayThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = editdiadiem.getText().toString().trim();
                mListCityModel = new ArrayList<>();
                mListCityModel = CityDatabase.getInstance(SaveLocation.this).cityDAO().searchCity(strName);
                cityAdapter.setData(mListCityModel);
            }
        });
        editdiadiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){

                }
                return false;
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAll();
            }
        });
        bottomNagationView();
    }

    private void Init() {
        recyclerview = findViewById(R.id.recyclerview);
        editdiadiem = findViewById(R.id.editdiadiem);
        Clear = findViewById(R.id.Clear);
        btLayThongTin = findViewById(R.id.btLayThongTin);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        addlocation = findViewById(R.id.addlocation);
    }
    private void loadData() {
        mListCityModel = CityDatabase.getInstance(SaveLocation.this).cityDAO().getListCity();
        cityAdapter.setData(mListCityModel);
    }
    private boolean isCityExist(@NonNull CityModel cityModel){
        List<CityModel> list = CityDatabase.getInstance(SaveLocation.this).cityDAO().checkcity(cityModel.getCityname());
        return list != null && !list.isEmpty();
    }
//    private void ShowFindCity(String strCity) {
//        //Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("https://api.openweathermap.org/data/2.5/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Methods myapi = retrofit.create(Methods.class);
//        Call<Model> callcity = myapi.getweather(strCity.trim(),apikey);
//        callcity.enqueue(new Callback<Model>() {
//            @Override
//            public void onResponse(Call<Model> call, Response<Model> response) {
//                if((response.isSuccessful())){
//                    //Toast.makeText(MainActivity.this,response.code()+" CALL API SUCCESS",Toast.LENGTH_LONG).show();
//                    Model.Coordinates coord = response.body().getCoord();
//                    ArrayList<Model.WeatherInfo> weather = response.body().getWeather();
//                    String weather_icon = weather.get(0).getIcon();
//                    Model.WeatherInfo weatherInfo = weather.get(0);
//                    Model.Main main = response.body().getMain();
//                    Model.Sys sys = response.body().getSys();
//                    Model.Clouds clouds = response.body().getClouds();
//
//                    //Ten Thanh pho va quoc gia
//                    String nameCity = sys.getCountry();
//                    //Nhiệt độ
//                    Double nhiet = main.getTemp();
//                    Double nd = Double.valueOf(nhiet);
//                    Double temperature = (Double)(nd/10);
//                    String nhietDo = String.valueOf(temperature.intValue());
//                    txtNdo.setText(nhietDo);
//                    //nhiệt độ min
//                    String min = main.getTemp_min();
//                    Double nn = Double.valueOf(min);
//                    Integer nmin = (int)(nn/10);
//                    String nhietdomin = String.valueOf(nmin.intValue());
//                    //nhiệt độ max
//                    String max = main.getTemp_max();
//                    Double nm = Double.valueOf(max);
//                    Integer nmax =(int)(nm/10);
//                    String nhietdomax = String.valueOf(nmax.intValue());
//                    MaxnMin.setText(String.valueOf(nhietdomin +"°"+ nhietdomax +"°"));
//
//                    //Icon
//                    String img = weatherInfo.getIcon();
//                    Picasso.with(item_list_location.this).load("http://openweathermap.org/img/w/" + img + ".png").into(Anh);
//
//                } else if (!(response.isSuccessful())) {
//                    Toast.makeText(SaveLocation.this,response.code()+" ERROR CALL API",Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Model> call, Throwable t) {
//                Toast.makeText(SaveLocation.this,"ERROR", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }
    private void DeleteItem(CityModel cityModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SaveLocation.this);
        builder.setTitle("DELETE");
        builder.setMessage("Are you sure want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //deleteCity
                CityDatabase.getInstance(SaveLocation.this).cityDAO().deleteButton(cityModel);
                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                loadData();
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
    private void DeleteAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SaveLocation.this);
        builder.setTitle("DELETE");
        builder.setMessage("Are you sure want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //deleteCity
                CityDatabase.getInstance(SaveLocation.this).cityDAO().ClearAll();
                Toast.makeText(getApplicationContext(), "delete ALL", Toast.LENGTH_SHORT).show();
                loadData();
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
    private void bottomNagationView() {
        // tao bottom menu
        bottomNavigationView.setSelectedItemId(R.id.bottom_location);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_location) {
                return true;
            } else if (id == R.id.bottom_profile) {
                startActivity(new Intent(SaveLocation.this, Profile.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (id == R.id.bottom_home) {
                startActivity(new Intent(SaveLocation.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }

}