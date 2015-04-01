package gko.app.gexam.committed.com_fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gko.app.gexam.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class RuleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_rule,container,false);
        return v;
    }
}