package gko.app.gexam.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.R;


public class RuleActivity extends ActionBarActivity {

    private Button btnNext;
    private RecyclerView mRecyclerView;
    private RuleAdapter adapter;
    private SQLiteDatabase db;

    private String strStudentUser, strTruePass;
    private List<Rule> RuleArray;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        Table table = new Table(this);

        RuleArray = table.rule(this);
        Log.d("Gexam", "Rule Array" + RuleArray);



        getSharedPefference();



        mRecyclerView = (RecyclerView) findViewById(R.id.ruleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RuleAdapter(RuleActivity.this, RuleArray);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));








        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(RuleActivity.this, QRActivity.class);


                    startActivity(intent);




            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();

        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

    }

    public List<Rule> getData() {

        List<Rule> data = new ArrayList<>();
//

        String[] ruleTitle = {"tam", "pe", "dog", "cat"};

        for (int i = 0; i < ruleTitle.length; i++) {


            Rule current = new Rule();
            current.title = ruleTitle[i];
            data.add(current);

        }

        return data;
    }

    public void getSharedPefference() {


        sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        String subject_name = sp.getString("subject_name", "NO value");
        String teacher_name = sp.getString("teacher_name", "No value");

        TextView txtCourse = (TextView) findViewById(R.id.txtCourse);
        TextView txtTeacherName = (TextView) findViewById(R.id.teacherName);

        txtCourse.setText(subject_name);
        txtTeacherName.setText(teacher_name);



    }

}
