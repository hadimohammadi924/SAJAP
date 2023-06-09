package ir.hadimohammadi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

public class etelatesazmani extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etelatesazmani);
        sp = getApplicationContext().getSharedPreferences("SAJAP", Context.MODE_PRIVATE);
  //      Toast.makeText(this, sp.getString("Users_IDD",""), Toast.LENGTH_SHORT).show();
  //    Toast.makeText(this, sp.getString("post",""), Toast.LENGTH_SHORT).show();
  //    Toast.makeText(this, sp.getString("Users_ID",""), Toast.LENGTH_SHORT).show();
       // sdata(Integer.valueOf(sp.getString("Users_ID","")));

        sdata( );



    }

    public void sdata( ){
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "https://pgtab.ir/Home/SDATA?d_personalcode=" + sp.getString("Users_ID","");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respo = new JSONObject(response);
                            Toast.makeText(etelatesazmani.this, respo.getString("madrak"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(etelatesazmani.this, "2", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(etelatesazmani.this, "3", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);
    }



    };
