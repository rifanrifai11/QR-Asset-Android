package com.politani.britech.qrasset.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.politani.britech.qrasset.R;
import com.politani.britech.qrasset.fragment.BaseFragmentBottomNavigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragmentBottomNavigation {

    LinearLayout visi,qrasset,develop;
    TextView qrassetWeb,britechWeb;


    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(int index) {
        AboutFragment fragment = new AboutFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        addContainer(view.findViewById(R.id.cordinatorLayout));
        initial(view);
        return view;
    }

    public void initial(View v) {

        v.findViewById(R.id.expandVisi).setOnClickListener(mClickListener);
        v.findViewById(R.id.expandqrasset).setOnClickListener(mClickListener);
        v.findViewById(R.id.expandWedDevelope).setOnClickListener(mClickListener);
        visi = v.findViewById(R.id.visi);
        qrasset = v.findViewById(R.id.qrasset);
        develop = v.findViewById(R.id.develop);
        v.findViewById(R.id.qrassetWeb).setOnClickListener(mClickListener);
        v.findViewById(R.id.britechWeb).setOnClickListener(mClickListener);

    }

    final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.expandVisi:
                    if(v.getRotation() == 0) {
                        v.setRotation(360);
                        visi.setVisibility(View.VISIBLE);
                    }
                    else{
                        v.setRotation(0);
                        visi.setVisibility(View.GONE);
                    }
                    break;
                case R.id.expandqrasset:
                    if(v.getRotation() == 0) {
                        v.setRotation(360);
                        qrasset.setVisibility(View.VISIBLE);
                    }
                    else{
                        v.setRotation(0);
                        qrasset.setVisibility(View.GONE);
                    }
                    break;
                case R.id.expandWedDevelope:
                    if(v.getRotation() == 0) {
                        v.setRotation(360);
                        develop.setVisibility(View.VISIBLE);
                    }
                    else{
                        v.setRotation(0);
                        develop.setVisibility(View.GONE);
                    }
                    break;
                case R.id.qrassetWeb:
                    String uri = "http://www.riungmitra.co.id";
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent2);
                    break;
                case R.id.britechWeb:
                    String uri2 = "http://britech.id";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri2));
                    startActivity(intent);
                    break;
            }
        }
    };

    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
