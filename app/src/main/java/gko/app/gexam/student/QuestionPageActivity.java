package gko.app.gexam.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;
import gko.app.gexam.student.generator.AlertDialoge;

public class QuestionPageActivity extends ActionBarActivity {

    private TextView txtQuestion, txtTime;
    private SharedPreferences sp;
    private Button btnNextQ, btnBackQ, btnSubmit;
    private String ALERT_TITLE = "ການສອບເສັງສົມບູນ", ALERT_MESSAGE = "ກະລຸນາລໍຖ້າຄະແນນຈາກອາຈານ",Question, Answer1, Answer2, Answer3,Answer4,
            CorrectAnswer1,CorrectAnswer2,CorrectAnswer3,CorrectAnswer4;

    private String[] arrayQuestion,QuestionAnswer;
    private int counter = 0;

    private int question_amount = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtTime = (TextView) findViewById(R.id.txtTime);

        btnBackQ = (Button) findViewById(R.id.btnBackQ);
        btnNextQ = (Button) findViewById(R.id.btnNextQ);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

//        int question_amount = sp.getInt("question_amount",-1);



         arrayQuestion = getQuestion().toArray(new String[getQuestion().size()]); // change list<String> to array


//
        Log.d("arrayQuestion", "arrayQuestion[" + 0 + "]= " + arrayQuestion[0]);
        Log.d("arrayQuestion", "arrayQuestion[" + 1 + "]= " + arrayQuestion[1]);
        Log.d("arrayQuestion", "arrayQuestion[" + 2 + "]= " + arrayQuestion[2]);
        Log.d("arrayQuestion", "arrayQuestion[" + 3 + "]= " + arrayQuestion[3]);


         Question = arrayQuestion[counter];
         Answer1 = getAnswer(Question).get(0);
         Answer2 = getAnswer(Question).get(1);
         Answer3 = getAnswer(Question).get(2);
         Answer4 = getAnswer(Question).get(3);

         CorrectAnswer1 = getAnswer(Question).get(4);
         CorrectAnswer2 = getAnswer(Question).get(5);
         CorrectAnswer3 = getAnswer(Question).get(6);
         CorrectAnswer4 = getAnswer(Question).get(7);


          QuestionAnswer = new String[]{Answer1, Answer2, Answer3, Answer4, Question, CorrectAnswer1, CorrectAnswer2, CorrectAnswer3, CorrectAnswer4};




        txtQuestion.setText(counter+1+"/"+(question_amount+1)+" "+Question);
        Log.d("counter", "counter = " + counter+ "question = " + Question);


        addRadioButton(QuestionAnswer.length - 5, QuestionAnswer);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialoge.AlertExit(QuestionPageActivity.this, ALERT_TITLE, ALERT_MESSAGE);


            }
        });

        btnBackQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter--;


                if (counter < 0) {


                    counter = question_amount;
                    QuestionAnswerPerPage(counter);


                } else {

                    Log.d("counter", "counter = " + counter+ "question = " + Question);

                    QuestionAnswerPerPage(counter);


                }

            }
        });

        btnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;


                if (counter <= question_amount) {






                    QuestionAnswerPerPage(counter);






                } else {

                    counter = 0;
                    Log.d("counter", "counter = " + counter+ "question = " + Question);

                    QuestionAnswerPerPage(counter);


                }


            }
        });


    }

    private void addRadioButton(int answerRow, String[] answer) {

        final RadioGroup rgp = (RadioGroup) findViewById(R.id.radioGroup);


        for (int row = 0; row < 1; row++) {


            for (int i = 0; i < answerRow; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(answer[i]);
                radioButton.setId(i);
                RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);


//                rprms.topMargin = 20 ;
                rprms.weight = 1f;
                rprms.leftMargin = 60;
//                rprms.gravity = Gravity.CENTER | Gravity.LEFT;
                rgp.addView(radioButton, rprms);
            }

        }

        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // get selected radio button from radioGroup
                int selectedId = rgp.getCheckedRadioButtonId();

                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                // find the radiobutton by returned id
                Toast.makeText(QuestionPageActivity.this, radioButton
                        .getText(), Toast.LENGTH_SHORT).show();


            }
        });


    }   //  end of addRadioButton


    private List<String> getAnswer(String Question) {

        List<String> labels = new ArrayList<>();

        String strQuery = "SELECT * FROM questions q INNER JOIN answer_option a ON q.question_id = a.question_id where q.question =?";

        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{Question});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("answer")));


            } while (cursor.moveToNext());


        }

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("correct")));


            } while (cursor.moveToNext());


        }

//        show what inside List<SpinnerObject>
//
//        int listSize = labels.size();
//
//        for (int i = 0; i < listSize; i++) {
//            Log.d("Member name: ", String.valueOf(labels.get(i)));
//        }

        return labels;

    }

    private List<String> getQuestion() {

        List<String> labels = new ArrayList<>();

        String strQuery = "SELECT * FROM questions";

        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("question")));

            } while (cursor.moveToNext());


        }

        Collections.shuffle(labels);


//        show what inside List<SpinnerObject>

        int listSize = labels.size();

        for (int i = 0; i < listSize; i++) {
//            Log.d("Member name: ", String.valueOf(labels.get(i)));
        }

        return labels;

    }



    public void QuestionAnswerPerPage(int Counter) {

        final RadioGroup rgp = (RadioGroup) findViewById(R.id.radioGroup);

        rgp.removeAllViews();



//
        Log.d("arrayQuestion", "arrayQuestion[" + 0 + "]= " + arrayQuestion[0]);
        Log.d("arrayQuestion", "arrayQuestion[" + 1 + "]= " + arrayQuestion[1]);
        Log.d("arrayQuestion", "arrayQuestion[" + 2 + "]= " + arrayQuestion[2]);
        Log.d("arrayQuestion", "arrayQuestion[" + 3 + "]= " + arrayQuestion[3]);


        String Question = arrayQuestion[counter];
        String Answer1 = getAnswer(Question).get(0);
        String Answer2 = getAnswer(Question).get(1);
        String Answer3 = getAnswer(Question).get(2);
        String Answer4 = getAnswer(Question).get(3);

        String CorrectAnswer1 = getAnswer(Question).get(4);
        String CorrectAnswer2 = getAnswer(Question).get(5);
        String CorrectAnswer3 = getAnswer(Question).get(6);
        String CorrectAnswer4 = getAnswer(Question).get(7);


        QuestionAnswer = new String[]{Answer1, Answer2, Answer3, Answer4, Question, CorrectAnswer1, CorrectAnswer2, CorrectAnswer3, CorrectAnswer4};

        txtQuestion.setText(Counter+1+"/"+(question_amount+1)+" "+Question);

        addRadioButton(QuestionAnswer.length-5,QuestionAnswer);


    }



}
