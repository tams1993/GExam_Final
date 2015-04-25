package gko.app.gexam.student;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.Database.Table;
import gko.app.gexam.R;


public class RuleActivity extends ActionBarActivity {

    private Button btnNext;
    private RecyclerView mRecyclerView;
    private RuleAdapter adapter;

    private String strStudentUser, strTruePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);


        mRecyclerView = (RecyclerView) findViewById(R.id.ruleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RuleAdapter(RuleActivity.this, getData());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Table table = new Table(this);




        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(RuleActivity.this, QRActivity.class);


                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Course is Active ", Toast.LENGTH_LONG).show();



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

    public static List<Rule> getData() {

        List<Rule> data = new ArrayList<>();
        String[] ruleTitle = {"tam", "pe", "dog", "cat"};

        for (int i = 0; i < ruleTitle.length; i++) {


            Rule current = new Rule();
            current.title = ruleTitle[i];
            data.add(current);

        }

        return data;
    }

}
