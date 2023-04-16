package ir.hadimohammadi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;


public class Splash extends AppCompatActivity {
ImageView imageView;
    LottieAnimationView animation_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
          imageView=findViewById(R.id.imageview);
         animation_view=findViewById(R.id.animation_view);
        //  pathView=findViewById(R.id.pathView);












        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
            }
        }, 3000);


    }


    @Override
    public void onBackPressed() {



    }






            }







