package ir.hadimohammadi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class etelatesazmani extends AppCompatActivity {
    SharedPreferences sp;
    TextView personall,codemelli,codemellii,shenasname,shenasnamee,tell,telll,mobile,mobilee,madrakk,madrak,reshtetahsili,reshtetahsilii,mahalesodorr,mahalesodor,
            saletavalodee,saletavalode,address,addresss,dadnamee,dadname,shomarehesab,shomarehesabb,shaba,shabaa;
    CircleImageView profile_imagee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etelatesazmani);
        sp = getApplicationContext().getSharedPreferences("SAJAP", Context.MODE_PRIVATE);
        personall = findViewById(R.id.personall);
        profile_imagee = findViewById(R.id.profile_imagee);
        codemelli = findViewById(R.id.codemelli);
        codemellii = findViewById(R.id.codemellii);
        shenasname = findViewById(R.id.shenasname);
        shenasnamee = findViewById(R.id.shenasnamee);
        tell = findViewById(R.id.tell);
        telll = findViewById(R.id.telll);
        mobile = findViewById(R.id.mobile);
        mobilee = findViewById(R.id.mobilee);
        madrakk = findViewById(R.id.madrakk);
        madrak = findViewById(R.id.madrak);
        reshtetahsili = findViewById(R.id.reshtetahsili);
        reshtetahsilii = findViewById(R.id.reshtetahsilii);
        mahalesodorr = findViewById(R.id.mahalesodorr);
        mahalesodor = findViewById(R.id.mahalesodor);
        saletavalode = findViewById(R.id.saletavalode);
        saletavalodee = findViewById(R.id.saletavalodee);
        addresss = findViewById(R.id.addresss);
        address = findViewById(R.id.address);
        dadnamee = findViewById(R.id.dadnamee);
        dadname = findViewById(R.id.dadname);
        shomarehesab = findViewById(R.id.shomarehesab);
        shomarehesabb = findViewById(R.id.shomarehesabb);
        shabaa = findViewById(R.id.shabaa);
        shaba = findViewById(R.id.shaba);

    Glide
            .with(etelatesazmani.this)
            .load(sp.getString("image", ""))
            .centerCrop()

            .into(profile_imagee);

   personall.setText(sp.getString("Users_Name", "") + " " + sp.getString("Users_FName", ""));
   /*
        telll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno="09360610067";


                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse(phoneno)));
            }
        });


    */
        sdata();


    }

    public void sdata() {
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "https://pgtab.ir/Home/SDATA?d_personalcode=" + sp.getString("Users_ID", "");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respo = new JSONObject(response);
                            codemellii.setText(respo.getString("codemelli"));
                            shenasnamee.setText(respo.getString("shenasname"));
                            telll.setText(respo.getString("tell"));
                            mobilee.setText(respo.getString("mobile"));
                            madrakk.setText(respo.getString("madrak"));
                            reshtetahsilii.setText(respo.getString("reshtetahsili"));
                            mahalesodorr.setText(respo.getString("mahalesodor"));
                            addresss.setText(respo.getString("address"));
                            dadnamee.setText(respo.getString("dadname"));
                            shomarehesabb.setText(respo.getString("shomarehesab"));
                            shabaa.setText(respo.getString("shaba"));
                            saletavalodee.setText(respo.getString("saletavalode")+"/"+respo.getString("mahetavalode")+"/"+respo.getString("rozetavalod"));





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


    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(etelatesazmani.this, MainActivity.class));
    }


};
