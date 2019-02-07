package com.example.marhacker.rss;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link rss_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link rss_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rss_frag extends Fragment {
    ArrayList<cattext> catt;
    ListView listView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public rss_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rss_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static rss_frag newInstance(String param1, String param2) {
        rss_frag fragment = new rss_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_rss_frag, container, false);
        // Inflate the layout for this fragment
        catt = new ArrayList<cattext>();
        listView = (ListView)v.findViewById(R.id.catlist);
       cattext catt0 = new cattext("Top Stories");
        cattext catt1 = new cattext("World");
        cattext catt2 = new cattext("Cricket");
        cattext catt3 = new cattext("People");
        cattext catt4 = new cattext("Latest Stories");
        cattext catt5 = new cattext("Business");
        cattext catt6 = new cattext("Indian Abroad");
        cattext catt7 = new cattext("Hindi");
        cattext catt9 = new cattext("Auto");
        cattext catt10 = new cattext("Health");
        cattext catt11 = new cattext("India");
        cattext catt12 = new cattext("Sports");
        cattext catt13 = new cattext("Cities");
        cattext catt14 = new cattext("Offbeat");
        catt.add(catt0);
        catt.add(catt1);
        catt.add(catt2);
        catt.add(catt3);
        catt.add(catt4);
        catt.add(catt5);
        catt.add(catt6);
        catt.add(catt7);
        catt.add(catt9);
        catt.add(catt10);
        catt.add(catt11);
        catt.add(catt12);
        catt.add(catt13);
        catt.add(catt14);
        catadapter adapter = new catadapter(getContext(),catt);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0)
                {
                    Intent top = new Intent(getContext(), com.example.marhacker.rss.top.class);
                    startActivity(top);
                }
                else if(position==1)
                {
                    Intent world = new Intent(getContext(), com.example.marhacker.rss.world.class);
                    startActivity(world);
                }
                else if(position==2)
                {
                    Intent cricket = new Intent(getContext(), com.example.marhacker.rss.cricket.class);
                    startActivity(cricket);
                }
                else if(position==3)
                {
                    Intent people = new Intent(getContext(), com.example.marhacker.rss.people.class);
                    startActivity(people);
                }
                else if(position==4)
                {
                    Intent lat=new Intent(getContext(),latest.class);
                    startActivity(lat);
                }
                else if(position==5)
                {
                    Intent bus=new Intent(getContext(),business.class);
                    startActivity(bus);
                }
                else if(position==6)
                {
                    Intent inb=new Intent(getContext(),indianbro.class);
                    startActivity(inb);
                }
                else if(position==7)
                {
                    Intent hin=new Intent(getContext(),hindi.class);
                    startActivity(hin);
                }
                else if(position==8)
                {
                    Intent aut=new Intent(getContext(),auto.class);
                    startActivity(aut);
                }
                else if(position==9)
                {
                    Intent heal=new Intent(getContext(),health.class);
                    startActivity(heal);
                }
                else if(position==10)
                {
                    Intent indian=new Intent(getContext(),india.class);
                    startActivity(indian);
                }
                else if(position==11)
                {
                    Intent sp=new Intent(getContext(),sportss.class);
                    startActivity(sp);
                }
                else if(position==12)
                {
                    Intent cit=new Intent(getContext(),cities.class);
                    startActivity(cit);
                }
                else if(position==13)
                {
                    Intent off=new Intent(getContext(),offbeat.class);
                    startActivity(off);
                }
            }
        });
        return  v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
