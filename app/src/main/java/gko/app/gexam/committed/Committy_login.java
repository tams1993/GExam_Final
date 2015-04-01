package gko.app.gexam.committed;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import gko.app.gexam.R;
import gko.app.gexam.committed.com_fragment.ComFragActivity;

public class Committy_login extends ActionBarActivity {

    private EditText edtComUser, edtComPass;
    private Spinner spnCom;
    private CheckBox chbActiveCourse;
    private Button btnComLogin;
    private String CourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committy_login);


        InitializeWidget();

        spnCom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 CourseName = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnComLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chbActiveCourse.isChecked() && CourseName != null) {


                    Intent intent = new Intent(Committy_login.this, ComFragActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(getApplicationContext(), "Check Active Please", Toast.LENGTH_LONG).show();


                }

            }
        });


    }

    private void InitializeWidget() {

        edtComUser = (EditText) findViewById(R.id.edtComUser);
        edtComPass = (EditText) findViewById(R.id.edtComPass);
        spnCom = (Spinner) findViewById(R.id.spnCom);
        chbActiveCourse = (CheckBox) findViewById(R.id.chbActiveCourse);
        btnComLogin = (Button) findViewById(R.id.btnComLogin);


    }   //  end of InitializeWidget


}
