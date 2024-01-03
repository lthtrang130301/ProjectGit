package com.example.api.Weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.R;
import com.example.api.Weather.api.API_5DAY;
import com.example.api.Weather.api.RetrofitClient;
import com.example.api.Weather.model.day.TIME;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeDay extends AppCompatActivity {
    String urlDayly = "https://api.openweathermap.org/data/2.5/forecast?q=hanoi&units=metric&cnt=7&appid=53fbf527d52d4d773e828243b90c1f8e";
    String tempC = "metric", tempF = "imperial", days = "7", apikey = "e0ea7c2430957c0b90c7a6375a5f8cba";
    ListView listviewData;
    Button button;
    TextView CITY;
    EditText editTextText;
    ImageView btnBack;
    private String hour_converted;
    private String head_url = "http://openweathermap.org/img/w/";
    private String date_converted;
    private int dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_days);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorTransparent));

        CITY = findViewById(R.id.CITY);
//        editTextText = findViewById(R.id.editTextText);
//        button = findViewById(R.id.button);
        btnBack = findViewById(R.id.btnBack);
        listviewData = findViewById(R.id.listviewData);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        Intent intent = getIntent();
        String city = intent.getStringExtra("NAME");
        Show7day(city);

        //CITY.setText(city + " • 7 ngày");
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String city = editTextText.getText().toString().trim();
//                Show7day(city);
//            }
//        });


    }

    private void Show7day(String city) {

        API_5DAY api5DAY = RetrofitClient.getRetrofitInstance().create(API_5DAY.class);
        Call<TIME> call = api5DAY.getweather2(city, tempC, apikey);
        call.enqueue(new Callback<TIME>() {
            @Override
            public void onResponse(Call<TIME> call, Response<TIME> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TimeDay.this, response.code() + " CALL API", Toast.LENGTH_LONG).show();

                    ArrayList<TIME.Dailyh> data = response.body().getDailies();
                    String[] name = new String[data.size()];
                    SimpleDateFormat sp = new SimpleDateFormat("HH:mm EEEE yyyy-MM-dd");
//                    setTitle("Danh sach 5 ngay va gio");

                    for (int i = 0; i < data.size(); i++) {
                        name[i] = "\nNgày tháng : " + (sp.format(new Date((long)(data.get(i).dt) * 1000L)))
                                + "\nCảm giác như: " + data.get(i).main.getFeels_like()+"°C"
                                + "\nNhiệt độ : " + data.get(i).main.getTemp()+"°C"
                                + "\nMây: " + data.get(i).clouds.getAll()+"%" ;
                    }
                    listviewData.setAdapter(new ArrayAdapter < String > (getApplicationContext(), android.R.layout.simple_list_item_1,name));


                } else {
                    Toast.makeText(TimeDay.this, response.code() + " ERROR CALL API", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TIME> call, Throwable t) {
                Toast.makeText(TimeDay.this, " ERROR READ", Toast.LENGTH_LONG).show();
            }

        });
    }

    public String convertToHour(String timeZone, long dateUnix) {
        Date date = new Date(dateUnix * 1000); // Date object
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Format object
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        String hourFormatted = sdf.format(date); // Formatted date string
        hour_converted = hourFormatted;
        return hour_converted;
    }

    public String convertToDate(String timeZone, long dateUnix) {
        Date date = new Date(dateUnix * 1000); // Date object
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.ENGLISH); // Format object
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        String dateFormatted = sdf.format(date); // Formatted date string
        date_converted = dateFormatted;
        return date_converted;
    }

    public String convertToCelsius(double kelvin) {
        return Math.round(kelvin - 273.15) + "°";
    }
}