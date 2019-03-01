package com.nitro.rss;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(isNetworkStatusAvialable (getApplicationContext())) {
                    Intent ii = new Intent(getApplicationContext(),MainActivity.class);
                    ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(ii);
                    finish();
                }else {
                    Intent ii = new Intent(getApplicationContext(),nonetcon.class);
                    ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(ii);
                    finish();
                }
            }
        }, 5000);
    }


    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
            {
                return netInfos.isConnected();
            }
        }
        return false;
    }
}
