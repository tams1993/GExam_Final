package gko.app.gexam.student;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import gko.app.gexam.R;


public class CoureseDetail_Activity extends ActionBarActivity {

    private Button btnStart;
    private CheckBox cbConfirm;
    private TextView txtCourse_detail_subject, txtInterval;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courese_detail_);

        btnStart = (Button) findViewById(R.id.btnStart);
        cbConfirm = (CheckBox) findViewById(R.id.cbConfirm);

       getSharedPefference();



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cbConfirm.isChecked()) {


                    startActivity(new Intent(CoureseDetail_Activity.this, QuestionPageActivity.class ));

                    Toast.makeText(getApplicationContext(),"CheckBox is Checked",Toast.LENGTH_LONG).show();




                } else {

                    Toast.makeText(getApplicationContext(),"CheckBox is unCheck",Toast.LENGTH_LONG).show();


                    new AlertDialog.Builder(CoureseDetail_Activity.this)
                            .setTitle("ກະລຸນາໝາຍຕິກ")
                            .setMessage("ກະລຸນາຕິກເພື່ອຢືນຢັນວ່າພ້ອມສອບເສັງແລ້ວ.")
                            .setPositiveButton("ຕົກລົງ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    dialog.dismiss();

                                }
                            })
                            .show();


                }

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

    public void getSharedPefference() {


        sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        String subject_name = sp.getString("subject_name", "NO value");
        int interval_time = sp.getInt("interval_time", -1);

        txtCourse_detail_subject = (TextView) findViewById(R.id.txtCourse_detail_subject);
        txtInterval = (TextView) findViewById(R.id.txtInterval);

        txtCourse_detail_subject.setText(subject_name);
        txtInterval.setText(String.valueOf(interval_time));



    }



}
