package ir.hadimohammadi.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

import org.json.JSONException;
import org.json.JSONObject;

public class update extends AppCompatActivity {

    int version2;
    Button updatee, exitt, mainpage;
    TextView updateVersion, now_version, updatetext, updated;
    LottieAnimationView updateee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        castupdate();
        version2 = 3;
        chekVersion(version2);


        updateee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue;
                Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
                Network network = new BasicNetwork(new HurlStack());
                requestQueue = new RequestQueue(cache, network);
                requestQueue.start();
                String url = "https://pgtab.info/Home/getv?id_V=" + version2;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject respoq = new JSONObject(response);
                                    String Status = respoq.getString("V_url");
                                    Uri uri = Uri.parse(Status);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
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
        });


        exitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                startActivity(new Intent(update.this, MainActivity.class));
            }
        });
    }

    public void chekVersion(int version2) {

        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();


        final String url = "https://pgtab.info/Home/getv?id_V=" + version2;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respo = new JSONObject(response);
                            String Status = respo.getString("v_now");
                            if (Status.equals("true")) {
                                updatetext.setText("برنامه شما بروز است.");
                                updateee.setVisibility(View.INVISIBLE);
                                updated.setVisibility(View.INVISIBLE);
                                exitt.setVisibility(View.INVISIBLE);
                                now_version.setText("نسخه فعلی برنامه نصب شده در گوشی شما: " + respo.getString("V_v"));
                                updateVersion.setText("ورژن بروز آماده بارگیری: " + respo.getString("xx1"));

                            } else {
                                updatetext.setText(respo.getString("date"));
                                mainpage.setVisibility(View.GONE);
                                now_version.setText("نسخه فعلی برنامه  در گوشی شما: " + respo.getString("V_v"));
                                updateVersion.setText("ورژن بروز آماده بارگیری: " + respo.getString("xx1"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(sabte_response.this, "نمیشه نمیدونم چرا", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);
    }

    public void castupdate() {
        updatee = findViewById(R.id.updatee);
        updateVersion = findViewById(R.id.updateVersion);
        now_version = findViewById(R.id.now_version);
        updatetext = findViewById(R.id.updatetext);
        exitt = findViewById(R.id.exitt);
        updateee = findViewById(R.id.updateee);
        updated = findViewById(R.id.updated);
        mainpage = findViewById(R.id.mainpage);


    }

    @Override
    public void onBackPressed() {
      //  no();
        yes();


    }


    public void no(){
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    };
    public void yes(){
       finishAffinity();
        startActivity(new Intent(update.this, MainActivity.class));
    };
}
