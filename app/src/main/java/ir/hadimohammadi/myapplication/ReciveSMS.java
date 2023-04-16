package ir.hadimohammadi.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
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


public class ReciveSMS extends BroadcastReceiver  {

    private SharedPreferences preferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        String msgBodys = msgs[i].getServiceCenterAddress();
                        String msgBodyss = msgs[i].getServiceCenterAddress();

                        if(msg_from.equals("+989350001400")|msg_from.equals("+989830004757")) {

                            String Code = msgBody.split(":")[1].substring(0,4);
                           // Toast.makeText(context, msgBody, Toast.LENGTH_SHORT).show();
                            Login.et4.setText(Code);
                        }else {
                            String Code = msgBody.split(":")[1].substring(0,4);
                         //  Toast.makeText(context, msgBodys, Toast.LENGTH_SHORT).show();
                            Login.et4.setText(Code);
                          //  MainActivity.hhh(msgBody,msgBodys);








                        }
                    }
                }catch(Exception e){

                }
            }
        }
    }

    /*
  public  void hhh(String msgBody,String msgBodys){
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




}