package ir.hadimohammadi.myapplication;

import static ir.hadimohammadi.myapplication.shekayatadapter.context;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class aboutt extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4,txt5,txt6;
    ImageView img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutt);


        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
       txt4=findViewById(R.id.txt4);
       txt5=findViewById(R.id.txt5);
       txt6=findViewById(R.id.txt6);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
      // img4=findViewById(R.id.img4);







        String url = "https://pgtab.info/user/SAJAP1.jpg";
        String url2 = "https://pgtab.info/user/hadi.jpg";

        Glide
                .with(aboutt.this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.sajaplogo)
                .into(img2);




            Glide
              .with(aboutt.this)
              .load(url2)
              .centerCrop()
              .placeholder(R.drawable.sajaplogo)
              .into(img3);

    }
}