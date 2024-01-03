package com.example.api.Weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.api.CityLike.SaveLocation;
import com.example.api.R;
import com.example.api.User.Profile;
import com.example.api.Weather.api.API_TIME;
import com.example.api.Weather.api.API_CURRENT;
import com.example.api.Weather.api.RetrofitClient;
import com.example.api.Weather.model.Clouds;
import com.example.api.Weather.model.Current;
import com.example.api.Weather.model.Main;
import com.example.api.Weather.model.Sys;
import com.example.api.Weather.model.day.TIME;
import com.example.api.Weather.model.WeatherInfo;
import com.example.api.Weather.model.Wind;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btLayThongTin;
    AppCompatButton btLay7day;
    EditText editdiadiem;
    TextView nhietdo, nhietdoMax, nhietdoMin, CityName, CamGiac, txtDoam, txtGio, txtMay, txtApsuat, txtDate;
    ImageView img_background;
    String url = "https://api.openweathermap.org/data/2.5/weather?q={city name}&units=metric&appid={api key}";
    String tempC = "metric", tempF = "imperial", days = "7", apikey = "e0ea7c2430957c0b90c7a6375a5f8cba";
    public static final String DEFAULT_CITY = "hanoi";
    private String link = "http://openweathermap.org/img/w/";
    ImageView icon1, icon2, icon3, icon4, icon5, icon6, icon7;
    TextView datetime1, nhietdo1, mota1, gio1, max1, min1, doam1;
    TextView datetime2, nhietdo2, mota2, gio2, max2, min2, doam2;
    TextView datetime3, nhietdo3, mota3, gio3, max3, min3, doam3;
    TextView datetime4, nhietdo4, mota4, gio4, max4, min4, doam4;
    TextView datetime5, nhietdo5, mota5, gio5, max5, min5, doam5;
    TextView datetime6, nhietdo6, mota6, gio6, max6, min6, doam6;
    TextView datetime7, nhietdo7, mota7, gio7, max7, min7, doam7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        init();
        ShowFindCity(DEFAULT_CITY);
        ShowHoursDay(DEFAULT_CITY);
        event();
//        Date dateCurrent = Calendar.getInstance().getTime();
//        String dateFormat = DateFormat.getDateInstance().format(dateCurrent);
//        txtDate.setText(dateFormat.toString());
    }

    private void init() {
        editdiadiem = findViewById(R.id.editdiadiem);
        btLayThongTin = findViewById(R.id.btLayThongTin);
        nhietdo = findViewById(R.id.nhietdo);
        CamGiac = findViewById(R.id.CamGiac);
        nhietdoMax = findViewById(R.id.nhietdoMax);
        nhietdoMin = findViewById(R.id.nhietdoMin);
        CityName = findViewById(R.id.CityName);
        img_background = findViewById(R.id.img_background);
        txtApsuat = findViewById(R.id.txtApsuat);
        txtMay = findViewById(R.id.txtMay);
        txtGio = findViewById(R.id.txtGio);
        txtDoam = findViewById(R.id.txtDoam);
        txtDate = findViewById(R.id.txtDate);
        btLay7day = findViewById(R.id.btLay7day);
        //GIO TRONG NGAY
        //day1
        datetime1 = findViewById(R.id.datetime1);
        nhietdo1 = findViewById(R.id.nhietdo1);
        mota1 = findViewById(R.id.mota1);
//        icon1 = findViewById(R.id.icon1);
        gio1 = findViewById(R.id.gio1);
        doam1 = findViewById(R.id.doam1);
        max1 = findViewById(R.id.max1);
        min1 = findViewById(R.id.min1);
        doam1 = findViewById(R.id.doam1);
        //day2
        datetime2 = findViewById(R.id.datetime2);
        nhietdo2 = findViewById(R.id.nhietdo2);
        mota2 = findViewById(R.id.mota2);
//        icon2 = findViewById(R.id.icon2);
        gio2 = findViewById(R.id.gio2);
        doam2 = findViewById(R.id.doam2);
        max2 = findViewById(R.id.max2);
        min2 = findViewById(R.id.min2);
        doam2 = findViewById(R.id.doam2);
        //day3
        datetime3 = findViewById(R.id.datetime3);
        nhietdo3 = findViewById(R.id.nhietdo3);
        mota3 = findViewById(R.id.mota3);
//        icon3 = findViewById(R.id.icon3);
        gio3 = findViewById(R.id.gio3);
        doam3 = findViewById(R.id.doam3);
        max3 = findViewById(R.id.max3);
        min3 = findViewById(R.id.min3);
        doam3 = findViewById(R.id.doam3);
        //day4
        datetime4 = findViewById(R.id.datetime4);
        nhietdo4 = findViewById(R.id.nhietdo4);
        mota4 = findViewById(R.id.mota4);
//        icon4 = findViewById(R.id.icon4);
        gio4 = findViewById(R.id.gio4);
        doam4 = findViewById(R.id.doam4);
        max4 = findViewById(R.id.max4);
        min4 = findViewById(R.id.min4);
        doam4 = findViewById(R.id.doam4);
        //day5
        datetime5 = findViewById(R.id.datetime5);
        nhietdo5 = findViewById(R.id.nhietdo5);
        mota5 = findViewById(R.id.mota5);
//        icon5 = findViewById(R.id.icon5);
        gio5 = findViewById(R.id.gio5);
        doam5 = findViewById(R.id.doam5);
        max5 = findViewById(R.id.max5);
        min5 = findViewById(R.id.min5);
        doam5 = findViewById(R.id.doam5);
        //day6
        datetime6 = findViewById(R.id.datetime6);
        nhietdo6 = findViewById(R.id.nhietdo6);
        mota6 = findViewById(R.id.mota6);
//        icon6 = findViewById(R.id.icon6);
        gio6 = findViewById(R.id.gio6);
        doam6 = findViewById(R.id.doam6);
        max6 = findViewById(R.id.max6);
        min6 = findViewById(R.id.min6);
        doam6 = findViewById(R.id.doam6);
        //day7
        datetime7 = findViewById(R.id.datetime7);
        nhietdo7 = findViewById(R.id.nhietdo7);
        mota7 = findViewById(R.id.mota7);
//        icon7 = findViewById(R.id.icon7);
        gio7 = findViewById(R.id.gio7);
        doam7 = findViewById(R.id.doam7);
        max7 = findViewById(R.id.max7);
        min7 = findViewById(R.id.min7);
        doam7 = findViewById(R.id.doam7);


    }

    private void event() {
        btLayThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editdiadiem.getText().toString().trim();
                ShowFindCity(city);
                ShowHoursDay(city);

            }
        });

        btLay7day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editdiadiem.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, TimeDay.class);
                intent.putExtra("NAME", city);
                startActivity(intent);
            }
        });

//        //thay doi kieu nhiet do
//        MaterialButton btnunits = findViewById(R.id.btnunits);
//        PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnunits);
//        popupMenu.getMenuInflater().inflate(R.menu.units_menu, popupMenu.getMenu());
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int id = item.getItemId();
//                if(id== R.id.metric){
//
//                }
//                else if(id == R.id.imperial) {
//
//                }
//                return false;
//            }
//        });

// tao bottom menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_home) {
                return true;
            } else if (id == R.id.bottom_location) {
                startActivity(new Intent(MainActivity.this, SaveLocation.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (id == R.id.bottom_profile) {
                startActivity(new Intent(MainActivity.this, Profile.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }

    private void ShowFindCity(String city) {
        API_CURRENT methods = RetrofitClient.getRetrofitInstance().create(API_CURRENT.class);
        Call<Current> call = methods.getweather(city.trim(), tempC, apikey);
        call.enqueue(new Callback<Current>() {
            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {
                if ((response.isSuccessful())) {
                    //Toast.makeText(MainActivity.this,response.code()+" CALL API SUCCESS",Toast.LENGTH_LONG).show();
                    Current current = response.body();
                    ArrayList<WeatherInfo> weather = response.body().getWeather();
                    String weather_type = weather.get(0).getMain();
                    String weather_icon = weather.get(0).getIcon();
                    WeatherInfo weatherInfo = weather.get(0);
                    Main main = response.body().getMain();
                    Sys sys = response.body().getSys();
                    Wind wind = response.body().getWind();
                    Clouds clouds = response.body().getClouds();

                    switch (weather_type) {
                        case "Thunderstorm":
                            if (weather_icon.contains("d")) {
                                img_background.setImageResource(R.drawable.img_thunderstorm_day);
                            } else
                                img_background.setImageResource(R.drawable.img_thunderstorm_night);
                            break;

                        case "Mist":
                            if (weather_icon.contains("d")) {
                                img_background.setImageResource(R.drawable.img_mist_day);
                            } else
                                img_background.setImageResource(R.drawable.img_mist_night);
                            break;
                        case "Clouds":
                            if (weather_icon.contains("d")) {
                                img_background.setImageResource(R.drawable.img_clouds_day);
                            } else
                                img_background.setImageResource(R.drawable.img_clouds_night);
                            break;

                        case "Drizzle":
                            if (weather_icon.contains("d")) {
                                img_background.setImageResource(R.drawable.img_drizzle_day);
                            } else
                                img_background.setImageResource(R.drawable.img_drizzle_night);
                            break;

                        case "Rain":
                            if (weather_icon.contains("d")) {
                                img_background.setImageResource(R.drawable.img_rain_day);
                            } else
                                img_background.setImageResource(R.drawable.img_rain_night);
                            break;

                        case "Snow":
                            if (weather_icon.contains("d")) {
                                img_background.setImageResource(R.drawable.img_winter_day);
                            } else
                                img_background.setImageResource(R.drawable.img_winter_night);
                            break;

                        case "Atmosphere":
                            img_background.setImageResource(R.drawable.atmosphere);
                            break;

                        case "Clear":
                            if (weather_icon.contains("d")) {
                                img_background.setImageResource(R.drawable.img_sky_clearly_day);
                            } else
                                img_background.setImageResource(R.drawable.img_sky_clearly_night);
                            break;
                    }

                    long l = Long.valueOf(current.getDt());
                    Date date = new Date(l * 1000L);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-YYYY", Locale.ENGLISH);
                    txtDate.setText(String.valueOf(simpleDateFormat.format(date)));

                    //Ten Thanh pho va quoc gia
                    String nameCity = sys.getCountry();
                    CityName.setText(String.valueOf(city + ", " + nameCity));

                    //Nhiệt độ
                    Double temperature = (Double.valueOf(main.getTemp()));
                    String nhietDo = String.valueOf(temperature.intValue());
                    nhietdo.setText(nhietDo);

                    //nhiệt độ min
                    Double nn = Double.valueOf(main.getTemp_min());
                    String nhietdomin = String.valueOf(nn.intValue());
                    nhietdoMin.setText(String.valueOf(nhietdomin + "°"));

                    //nhiệt độ max
                    Double nm = Double.valueOf(main.getTemp_max());
                    String nhietdomax = String.valueOf(nm.intValue());
                    nhietdoMax.setText(String.valueOf(nhietdomax + "°"));

                    //cảm giác như và mô tả
                    Double fl = Double.valueOf(main.getFeels_like());
                    String camgiac = String.valueOf(fl.intValue());
                    String description = weatherInfo.getDescription();
                    CamGiac.setText(String.valueOf("Feel like " + camgiac + "°C, " + description));

                    //Độ ẩm
                    String humidity = main.getHumidity();
                    txtDoam.setText(String.valueOf(humidity + "%"));
                    //Tốc độ gió
                    String winds = wind.getSpeed();
                    txtGio.setText(String.valueOf(winds + "m/s"));
                    //Phần trăm mây
                    String cloud = clouds.getAll();
                    txtMay.setText(String.valueOf(cloud + "%"));
                    //Áp suất
                    String apsuat = main.getPressure();
                    txtApsuat.setText(String.valueOf(apsuat + "hPa"));
//                    Icon
//                    String img = weatherInfo.getIcon();
//                    Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + img + ".png").into(Anh);

                } else if (!(response.isSuccessful())) {
                    Toast.makeText(MainActivity.this, response.code() + " ERROR CALL API", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void ShowHoursDay(String city) {

        API_TIME apiTime = RetrofitClient.getRetrofitInstance().create(API_TIME.class);
        Call<TIME> call = apiTime.getweather1(city.trim(), tempC, days, apikey);
        call.enqueue(new Callback<TIME>() {
            @Override
            public void onResponse(Call<TIME> call, Response<TIME> response) {
//                        String weather_icon = weather.get(0).getIcon();
//                        String weather_type = weather.get(0).getMain();
//                        String[] names = new String[data.size()];

//                        for (int i = 0; i < data.size(); i++) {
//                            names[i] = "nd : " + data.get(1).main.temp + "\ncamgiac : " + data.get(1).main.feels_like
//                                    + "\nmay " + data.get(1).clouds.all+ "\ndt : " + data.get(1).dt;
//                        }
//                        listView.setAdapter(new ArrayAdapter < String > (getApplicationContext(), android.R.layout.simple_list_item_1,names));
                if ((response.isSuccessful())) {
                TIME week = response.body();
                ArrayList<TIME.Dailyh> dailyList = week.getDailies();
                TIME.Dailyh today = dailyList.get(0);

                ArrayList<TIME.WeatherInfo> weather = today.weather;
                TIME.WeatherInfo weatherInfo = weather.get(0);
                String weather_icon = weatherInfo.getIcon();
                String weather_type = weatherInfo.getMain();

                //Show do am
                doam1.setText(String.valueOf(week.getDailies().get(0).main.getHumidity()));
                doam2.setText(String.valueOf(week.getDailies().get(1).main.getHumidity()));
                doam3.setText(String.valueOf(week.getDailies().get(2).main.getHumidity()));
                doam4.setText(String.valueOf(week.getDailies().get(3).main.getHumidity()));
                doam5.setText(String.valueOf(week.getDailies().get(4).main.getHumidity()));
                doam6.setText(String.valueOf(week.getDailies().get(5).main.getHumidity()));
                doam7.setText(String.valueOf(week.getDailies().get(6).main.getHumidity()));
                //Show nhiet do
                nhietdo1.setText(String.valueOf(week.getDailies().get(0).main.getTemp()) + "°");
                nhietdo2.setText(String.valueOf(week.getDailies().get(1).main.getTemp()) + "°");
                nhietdo3.setText(String.valueOf(week.getDailies().get(2).main.getTemp()) + "°");
                nhietdo4.setText(String.valueOf(week.getDailies().get(3).main.getTemp()) + "°");
                nhietdo5.setText(String.valueOf(week.getDailies().get(4).main.getTemp()) + "°");
                nhietdo6.setText(String.valueOf(week.getDailies().get(5).main.getTemp()) + "°");
                nhietdo7.setText(String.valueOf(week.getDailies().get(6).main.getTemp()) + "°");
                //Show toc do gio
                gio1.setText(String.valueOf(week.getDailies().get(0).wind.getSpeed()));
                gio2.setText(String.valueOf(week.getDailies().get(1).wind.getSpeed()));
                gio3.setText(String.valueOf(week.getDailies().get(2).wind.getSpeed()));
                gio4.setText(String.valueOf(week.getDailies().get(3).wind.getSpeed()));
                gio5.setText(String.valueOf(week.getDailies().get(4).wind.getSpeed()));
                gio6.setText(String.valueOf(week.getDailies().get(5).wind.getSpeed()));
                gio7.setText(String.valueOf(week.getDailies().get(6).wind.getSpeed()));
                //Show nhiet do max
                max1.setText(String.valueOf(week.getDailies().get(0).main.getTemp_max()));
                max2.setText(String.valueOf(week.getDailies().get(1).main.getTemp_max()));
                max3.setText(String.valueOf(week.getDailies().get(2).main.getTemp_max()));
                max4.setText(String.valueOf(week.getDailies().get(3).main.getTemp_max()));
                max5.setText(String.valueOf(week.getDailies().get(4).main.getTemp_max()));
                max6.setText(String.valueOf(week.getDailies().get(5).main.getTemp_max()));
                max7.setText(String.valueOf(week.getDailies().get(6).main.getTemp_max()));
                //Show nhiet do min
                min1.setText(String.valueOf(week.getDailies().get(0).main.getTemp_min()));
                min2.setText(String.valueOf(week.getDailies().get(1).main.getTemp_min()));
                min3.setText(String.valueOf(week.getDailies().get(2).main.getTemp_min()));
                min4.setText(String.valueOf(week.getDailies().get(3).main.getTemp_min()));
                min5.setText(String.valueOf(week.getDailies().get(4).main.getTemp_min()));
                min6.setText(String.valueOf(week.getDailies().get(5).main.getTemp_min()));
                min7.setText(String.valueOf(week.getDailies().get(6).main.getTemp_min()));
                //Show ngay thang
                //day 1
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
                String Day1 = simpleDateFormat1.format(new Date((long) (week.getDailies().get(0).dt) * 1000L));
                datetime1.setText(String.valueOf(Day1));
                //day2
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                String Day2 = simpleDateFormat2.format(new Date((long) (week.getDailies().get(1).dt) * 1000L));
                datetime2.setText(String.valueOf(Day2));
                //day3
                SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm ");
                String Day3 = simpleDateFormat3.format(new Date((long) (week.getDailies().get(2).dt) * 1000L));
                datetime3.setText(String.valueOf(Day3));
                //day4
                SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat(" HH:mm ");
                String Day4 = simpleDateFormat4.format(new Date((long) (week.getDailies().get(3).dt) * 1000L));
                datetime4.setText(String.valueOf(Day4));
                //day5
                SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("HH:mm ");
                String Day5 = simpleDateFormat5.format(new Date((long) (week.getDailies().get(4).dt) * 1000L));
                datetime5.setText(String.valueOf(Day5));
                //day 6
                SimpleDateFormat simpleDateFormat6 = new SimpleDateFormat("HH:mm ");
                String Day6 = simpleDateFormat6.format(new Date((long) (week.getDailies().get(5).dt) * 1000L));
                datetime6.setText(String.valueOf(Day6));
                //day7
                SimpleDateFormat simpleDateFormat7 = new SimpleDateFormat("HH:mm");
                String Day7 = simpleDateFormat7.format(new Date((long) (week.getDailies().get(6).dt) * 1000L));
                datetime7.setText(String.valueOf(Day7));
                //Show icon
                //Picasso.with(MainActivity.this).load(link +  weather_icon + ".png").into(icon1);
//                        Picasso.with(MainActivity.this).load(link +  week.getDailies().get(1).weather.get(1).getIcon() + ".png").into(icon2);
//                        Picasso.with(MainActivity.this).load(link +  week.getDailies().get(2).weather.get(2).getIcon() + ".png").into(icon3);
//                        Picasso.with(MainActivity.this).load(link +  week.getDailies().get(3).weather.get(3).getIcon() + ".png").into(icon4);
//                        Picasso.with(MainActivity.this).load(link +  week.getDailies().get(4).weather.get(4).getIcon() + ".png").into(icon5);
//                        Picasso.with(MainActivity.this).load(link +  week.getDailies().get(5).weather.get(5).getIcon() + ".png").into(icon6);
//                        Picasso.with(MainActivity.this).load(link +  week.getDailies().get(6).weather.get(6).getIcon() + ".png").into(icon7);
                //Show mota
                mota1.setText(String.valueOf(weather.get(0).getDescription()));
//                        mota2.setText(String.valueOf( weather.get(1).description));
//                        mota3.setText(String.valueOf(week.getDailies().get(2).weather.get(2).description));
//                        mota4.setText(String.valueOf(week.getDailies().get(3).weather.get(3).description));
//                        mota5.setText(String.valueOf(week.getDailies().get(4).weather.get(4).description));
//                        mota6.setText(String.valueOf(week.getDailies().get(5).weather.get(5).description));
//                        mota7.setText(String.valueOf(week.getDailies().get(6).weather.get(6).description));

                } else if (!(response.isSuccessful())) {
                    Toast.makeText(MainActivity.this, response.code() + " ERROR CALL API", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<TIME> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

}