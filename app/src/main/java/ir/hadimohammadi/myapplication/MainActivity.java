package ir.hadimohammadi.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
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

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView ahkam,  tashvigat, tanbih, shenasname, khabar, etelaat, tiket,update, exitee,personal,amozesh,takafol,about,rename,texxt;
    LottieAnimationView ahkamm,tanbihh,shenasnamee,khabarr,etelaatt,tikett,updatee,amozeshh,takafoll,aboutt,renamee,tashvigatt;
    ImageView image;
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    String hh, version;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ahkam = findViewById(R.id.ahkam);
        tashvigat = findViewById(R.id.tashvigat);
        tanbih = findViewById(R.id.tanbih);
        shenasname = findViewById(R.id.shenasname);
        khabar = findViewById(R.id.khabar);
        etelaat = findViewById(R.id.etelaat);
        tiket = findViewById(R.id.tiket);
        update = findViewById(R.id.update);
        ahkamm = findViewById(R.id.ahkamm);
        tanbihh = findViewById(R.id.tanbihh);
        shenasnamee = findViewById(R.id.shenasnamee);
       khabarr = findViewById(R.id.khabarr);
        etelaatt = findViewById(R.id.etelaatt);
        tikett = findViewById(R.id.tikett);
        updatee = findViewById(R.id.updatee);
        exitee = findViewById(R.id.exitee);
       // image = findViewById(R.id.image);
        personal = findViewById(R.id.personal);
        profile_image = findViewById(R.id.profile_image);
        amozeshh = findViewById(R.id.amozeshh);
        amozesh = findViewById(R.id.amozesh);
        takafoll = findViewById(R.id.takafoll);
        takafol = findViewById(R.id.takafol);
        aboutt = findViewById(R.id.aboutt);
        about = findViewById(R.id.about);
        renamee = findViewById(R.id.renamee);
        rename = findViewById(R.id.rename);
        tashvigatt = findViewById(R.id.tashvigatt);
        tashvigat = findViewById(R.id.tashvigat);

        version = "3";
        int Versionn = Integer.parseInt(version);
        chekVersion(Versionn);

        sp = getApplicationContext().getSharedPreferences("SAJAP", Context.MODE_PRIVATE);




        Toast.makeText(this, "کاربر عزیز " + sp.getString("Users_Name", "").toString() + " " + sp.getString("Users_FName", "") + " خوش آمدید", Toast.LENGTH_SHORT).show();
        // editor = sp.edit();

        if (sp.getString("Users_ID", "").equals("")) {
            finish();
            startActivity(new Intent(MainActivity.this, Login.class));
        }

        exitee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        Glide
                .with(MainActivity.this)
                .load(sp.getString("image", ""))
                .centerCrop()

                .into(profile_image);

          personal.setText(sp.getString("Users_Name", "")+" "+sp.getString("Users_FName", ""));

        ahkamm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ahkam", Toast.LENGTH_SHORT).show();
            }
        });
        tashvigatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "tashvigat", Toast.LENGTH_SHORT).show();
            }
        });
        tanbihh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "tanbih", Toast.LENGTH_SHORT).show();
            }
        });
        shenasnamee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "shenasname", Toast.LENGTH_SHORT).show();
            }
        });
        khabarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finishAffinity();
                startActivity(new Intent(MainActivity.this, tablo.class));
            }
        });
        tikett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, tiketha.class));
            }
        });
        updatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, update.class));
            }
        });
        etelaatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "etelaat", Toast.LENGTH_SHORT).show();
            }
        });
        renamee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "renamee", Toast.LENGTH_SHORT).show();
            }
        });
        amozeshh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "amozaesh", Toast.LENGTH_SHORT).show();
            }
        });
        takafoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "takafoll", Toast.LENGTH_SHORT).show();
            }
        });
        aboutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//c
                startActivity(new Intent(MainActivity.this, aboutt.class));
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    public void chekVersion(int id_V) {

        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();


        String url = "https://pgtab.info/Home/getv?id_V=" + id_V;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respo = new JSONObject(response);
                            String Status = respo.getString("v_now");


                            if (Status.equals("true")) {


                            } else {
                                finish();
                                startActivity(new Intent(MainActivity.this, update.class));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }



//ff

}
