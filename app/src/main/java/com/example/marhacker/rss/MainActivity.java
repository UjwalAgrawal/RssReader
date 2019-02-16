package com.example.marhacker.rss;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
public class MainActivity extends AppCompatActivity implements TopNews.OnFragmentInteractionListener,rss_frag.OnFragmentInteractionListener,settingsss.OnFragmentInteractionListener  {
    URL url;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.tuba);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Kinky Feed");
            frame = (FrameLayout)findViewById(R.id.pager);
            final TopNews top = new TopNews();
            final rss_frag rss =new rss_frag();
            final settingsss setting=new settingsss();
            getSupportFragmentManager().beginTransaction().add(R.id.pager,top).add(R.id.pager,rss).add(R.id.pager,setting).hide(rss).hide(setting).commit();
            final BottomNavigationView bn=(BottomNavigationView)findViewById(R.id.btn);
            bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_home:
                            getSupportFragmentManager().beginTransaction().show(top).hide(rss).hide(setting).commit();
                            break;
                        case R.id.menu_feeds:
                            getSupportFragmentManager().beginTransaction().show(rss).hide(top).hide(setting).commit();
                            break;
                        case R.id.menu_settings:
                            getSupportFragmentManager().beginTransaction().show(setting).hide(rss).hide(top).commit();
                            break;

                    }
                    return true;
                }
            });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to close the App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    public InputStream getInputStream(URL url)
    {
        try
        {
            return url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}