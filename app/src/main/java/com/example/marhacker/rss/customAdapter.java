package com.example.marhacker.rss;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class customAdapter extends ArrayAdapter<heads>  {
    private final Context context;
    private final ArrayList<heads> list;
    private final ArrayList<im> ok;
    private final ArrayList<dis> dd;

    public customAdapter(Context context, ArrayList list,ArrayList ok,ArrayList dd){
        super(context,R.layout.custom,list);
        this.context=context;
        this.list=list;
        this.ok = ok;
        this.dd = dd;
    }

public void clear()
{
    this.list.clear();
    this.ok.clear();
    this.dd.clear();
}

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom,parent,false);
        TextView head =(TextView) row.findViewById(R.id.head);
        TextView des =(TextView) row.findViewById(R.id.des);
        ImageView img = (ImageView) row.findViewById(R.id.img);
        head.setText(list.get(position).getHead());
        des.setText(dd.get(position).getDes());
        img.setImageBitmap(ok.get(position).getBm());
        return row;
    }
}
