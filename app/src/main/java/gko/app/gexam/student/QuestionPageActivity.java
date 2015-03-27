package gko.app.gexam.student;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import gko.app.gexam.R;
import gko.app.gexam.student.generator.AlertDialoge;

public class QuestionPageActivity extends ActionBarActivity {

     Button btnNextQ, btnBackQ, btnSubmit;
    String ALERT_TITLE = "ການສອບເສັງສົມບູນ", ALERT_MESSAGE = "ກະລຸນາລໍຖ້າຄະແນນຈາກອາຈານ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        btnBackQ = (Button) findViewById(R.id.btnBackQ);
        btnNextQ = (Button) findViewById(R.id.btnNextQ);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);


       String[] allAnswer = getResources().getStringArray(R.array.country_arrays);


        for (int i = 0; i < allAnswer.length; i++) {

            Log.d("exam",allAnswer[i]);


        }

        addRadioButton(allAnswer.length, allAnswer);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialoge.AlertExit(QuestionPageActivity.this, ALERT_TITLE, ALERT_MESSAGE);



            }
        });



    }

    private void addRadioButton(int answerRow, String[] answer) {


//        for (int row = 0; row < 1; row++) {
//            LinearLayout ll = new LinearLayout(this);
//            ll.setOrientation(LinearLayout.VERTICAL);
//
//            for (int i = 0; i < answerRow; i++) {
//                RadioButton rdbtn = new RadioButton(this);
//                rdbtn.setId((row * 2) + i);
////                rdbtn.setText("Radio " + rdbtn.getId());
//                rdbtn.setText(answer[i]);
//                ll.addView(rdbtn);
//
//            }
//            ((ViewGroup) findViewById(R.id.radioGroup)).addView(ll);
//
//
//
//        }

        final RadioGroup rgp= (RadioGroup) findViewById(R.id.radioGroup);



        for (int row = 0; row < 1; row++) {


            for(int i=0;i<answerRow;i++){
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(answer[i]);
                radioButton.setId(i);
            RadioGroup.LayoutParams rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);


//                rprms.topMargin = 20 ;
                rprms.weight = 1f;
                rprms.leftMargin = 60;
                rprms.gravity = Gravity.CENTER | Gravity.LEFT;
                rgp.addView(radioButton,rprms);
            }

        }

        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // get selected radio button from radioGroup
                int selectedId = rgp.getCheckedRadioButtonId();

                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                // find the radiobutton by returned id
                Toast.makeText(QuestionPageActivity.this, radioButton
                        .getText(), Toast.LENGTH_SHORT).show();


            }
        });


        

    }   //  end of addRadioButton


}
