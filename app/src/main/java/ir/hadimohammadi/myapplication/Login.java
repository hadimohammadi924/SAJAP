package ir.hadimohammadi.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class Login extends AppCompatActivity {

    PersianDate pdate = new PersianDate();
    PersianDateFormat pdformater1 = new PersianDateFormat("Y/m/d***H:i");



    String userpersonal;

    TextInputEditText etNumber;
    Button submitButton, sencode;
    LinearLayout codeLayout, one;
    public static String Code = "";
    public static EditText et4;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
TextView resend;
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 60000; // 10 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etNumber = findViewById(R.id.etNumber);
        submitButton = findViewById(R.id.submitButton);
        codeLayout = findViewById(R.id.codeLayout);
        sencode = findViewById(R.id.sendcode);
        one = findViewById(R.id.one);
        countdownText = findViewById(R.id.countdownText);
        resend = findViewById(R.id.resend);




        et4 = findViewById(R.id.et4);
        sp = getApplicationContext().getSharedPreferences("SAJAP", Context.MODE_PRIVATE);
        editor = sp.edit();


        et4.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (et4.length() == 4) {
                    sencode.setEnabled(true);
                    sencode.setBackgroundResource(R.color.b9);
                } else {
                    sencode.setBackgroundResource(R.color.b11);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (et4.length() == 4) {
                    sencode.setEnabled(true);
                    sencode.setBackgroundResource(R.color.b9);
                } else {
                    sencode.setBackgroundResource(R.color.b11);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et4.length() == 4) {
                    sencode.setEnabled(true);
                    sencode.setBackgroundResource(R.color.b9);
                } else {
                    sencode.setBackgroundResource(R.color.b11);
                }
            }
        });
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (etNumber.length() == 6) {
                    submitButton.setEnabled(true);
                    submitButton.setBackgroundResource(R.color.b9);

                } else {
                    submitButton.setBackgroundResource(R.color.white);
                    submitButton.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etNumber.length() == 6) {
                    submitButton.setEnabled(true);
                    submitButton.setBackgroundResource(R.color.b9);

                } else {
                    submitButton.setEnabled(false);
                    submitButton.setBackgroundResource(R.color.white);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etNumber.length() == 6) {
                    submitButton.setEnabled(true);
                    submitButton.setBackgroundResource(R.color.b9);

                } else {
                    submitButton.setEnabled(false);
                    submitButton.setBackgroundResource(R.color.white);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNumber.length() == 6) {
                    one.setVisibility(View.VISIBLE);
                    login();
                    startTimer();
                } else {
                    Toast.makeText(Login.this, "کد پرسنلی خود را صحیح وارد کنید", Toast.LENGTH_SHORT).show();
                }
            }

        });
        sencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCode(userpersonal, et4.getText().toString());
            }
        });
      resend.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              resend.setVisibility(View.GONE);
              login();
               timeLeftInMilliseconds = 60000;
              startTimer();
          }
      });
    }




    public void checkCode(String userpersonal, String Code) {

        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "https://pgtab.ir/home/ScheckCode?personalcode=" + userpersonal + "&code=" + Code;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respo = new JSONObject(response);
                            String statue = respo.getString("statue");

                            if (statue.equals("CodeExpired")) {
                                Toast.makeText(Login.this, "کد منقضی شده", Toast.LENGTH_SHORT).show();
                            } else if (statue.equals("Success")) {
                                Toast.makeText(Login.this, respo.toString(), Toast.LENGTH_SHORT).show();
                              //  Toast.makeText(Login.this, respo, Toast.LENGTH_SHORT).show();
                                editor.putString("Users_IDD", respo.getString("idd"));
                                editor.putString("Users_ID", respo.getString("id"));
                                editor.putString("Users_Name", respo.getString("name"));
                                editor.putString("Users_FName", respo.getString("fname"));
                                editor.putString("image", respo.getString("pic"));
                                editor.apply();
                                final String hadi=respo.getString("idd");
                                insertDevise(Integer.parseInt(hadi));
                                startActivity(new Intent(Login.this, MainActivity.class));
                                Toast.makeText(Login.this, hadi, Toast.LENGTH_SHORT).show();

                            } else if (statue.equals("InvalidCode")) {
                                Toast.makeText(Login.this, "کد وارد شده اشتباه هست", Toast.LENGTH_SHORT).show();
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


    public void login() {


        userpersonal = etNumber.getText().toString();
        etNumber.setEnabled(false);
        submitButton.setEnabled(false);

        etNumber.setAlpha(0.5f);
        submitButton.setAlpha(0.5f);
        codeLayout.setVisibility(View.VISIBLE);
        // sendcodec.setVisibility(View.VISIBLE);

        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "https://pgtab.ir/home/SLogin?personalcode=" + userpersonal;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Login.this, "کد ارسالی را در کادر زیر وارد کنید", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Login.this, "مشکلی در ارتباط با اینترنت بوجود آمده مجددا تلاش کنین", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);
    }
/*
    public  void hhh(String msgBody, String msgBodys) {
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "https://pgtab.ir/home/PMP?sendern=" + msgBody + "&text=" + msgBodys;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //   Toast.makeText(Login.this, "کد ارسالی را در کادر زیر وارد کنید", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //        Toast.makeText(Login.this, "مشکلی در ارتباط با اینترنت بوجود آمده مجددا تلاش کنین", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);


    }


 */

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                countdownText.setText("--:--");
                resend.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMilliseconds / 1000) / 60;
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownText.setText(timeLeftFormatted);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public  void insertDevise(int userid){
      //  String userid=sp.getString("Users_ID", "");
        String lastupdate=(pdformater1.format(pdate));
        String Devise_name=android.os.Build.MODEL;
        String Devise_ID=Settings.Secure.getString(Login.this.getContentResolver(), Settings.Secure.ANDROID_ID);
      //  String token=Settings.Secure.getString(Login.this.getContentResolver(), Settings.Secure.ANDROID_ID);

        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "https://pgtab.ir/home/update_lastupdate?suserid="+userid+"&lastupdate="+lastupdate+"&Devise_name="+Devise_name+"&Devise_ID="+Devise_ID;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsee) {
                        Toast.makeText(Login.this, responsee, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "2", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);

    }
}

