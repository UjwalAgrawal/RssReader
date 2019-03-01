package com.nitro.rss;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;

public class readstory extends AppCompatActivity {
    TextView tt,story;
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readstory);
        new dosomething().execute();



    }

    protected class dosomething extends AsyncTask<Integer, Void, Exception> {
        ProgressDialog pd = new ProgressDialog(readstory.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(!isFinishing()) {
                pd.setMessage("Fetching Story Please Wait...");
                if(!isFinishing()){
                pd.show();}
            }
        }

        @Override
        protected Exception doInBackground(Integer... integers) {
            try {
                Bundle bb = getIntent().getExtras();
                String s = bb.getString("1");
                TextView tt = (TextView) findViewById(R.id.tt);
                tt.setMovementMethod(new ScrollingMovementMethod());
                Document dd = Jsoup.connect(s).get();
               TextView story = (TextView) findViewById(R.id.story);
               ImageView im = (ImageView)findViewById(R.id.readimg);
                URL url= new URL(bb.getString("6"));
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                tt.setText(dd.getElementsByTag("p").text());

                    story.setText(bb.getString("2"));
                    im.setImageBitmap(bitmap);
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception e) {
            super.onPostExecute(e);
            pd.dismiss();
        }
    }
}


