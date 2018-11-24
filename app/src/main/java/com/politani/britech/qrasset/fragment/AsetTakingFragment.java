package com.politani.britech.qrasset.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.britech.riung.R;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.politani.britech.qrasset.fragment.BaseFragmentBottomNavigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsetTakingFragment extends BaseFragmentBottomNavigation {


    public AsetTakingFragment() {
        // Required empty public constructor
    }

    public static AsetTakingFragment newInstance(int index) {
        AsetTakingFragment fragment = new AsetTakingFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aset_taking, container, false);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
