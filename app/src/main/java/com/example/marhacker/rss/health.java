package com.example.marhacker.rss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class health extends AppCompatActivity {
    ListView lv;
    ArrayList<dis> dis;
    ArrayList<String> links;
    ArrayList<heads> des;
    ArrayList<im> bm;
    ArrayList<URL> imgurl;
    ArrayList<String> headings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        Toolbar toolbar = (Toolbar) findViewById(R.id.heltool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Health News Feed");
        lv= (ListView) findViewById(R.id.healthlist);
        links = new ArrayList<String>();
        des=new ArrayList<heads>();
        bm = new ArrayList<im>();
        dis = new ArrayList<dis>();
        imgurl = new ArrayList<>();
        headings = new ArrayList<>();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(links.get(position));
                String link = uri.toString();
                im bitmap = bm.get(position);
                String heading = headings.get(position);
                Intent intent = new Intent(getApplicationContext(),readstory.class);
                intent.putExtra("1",link);
                intent.putExtra("2",heading);
                intent.putExtra("6",imgurl.get(position).toString());
                startActivity(intent);
            }
        });
        new ProcessInBackground().execute();
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.helfresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                customAdapter customAdapter = new customAdapter( getApplicationContext(),des,bm,dis);
                customAdapter.clear();
                new ProcessInBackground().execute();
                pullToRefresh.setRefreshing(false);
            }
        });
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

    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {
        Exception exception = null;
        ProgressDialog pd = new ProgressDialog(health.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Fetching Story Please Wait...");
            pd.show();
        }

        @Override
        protected Exception doInBackground(Integer... params) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL("http://feeds.feedburner.com/ndtvcooks-latest");
                xpp.setInput(getInputStream(url), "UTF_8");
                boolean insideItem = false;
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                String heada = xpp.nextText();
                                heads heads = new heads();
                                heads.setHead(heada);
                                des.add(heads);
                                headings.add(heada);
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                links.add(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("fullimage")) {
                            im im = new im();
                            URL url1 = new URL(xpp.nextText());
                            Bitmap bitmap = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
                            im.setBm(bitmap);
                            imgurl.add(url1);
                            bm.add(im);
                        } else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                dis d = new dis();
                                d.setDes(xpp.nextText());
                                dis.add(d);
                            }
                        }

                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }

                    eventType = xpp.next();
                }
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            customAdapter customAdapter = new customAdapter(getApplicationContext(), des, bm, dis);
            lv.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            pd.dismiss();
        }
    }
}
