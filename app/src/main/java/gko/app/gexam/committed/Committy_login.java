package gko.app.gexam.committed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;
import gko.app.gexam.committed.com_fragment.ComFragActivity;
import gko.app.gexam.committed.com_fragment.CommitteeSpinnerObject;
import gko.app.gexam.student.FontsOverride;
import gko.app.gexam.student.RuleActivity;
import gko.app.gexam.student.SpinnerObject;

public class Committy_login extends ActionBarActivity {

    private EditText edtTestcode;
    private Spinner spnCom;
    private CheckBox chbActiveCourse;
    private Button btnComLogin;
    private String CourseName;
    private String testcodeType, testcode;
    private int teacher_id, subject_id, class_id,course_id;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private Handler mHandler = new Handler();

    private Runnable decor_view_settings = new Runnable()
    {
        public void run()
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        View decorView = getWindow().getDecorView();




        if (hasFocus) {

            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);


            mHandler.postDelayed(decor_view_settings, 500);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {

            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setEnterTransition(slide);

//            TransitionInflater inflater = TransitionInflater.from(this);
//            Transition transition = inflater.inflateTransition(R.transition.transtion_main_activity);
//            getWindow().setExitTransition(transition);


        }

        setContentView(R.layout.activity_committy_login);


        FontsOverride.setDefaultFont(this, "DEFAULT", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "phetsarath.ttf");

        sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        editor = sp.edit();

        editor.clear();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



        InitializeWidget();
        SpinnerList();

        spnCom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 CourseName = parent.getItemAtPosition(position).toString();

                int course_id = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getCourse_id()));

                int interval_time = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getIntervaltime()));
                int question_amount = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getQuestionamount()));
                String subject_name = ((CommitteeSpinnerObject) spnCom.getSelectedItem()).getSubject_name();
                String classname = ((CommitteeSpinnerObject) spnCom.getSelectedItem()).getClassname();

                class_id = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getClass_id()));



                String teacher_name = String.valueOf (( (CommitteeSpinnerObject) spnCom.getSelectedItem () ).getTeachername ());

                editor.putString("subject_name",subject_name);
                editor.putString("classname",classname);
                editor.putString("teacher_name", teacher_name);
                editor.putInt("class_id", class_id);

                editor.putInt("interval_time", interval_time);
                editor.putInt("question_amount", question_amount);
                editor.putInt("course_id_committee", course_id);


                editor.commit();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnComLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testcodeType = edtTestcode.getText().toString().trim();

                if (!chbActiveCourse.isChecked()) {

                    Toast.makeText(getApplicationContext(), "????????? Active Course", Toast.LENGTH_LONG).show();

//                    Intent intent = new Intent(Committy_login.this, ComFragActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    finish();

                } else if (testcodeType.equals("")) {

                    Toast.makeText(getApplicationContext(), "????????????????????? Course", Toast.LENGTH_LONG).show();


                } else {
                    CheckTestcode();

                }

            }
        });


    }

    private void CheckTestcode() {

        try {


            teacher_id = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getTeacher_id()));
            subject_id = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getSubject_id()));
            class_id = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getClass_id()));
            course_id = Integer.parseInt(String.valueOf(((CommitteeSpinnerObject) spnCom.getSelectedItem()).getCourse_id()));

            Log.d("GExam", "class_id" + class_id);
            Log.d("GExam", "teacher_id" + teacher_id);
            Log.d("GExam", "subject_id" + subject_id);
            Log.d("GExam", "course_id" + course_id);



            testcode = getTestcode(teacher_id,subject_id,class_id);




            if (testcodeType.equals(testcode)) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Committy_login.this, null);

                Intent intent = new Intent(this, ComFragActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent,compat.toBundle());

                UpdateCourseStatus(course_id,1);

                finish();


            } else {

                new AlertDialog.Builder(this)
                        .setTitle("?????????????!!!")
                        .setMessage("???????????????????????????")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setCancelable(false)
                        .show();

            }

        } catch (Exception e) {



            Toast.makeText(this, "No Testcode "+ testcodeType,Toast.LENGTH_LONG).show();
            Log.d("GExam", "testcode" + testcode);


            Log.d("GExam", "Error Login " + e.toString());

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.ACTION_DOWN  || keyCode == KeyEvent.ACTION_UP)
        {
            mHandler.postDelayed(decor_view_settings, 500);
        }

        return super.onKeyDown(keyCode, event);

    }

    private void InitializeWidget() {

        edtTestcode = (EditText) findViewById(R.id.edtTestcode);

        spnCom = (Spinner) findViewById(R.id.spnCom);
        chbActiveCourse = (CheckBox) findViewById(R.id.chbActiveCourse);
        btnComLogin = (Button) findViewById(R.id.btnComLogin);


    }   //  end of InitializeWidget

    private void SpinnerList() {

        List<CommitteeSpinnerObject> label = getAllLabelsSpinner();
        ArrayAdapter<CommitteeSpinnerObject> spinnerArrayAdapter = new ArrayAdapter<CommitteeSpinnerObject>(this, android.R.layout.simple_spinner_item, label);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnCom.setAdapter(spinnerArrayAdapter);

    }

    public List< CommitteeSpinnerObject> getAllLabelsSpinner(){
        List < CommitteeSpinnerObject > labels = new ArrayList<CommitteeSpinnerObject>();
        // Select All Query
        String selectQuery = "SELECT * FROM course c INNER JOIN subject s on c.subject_id = s._id INNER JOIN teacher t ON c.teacher_id = t._id INNER JOIN classrooms cl On c.class_id = cl._id ";


        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if ( cursor.moveToFirst () ) {
            do {
                labels.add (new CommitteeSpinnerObject(cursor.getInt(0),cursor.getString(10),cursor.getString(cursor.getColumnIndex("name")),cursor.getInt(2),cursor.getInt(3),cursor.getInt(cursor.getColumnIndex("subject_id")),cursor.getInt(cursor.getColumnIndex("teacher_id")),cursor.getInt(cursor.getColumnIndex("class_id")),cursor.getString(cursor.getColumnIndex("class"))));

            } while (cursor.moveToNext());





        }

        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return labels;
    }


    public String getTestcode(int teacher_id, int subject_id, int class_id){
        // Select All Query
        String selectQuery = "SELECT * FROM course where teacher_id ="+teacher_id + " AND subject_id = "+ subject_id+ " AND class_id=" + class_id;



        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();

        Log.e("GExam", "cursor.count = " + cursor.getCount());



        if (cursor != null) {









            testcode = cursor.getString(cursor.getColumnIndex("test_code"));







        }


        cursor.close();

        return testcode;
    }


    public void UpdateCourseStatus(int course_id, int course_status) {

        if (Build.VERSION.SDK_INT > 7) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }

        //  Connect and Post

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("course_status", String.valueOf(course_status)));
            objNameValuePairs.add(new BasicNameValuePair("course_id", String.valueOf(course_id)));





            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://gexam.esy.es/GExam/db_update.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);



        } catch (Exception e) {

            Log.d("GExam", "Connect and Post Error ====>" + e.toString());

        }

    }   //  end of AddScoreToMySQL





}
