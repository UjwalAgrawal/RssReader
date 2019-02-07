package com.example.marhacker.rss;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class catadapter extends ArrayAdapter<cattext> {

    private final Context context;
    private final ArrayList<cattext> list;

    public catadapter(Context context, ArrayList list){
        super(context,R.layout.custom_cat,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_cat,parent,false);
        ImageView im =(ImageView)row.findViewById(R.id.catimg);
        TextView tt = (TextView)row.findViewById(R.id.cattxt);
        String s=list.get(position).getCattext();
        tt.setText(s);
        if(s.equalsIgnoreCase("Top Stories"))
        {
            im.setImageResource(R.drawable.top);
        }
        else if(s.equalsIgnoreCase("World"))
        {
            im.setImageResource(R.drawable.world);
        }

        else if(s.equalsIgnoreCase("Cricket"))
        {
            im.setImageResource(R.drawable.cricket);
        }

        else if(s.equalsIgnoreCase("people"))
        {
            im.setImageResource(R.drawable.people);
        }

        else if(s.equalsIgnoreCase("Latest Stories"))
        {
            im.setImageResource(R.drawable.latest);
        }

        else if(s.equalsIgnoreCase("Business"))
        {
            im.setImageResource(R.drawable.business);
        }
        else if(s.equalsIgnoreCase("Indian Abroad"))
        {
            im.setImageResource(R.drawable.indianabro);
        }
        else if(s.equalsIgnoreCase("Hindi"))
        {
            im.setImageResource(R.drawable.hindi);
        }
        else if(s.equalsIgnoreCase("movies"))
        {
            im.setImageResource(R.drawable.movies);
        }
        else if(s.equalsIgnoreCase("auto"))
        {
            im.setImageResource(R.drawable.auto);
        }
        else if(s.equalsIgnoreCase("health"))
        {
            im.setImageResource(R.drawable.health);
        }
        else if(s.equalsIgnoreCase("india"))
        {
            im.setImageResource(R.drawable.india);
        }
        else if(s.equalsIgnoreCase("sports"))
        {
            im.setImageResource(R.drawable.sports);
        }else if(s.equalsIgnoreCase("cities"))
        {
            im.setImageResource(R.drawable.cities);
        }
        else if(s.equalsIgnoreCase("offbeat"))
        {
            im.setImageResource(R.drawable.offbeat);
        }
        return row;
    }
}
