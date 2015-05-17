package gko.app.gexam.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;
import gko.app.gexam.student.generator.AlertDialoge;

public class QuestionPageActivity extends ActionBarActivity {

    private TextView txtQuestion, txtTime;

    private Button btnNextQ, btnBackQ, btnSubmit;
    private String ALERT_TITLE = "ການສອບເສັງສົມບູນ", ALERT_MESSAGE = "ກະລຸນາລໍຖ້າຄະແນນຈາກອາຈານ",Question, Answer1, Answer2, Answer3,Answer4,
            CorrectAnswer1,CorrectAnswer2,CorrectAnswer3,CorrectAnswer4, ALERT_EXIT_MESSAGE="ທ່ານໄດ້ອອກຈາກໂປຣແກຣມໃນລະຫວ່າງການສອບເສັງເກີນກຳນົດ. ກະລຸນາລໍຖ້າຄະແນນຈາກອາຈານ"
            , ALERT_EXIT_TITILE = "ການສອບເສັງຖືກຍຸດຕິ!!!", specificQuestion, specificAnswer, specificCorrectAnswer;

    private String[] arrayQuestion,QuestionAnswer,AnswerOnly;
    private int counter = 0, exitCount =0, score=0;

    private int question_amount = 4, teacher_id, subject_id, answer,interval_time;

    private SharedPreferences sp, spName;
    private SharedPreferences.Editor editor;

    private CountDownTimer objCountDown;


    private RadioGroup rgp;
    private int Student_ID;
    private String ALERT_BACKPRESS_TITLE= "ທ່ານໄດ້ກົດປຸ່ມກັບຄືນ!!!";
    private String ALERT_BACKPRESS_MESSAGE= "ທ່ານບໍ່ສາມາດກັບຄືນໄດ້ໃນຂະນະທີ່ເສັງຢູ່";

    private int QuestionID;

    @Override
    protected void onStop() {
        super.onStop();

        exitCount++;


        Log.d("GExam", "option key pressed");

    }

    @Override
    public void onBackPressed() {




        AlertDialoge.AlertBackPress(QuestionPageActivity.this, ALERT_BACKPRESS_TITLE, ALERT_BACKPRESS_MESSAGE);



    }

    @Override
    protected void onResume() {
        super.onResume();

        if (exitCount == 5+1 ) {

            AlertDialoge.AlertExit(QuestionPageActivity.this, ALERT_EXIT_TITILE, ALERT_EXIT_MESSAGE);
            objCountDown.cancel();


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtTime = (TextView) findViewById(R.id.txtTime);

        btnBackQ = (Button) findViewById(R.id.btnBackQ);
        btnNextQ = (Button) findViewById(R.id.btnNextQ);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        sp = getSharedPreferences("PREF_QUESTION", Context.MODE_PRIVATE);
        spName = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        teacher_id = spName.getInt("teacher_id", -1);
        subject_id = spName.getInt("subject_id", -1);
        interval_time = spName.getInt("interval_time", -1);


        editor = sp.edit();
        editor.clear();

//        int question_amount = sp.getInt("question_amount",-1);




         arrayQuestion = getQuestion().toArray(new String[getQuestion().size()]); // change list<String> to array

        for (int i = 0; i < arrayQuestion.length; i++) {


            Log.d("GExam", "arrayQuestion " + i + arrayQuestion[i]);

        }




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

//        AnswerOnly = new String[]{CorrectAnswer1, CorrectAnswer2, CorrectAnswer3, CorrectAnswer4};


        QuestionID = getQuestionID(Question);

        AnswerOnly = getCorrectAnswer(Question);
         specificQuestion = AnswerOnly[0];
         specificAnswer = AnswerOnly[1];
         specificCorrectAnswer = AnswerOnly[2];




        Log.d("GExam", "QuestionID= " + QuestionID);










        txtQuestion.setText(counter+1+"/"+(question_amount+1)+" "+Question);
//        Log.d("counter", "counter = " + counter+ "question = " + Question);


        addRadioButton(QuestionAnswer.length - 5, QuestionAnswer);
        CountDown();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialoge.AlertExit(QuestionPageActivity.this, ALERT_TITLE, ALERT_MESSAGE);




                for (int i = 0; i <= question_amount; i++) {

                    String spAnswer = sp.getString("specificAnswer" + i, "no Answer");
                    String spChoice = sp.getString("answer_choice" + i, "no Choice");

                    int spQuestion_ID = sp.getInt("Question_ID " + i, -1);
                    int spanswer_choice_ID = sp.getInt("answer_choice_ID" + i, -1);

                    int course_id = spName.getInt("course_id", -1);
                    int std_id = spName.getInt("std_id", -1);

                    AddStudentChoiceToMySQL(course_id,std_id ,spQuestion_ID,spanswer_choice_ID);


                    Log.e("GExam", "course_id= " + String.valueOf(spName.getInt("course_id", -1)));
                    Log.e("GExam", "std_id= " + String.valueOf(sp.getInt("std_id", -1)));
                    Log.e("GExam", "spQuestion_ID= " +  String.valueOf(spQuestion_ID));
                    Log.e("GExam", "spanswer_choice_ID= " +  String.valueOf(spanswer_choice_ID));


                    if (spChoice.equals(spAnswer)) {

                        score++;

                }

                }

                Log.e("GExam", "total score = " + score);

                Student_ID = spName.getInt("std_id", -1);




                AddScoreToMySQL(score,Student_ID,subject_id,teacher_id);
                Log.e("GExam", "Student_ID = " + Student_ID);


                objCountDown.cancel();
                editor.clear();



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

                int answer = sp.getInt("answer_choice " + (counter), -1);

                if (answer != -1) {




                    ((RadioButton)rgp.getChildAt(answer)).setChecked(true);

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

                 answer = sp.getInt("answer_choice " + (counter), -1);

//                if (rgp.getCheckedRadioButtonId() != -1) {
//
//                    rgp.clearCheck();
//
//
//                }

                if (answer != -1) {


                    ((RadioButton) rgp.getChildAt(answer)).setChecked(true);

                }




            }
        });


    }

    private void addRadioButton(int answerRow, String[] answer) {

          rgp = (RadioGroup) findViewById(R.id.radioGroup);

//        ((RadioButton) rgp.getChildAt(answer)).setChecked(true);

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

                String selection =(String) radioButton.getText();



                editor.putInt("answer_choice " + counter, checkedId);
                editor.putString("answer_choice" + counter, selection);
                editor.putString("specificAnswer" + counter, specificAnswer);
                editor.putInt("answer_choice_ID" + counter, getChoiceID(selection));
                editor.commit();

                // find the radiobutton by returned id
                Toast.makeText(QuestionPageActivity.this, radioButton
                        .getText(), Toast.LENGTH_SHORT).show();

                Log.d("GExam", "Question: " + (counter + 1) + " = " + String.valueOf(sp.getInt("answer_choice " + (counter), -1)));


//                if (selection.equals(specificAnswer)) {
//
//
//                    score++;
//
//
//                }


                Log.e("GExam", "score = " + score);
                Log.e("GExam", "selection = " + selection);
                Log.e("GExam", "specificAnswer = " + specificAnswer);



            }
        });


    }   //  end of addRadioButton


    private List<String> getAnswer(String Question) {

        List<String> labels = new ArrayList<>();

        String strQuery = "SELECT * FROM questions q INNER JOIN answer_option a ON q._id = a.question_id where q.question =?";

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


    private List<Integer> getAnswerID(String Question) {

        List<Integer> labels = new ArrayList<>();

        String strQuery = "SELECT * FROM questions q INNER JOIN answer_option a ON q._id = a.question_id where q.question =?";

        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{Question});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getInt(cursor.getColumnIndex("answer")));


            } while (cursor.moveToNext());


        }

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getInt(cursor.getColumnIndex("correct")));


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

        String strQuery = "SELECT * FROM questions where teacher_id ="+teacher_id+" and subject_id=" + subject_id;

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


        rgp.removeAllViews();






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


        QuestionID = getQuestionID(Question);

        AnswerOnly = getCorrectAnswer(Question);
        specificQuestion = AnswerOnly[0];
        specificAnswer = AnswerOnly[1];
        specificCorrectAnswer = AnswerOnly[2];

        Log.d("GExam", "QuestionID= " + QuestionID);


        txtQuestion.setText(Counter+1+"/"+(question_amount+1)+" "+Question);

        editor.putInt("Question_ID " + Counter, QuestionID);


        addRadioButton(QuestionAnswer.length - 5, QuestionAnswer);


    }

    private void CountDown() {


        objCountDown = new CountDownTimer(interval_time * 60000, 100) {

            public void onTick(long l) {


                int secondsLeft = 0;




                if (Math.round((float) l / 1000.0f) != secondsLeft) {
                    secondsLeft = Math.round((float) l / 1000.0f);

                    // time countdown

                    txtTime.setText("ເວລາທີ່ເຫຼືອ: " + String.format("%02d:%02d:%02d", secondsLeft / 3600, (secondsLeft % 3600) / 60, (secondsLeft % 60)));

                }
            }

            public void onFinish() {


                txtTime.setText("Time's up!!!");
                AlertDialoge.AlertExit(QuestionPageActivity.this, ALERT_TITLE, ALERT_MESSAGE);



            }

        }.start();



    }

    public String[] getCorrectAnswer(String Question) {

        String selectQuery = "SELECT * FROM questions q INNER JOIN answer_option a ON q._id = a.question_id where q.question =? AND a.correct = 1";


        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{Question});

        cursor.moveToFirst();


        String[] arrayData = null;


        if (cursor != null) {



            arrayData = new String[cursor.getColumnCount()];

            arrayData[0] = cursor.getString(cursor.getColumnIndex("question"));
            arrayData[1] = cursor.getString(cursor.getColumnIndex("answer"));


            arrayData[2] = cursor.getString(cursor.getColumnIndex("correct"));




        }



        cursor.close();
        return arrayData;

    }


    public int getQuestionID(String Question) {

        String strQuery = "SELECT * FROM questions where question=? ";


        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery,new String[]{Question});

        cursor.moveToFirst();


        int arrayID = 0;


        if (cursor != null) {


            arrayID = cursor.getInt(cursor.getColumnIndex("_id"));





        }



        cursor.close();
        return arrayID;

    }


    public int getChoiceID(String AnswerChoice) {

        String strQuery = "SELECT * FROM answer_option where answer=? ";


        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery,new String[]{AnswerChoice});

        cursor.moveToFirst();


        int arrayID = 0;


        if (cursor != null) {


            arrayID = cursor.getInt(cursor.getColumnIndex("_id"));





        }



        cursor.close();
        return arrayID;

    }


    public void AddScoreToMySQL(int score, int Student_id, int subject_id, int teacher_id) {

        if (Build.VERSION.SDK_INT > 7) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }

        //  Connect and Post

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("final", String.valueOf(score)));
            objNameValuePairs.add(new BasicNameValuePair("std_id", String.valueOf(Student_id)));
            objNameValuePairs.add(new BasicNameValuePair("subject_id", String.valueOf(subject_id)));
            objNameValuePairs.add(new BasicNameValuePair("teacher_id", String.valueOf(teacher_id)));


            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://192.168.1.5/GExam/db_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            Log.d("GExam", "String score = " + String.valueOf(score));

        } catch (Exception e) {

            Log.d("GExam", "Connect and Post Error ====>" + e.toString());

        }

    }   //  end of AddScoreToMySQL


    public void AddStudentChoiceToMySQL(int course_id, int std_id, int question_id, int student_ans_id) {

        if (Build.VERSION.SDK_INT > 7) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }

        //  Connect and Post

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("course_id", String.valueOf(course_id)));
            objNameValuePairs.add(new BasicNameValuePair("std_id", String.valueOf(std_id)));
            objNameValuePairs.add(new BasicNameValuePair("question_id", String.valueOf(question_id)));
            objNameValuePairs.add(new BasicNameValuePair("student_ans_id", String.valueOf(student_ans_id)));


            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://192.168.1.5/GExam/db_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            Log.e("GExam","course_id " + course_id);
            Log.e("GExam","std_id " + std_id);
            Log.e("GExam","question_id " + question_id);
            Log.e("GExam","student_ans_id " + student_ans_id);


        } catch (Exception e) {

            Log.e("GExam", "Connect and Post Error ====>" + e.toString());

        }

    }   //  end of AddScoreToMySQL



}
