package gko.app.gexam.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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


        if (Build.VERSION.SDK_INT >= 21) {

            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setEnterTransition(slide);

//            TransitionInflater inflater = TransitionInflater.from(this);
//            Transition transition = inflater.inflateTransition(R.transition.transtion_main_activity);
//            getWindow().setExitTransition(transition);


        }

        setContentView(R.layout.activity_rule);

        Table table = new Table(this);

        RuleArray = table.rule(this);
        Log.d("Gexam", "Rule Array" + RuleArray);



        getSharedPefference();



        mRecyclerView = (RecyclerView) findViewById(R.id.ruleList);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RuleAdapter(RuleActivity.this, RuleArray);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());









        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(RuleActivity.this, null);

                    Intent intent = new Intent(RuleActivity.this, QRActivity.class);


                    startActivity(intent,compat.toBundle());




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

//    public List<Rule> getData() {
//
//        List<Rule> data = new ArrayList<>();
////
//
//        String[] ruleTitle = {"tam", "pe", "dog", "cat"};
//
//        for (int i = 0; i < ruleTitle.length; i++) {
//
//
//            Rule current = new Rule();
//            current.title = ruleTitle[i];
//            data.add(current);
//
//        }
//
//        return data;
//    }

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
