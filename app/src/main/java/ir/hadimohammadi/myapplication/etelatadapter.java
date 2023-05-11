package ir.hadimohammadi.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class etelatadapter extends RecyclerView.Adapter<etelatadapter.ContactViewHolder> {

    Context mConext;
    public SharedPreferences sp;
    private List<etelaat> etelatList;
    public static Activity context;

    static etelatadapter etelatAdapterInterface;

    public etelatadapter(ArrayList<etelaat> etelatlist, etelatadapter.etelatAdapterInterface etelatAdapterInterface) {
        this.etelatList = etelatlist;
        this.etelatAdapterInterface = (etelatadapter) etelatAdapterInterface;
//
    }


    public interface etelatAdapterInterface {

        void onCustomListitemClick(int position);
    }

    public etelatadapter(List<etelaat> etelatList, Activity activity) {
        this.etelatList = etelatList;
        context = activity;

    }


    @Override
    public int getItemCount() {
        return etelatList.size();
    }


    @Override
    public void onBindViewHolder(etelatadapter.ContactViewHolder contactViewHolder, @SuppressLint("RecyclerView") int i) {
        //   SharedPreferences sp = mConext.getApplicationContext().getSharedPreferences("SAJAP", 0);
        //   sp.getString("post","");
        //   Toast.makeText(mConext,  sp.getString("post",""), Toast.LENGTH_SHORT).show();

        if (etelatList.get(i).getType().equals("1")) {

            contactViewHolder.typ.setText(etelatList.get(i).getType());
            contactViewHolder.eteladate.setText("اطلاعیه مورخه :" + etelatList.get(i).getDate());
            contactViewHolder.etelatitle.setText("عنوان: " + etelatList.get(i).getType1());
            contactViewHolder.etelaperiod.setText(etelatList.get(i).getType2());
            contactViewHolder.type3.setText("حوزه شکایت: " + etelatList.get(i).getType3());
            contactViewHolder.type4.setText("ثبت شده توسط:" + etelatList.get(i).getType4());
            ContactViewHolder.tabb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(mConext,contactViewHolder.eteladate.getText().;
                    // Toast.makeText(context, contactViewHolder.etelaperiod.getText().toString(), Toast.LENGTH_SHORT).show();
                    tablo.tabbhq.setVisibility(View.VISIBLE);
                    tablo.typeeezq.setText(contactViewHolder.etelaperiod.getText());


                }
            });
            contactViewHolder.showw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String o=etelatList.get(i).getType3();

                    tablo.getitqq.setVisibility(View.VISIBLE);
                    tablo.picc.setVisibility(View.VISIBLE);
                    Glide
                            .with(context)
                          //  .load(contactViewHolder.type3.toString())
                           // .load("https://pgtab.info/user/hadi.jpg")
                            .load(o)
                            .centerCrop()

                            .into(tablo.picc);

                }
            });
        } else {
            ContactViewHolder.loggo.setVisibility(View.GONE);
            contactViewHolder.eteladate.setText("اطلاعیه مورخه :" + etelatList.get(i).getDate());
            contactViewHolder.typ.setText(etelatList.get(i).getType());
            contactViewHolder.etelatitle.setText("عنوان: " + etelatList.get(i).getType1());
            contactViewHolder.etelaperiod.setText(etelatList.get(i).getType2());
            contactViewHolder.type3.setText("حوزه شکایت: " + etelatList.get(i).getType3());
            contactViewHolder.type4.setText("ثبت شده توسط:" + etelatList.get(i).getType4());

            ContactViewHolder.tabb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tablo.tabbhq.setVisibility(View.VISIBLE);
                    tablo.typeeezq.setText(contactViewHolder.etelaperiod.getText());
                    // contactViewHolder.typeeez.setText(contactViewHolder.etelaperiod.getText().toString());
                    //  Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                    //  ContactViewHolder.tabbh.setVisibility(View.VISIBLE);
                    //  Toast.makeText(context, "4", Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(mConext,contactViewHolder.eteladate.getText().;
                    //   Toast.makeText(context, contactViewHolder.eteladate.getText().toString(), Toast.LENGTH_SHORT).show();


                    //    contactViewHolder.typeee.setVisibility(View.VISIBLE);

                }
            });


        }

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.etelaatcard, viewGroup, false);
        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {


        static CardView tabb, loggo, tabbh;
        TextView eteladate, etelatitle, etelaperiod, type3, type4, typeeez, typ;
        LottieAnimationView maill, showw;
        Button getit;

        public ContactViewHolder(final View v) {
            super(v);


            eteladate = v.findViewById(R.id.eteladate);
            etelatitle = v.findViewById(R.id.etelatitle);
            etelaperiod = v.findViewById(R.id.etelaperiod);
            type3 = v.findViewById(R.id.type3);
            type4 = v.findViewById(R.id.type4);
            maill = v.findViewById(R.id.maill);
            showw = v.findViewById(R.id.showw);
            loggo = v.findViewById(R.id.loggo);
            tabb = v.findViewById(R.id.tabb);
            //  typeeez = v.findViewById(R.id.typeeez);
            //  getit = v.findViewById(R.id.getit);
            //  tabbh = v.findViewById(R.id.tabbh);
            typ = v.findViewById(R.id.typ);


        }


    }


}








