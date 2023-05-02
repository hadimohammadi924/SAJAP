package ir.hadimohammadi.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class aboutt extends AppCompatActivity {

    TextView txt1;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutt);


        txt1=findViewById(R.id.txt1);
        img1=findViewById(R.id.img1);




    }
}