package com.example.marhacker.rss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopNews.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopNews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopNews extends Fragment {
    ListView lv;
    ArrayList<dis> dis;
    ArrayList<String> links;
    ArrayList<heads> des;
    ArrayList<im> bm;
    ArrayList<URL> imgurl;
    ArrayList<String> headings;
    Object object;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TopNews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopNews.
     */
    // TODO: Rename and change types and number of parameters
    public static TopNews newInstance(String param1, String param2) {
        TopNews fragment = new TopNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_news, container, false);
        lv= view.findViewById(R.id.lav);
        links = new ArrayList<String>();
        des=new ArrayList<heads>();
        bm = new ArrayList<im>();
        dis = new ArrayList<dis>();
        imgurl = new ArrayList<>();
        headings = new ArrayList<>();
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.topfresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                customAdapter customAdapter = new customAdapter( getActivity(),des,bm,dis);
                customAdapter.clear();
                new TopNews.ProcessInBackground().execute();
                pullToRefresh.setRefreshing(false);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(links.get(position));
                String link = uri.toString();
                im bitmap = bm.get(position);
                String heading = headings.get(position);
                Intent intent = new Intent(getContext(),readstory.class);
                intent.putExtra("1",link);
                intent.putExtra("2",heading);
                intent.putExtra("6",imgurl.get(position).toString());
                startActivity(intent);
            }
        });
        new TopNews.ProcessInBackground().execute();

        return view;
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

    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception>
    {
        ProgressDialog pd = new ProgressDialog(getContext());
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setMessage("Fetching Stories Please Wait...");
            pd.show();
        }

        @Override
        protected Exception doInBackground(Integer... params) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL("http://feeds.feedburner.com/ndtvnews-trending-news");
                xpp.setInput(getInputStream(url), "UTF_8");
                boolean insideItem = false;
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    if (eventType == XmlPullParser.START_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            insideItem = true;
                        }
                        else if (xpp.getName().equalsIgnoreCase("title"))
                        {
                            if (insideItem)
                            {
                                String heada = xpp.nextText();
                                heads heads = new heads();
                                heads.setHead(heada);
                                des.add(heads);
                                headings.add(heada);
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("link"))
                        {
                            if (insideItem)
                            {
                                links.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("fullimage"))
                        {
                            im im = new im();
                            URL url1 = new URL(xpp.nextText());
                            Bitmap bitmap = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
                            im.setBm(bitmap);
                            imgurl.add(url1);
                            bm.add(im);
                        }
                        else if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            if (insideItem)
                            {
                                dis d = new dis();
                                d.setDes(xpp.nextText());
                                dis.add(d);
                            }
                        }

                    }
                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                    {
                        insideItem = false;
                    }

                    eventType = xpp.next();
                }
            }
            catch (MalformedURLException e)
            {
                exception = e;
            }
            catch (XmlPullParserException e)
            {
                exception = e;
            }
            catch (IOException e)
            {
                exception = e;
            }
            return exception;
        }
        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            customAdapter customAdapter = new customAdapter( getActivity(),des,bm,dis);
            lv.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            pd.dismiss();


        }
    }

    public Object getObject() {
        return object;
    }

    public void setobj(Object o)
    {
        this.object=o;
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
