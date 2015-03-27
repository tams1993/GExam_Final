package gko.app.gexam.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import gko.app.gexam.R;


public class CoureseDetail_Activity extends ActionBarActivity {

    private Button btnStart;
    private CheckBox cbConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courese_detail_);

        btnStart = (Button) findViewById(R.id.btnStart);
        cbConfirm = (CheckBox) findViewById(R.id.cbConfirm);

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



}
