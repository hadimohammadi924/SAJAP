package ir.hadimohammadi.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class tablo extends AppCompatActivity {

    RecyclerView ticketsListRecyclerView;
    TextView xtt2;
    PersianDate pdate = new PersianDate();
    PersianDateFormat pdformater1 = new PersianDateFormat("Y/m/d");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablo);
        tcast();



    }


    public void tcast() {

        xtt2 = findViewById(R.id.xtt2);


        SharedPreferences sharedPreferences = getSharedPreferences("SAJAP", Context.MODE_PRIVATE);
        xtt2.setText("امروز مورخ : "+pdformater1.format(pdate));
    }



    @Override
    public void onBackPressed() {
       finishAffinity();
        startActivity(new Intent(tablo.this, MainActivity.class));
    }

}