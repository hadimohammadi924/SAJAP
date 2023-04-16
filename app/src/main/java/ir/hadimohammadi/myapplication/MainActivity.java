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

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView ahkam,  tashvigat, tanbih, shenasname, khabar, etelaat, tiket,update, exitee,personal,amozesh,takafol,about,rename,texxt;
    LottieAnimationView ahkamm,tanbihh,shenasnamee,khabarr,etelaatt,tikett,updatee,amozeshh,takafoll,aboutt,renamee,tashvigatt;
    ImageView image;
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
String hadi="hadi";

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
      //  Toast.makeText(this, sp.getString("pic",""), Toast.LENGTH_SHORT).show();
        Glide
                .with(MainActivity.this)
                .load(sp.getString("image", ""))
                .centerCrop()

                .into(profile_image);

          personal.setText(sp.getString("Users_Name", "")+" "+sp.getString("Users_FName", ""));










    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }








}