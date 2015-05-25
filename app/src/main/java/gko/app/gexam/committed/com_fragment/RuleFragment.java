package gko.app.gexam.committed.com_fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gko.app.gexam.R;
import gko.app.gexam.student.Rule;
import gko.app.gexam.student.RuleActivity;
import gko.app.gexam.student.RuleAdapter;
import gko.app.gexam.student.Table;

/**
 * Created by hp1 on 21-01-2015.
 */
public class RuleFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RuleAdapter adapter;
    private SQLiteDatabase db;

    private String strStudentUser, strTruePass;
    private List<Rule> RuleArray;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {



        View v =inflater.inflate(R.layout.fragment_rule,container,false);


        Table table = new Table(getActivity());

        RuleArray = table.rule(getActivity());
        Log.d("Gexam", "Rule Array" + RuleArray);


        sp = this.getActivity().getSharedPreferences("PREF_NAME",Context.MODE_PRIVATE);

        String subject_name = sp.getString("subject_name", "NO value");
        String teacher_name = sp.getString("teacher_name", "No value");
        String classname = sp.getString("classname", "No value");


        TextView txtCourse = (TextView) v.findViewById(R.id.txtCourseFragment);
        TextView txtTeacherName = (TextView) v.findViewById(R.id.teacherNameFragment);
        TextView txtFragClassName = (TextView) v.findViewById(R.id.txtFragClassName);

        txtCourse.setText(subject_name);
        txtTeacherName.setText(teacher_name);
        txtFragClassName.setText(classname);






        mRecyclerView = (RecyclerView) v.findViewById(R.id.ruleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RuleAdapter(getActivity(), RuleArray);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;




    }



}