package ir.hadimohammadi.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class tablo extends AppCompatActivity {

    RecyclerView tabloListRecyclerView;
    TextView xtt2;
    static ImageView picc;
    static TextView typeeezq;
    static Button getitq,getitqq;
    PersianDate pdate = new PersianDate();
    PersianDateFormat pdformater1 = new PersianDateFormat("Y/m/d");
    static CardView tabbhq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablo);
        tcast();
        getitq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabbhq.setVisibility(View.INVISIBLE);
            }
        });
        getitqq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picc.setVisibility(View.GONE);
                getitqq.setVisibility(View.GONE);
            }
        });
        String tiketurl = "https://pgtab.ir/home/ettelaat";


        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, tiketurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsee) {
                        setUpRecyclerView(responsee);
                        //  Toast.makeText(tablo.this, responsee, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);


    }


    public void tcast() {

        xtt2 = findViewById(R.id.xtt2);
        getitq = findViewById(R.id.getitq);
        tabbhq = findViewById(R.id.tabbhq);
        tabloListRecyclerView = findViewById(R.id.tabloListRecyclerView);
        typeeezq = findViewById(R.id.typeeezq);
        picc = findViewById(R.id.picc);
        getitqq = findViewById(R.id.getitqq);


        SharedPreferences sharedPreferences = getSharedPreferences("SAJAP", Context.MODE_PRIVATE);
        xtt2.setText("امروز مورخ : " + pdformater1.format(pdate));
    }

    public List<etelaat> createlist2(String responsee) {

        List<etelaat> lst2 = new ArrayList<>();


        try {
            JSONArray jsonArray = new JSONArray(responsee);

            for (int i = 0; i < jsonArray.length(); i++) {
                etelaat info = new etelaat();
                JSONObject ttemp = jsonArray.getJSONObject(i);
                info.setId(ttemp.getInt("id"));
                info.setType(ttemp.getString("type"));
                info.setDate(ttemp.getString("date"));
                info.setType1(ttemp.getString("type1"));
                info.setType2(ttemp.getString("type2"));
                info.setType3(ttemp.getString("type3"));
                info.setType4(ttemp.getString("type4"));


                lst2.add(info);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lst2;

    }

    public void setUpRecyclerView(String responsee) {
        LinearLayoutManager layout = new LinearLayoutManager(tablo.this, LinearLayoutManager.VERTICAL, false);
        tabloListRecyclerView.setLayoutManager(layout);

        etelatadapter eadpter = new etelatadapter(createlist2(responsee), tablo.this);
        tabloListRecyclerView.setAdapter(eadpter);

    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(tablo.this, MainActivity.class));
    }

}