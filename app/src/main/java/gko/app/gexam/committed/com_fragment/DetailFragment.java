package gko.app.gexam.committed.com_fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gko.app.gexam.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class DetailFragment extends Fragment {


    private TextView txtCourse_detail_subject, txtInterval;
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_detail,container,false);

        getSharedPefference(v);

        return v;



    }


    public void getSharedPefference(View view) {


        sp = this.getActivity().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        String subject_name = sp.getString("subject_name", "NO value");
        int interval_time = sp.getInt("interval_time", -1);

        txtCourse_detail_subject = (TextView) view.findViewById(R.id.txtCourse_detail_subject_fragment);
        txtInterval = (TextView) view.findViewById(R.id.txtInterval_fragment);

        txtCourse_detail_subject.setText(subject_name);
        txtInterval.setText(String.valueOf(interval_time));



    }

}