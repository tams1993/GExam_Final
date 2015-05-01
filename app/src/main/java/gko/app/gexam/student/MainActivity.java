package gko.app.gexam.student;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.Database.Json_to_SQlite;
import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;
import gko.app.gexam.committed.Committy_login;


public class MainActivity extends ActionBarActivity {

    private Button btnLogin;
    private Spinner spinner,spnCom;
    private EditText edtUser, edtPass;
    private TextView txtCom;
    private Handler mHandler = new Handler();
    private CheckBox chbActiveCourse;
    private String CourseName, strStudentUser, strStudentPass, strTruePass, strStatus, strStd_id, strTest_code, strName,strSurname,strPhone, strEmail, strClass_name;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;




    private Json_to_SQlite json_to_sQlite = new Json_to_SQlite();

    public static final String URL_JSON = "http://192.168.1.2/gexam/db_connect.php";

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
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        this.deleteDatabase("GExam.db");

        new SimpleTask().execute(URL_JSON);




        sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        editor = sp.edit();

        editor.clear();








        FontsOverride.setDefaultFont(this, "DEFAULT", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "phetsarath.ttf");




        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        spinner = (Spinner) findViewById(R.id.spinner);
        txtCom = (TextView) findViewById(R.id.txtCommittee);
        chbActiveCourse = (CheckBox) findViewById(R.id.chbActiveCourse);
        spnCom = (Spinner) findViewById(R.id.spnCom);


        txtCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                                Intent intent = new Intent(MainActivity.this, Committy_login.class);
                startActivity(intent);

//                callLoginDialog();

            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                Toast.makeText(parent.getContext(),
                        "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();

                int testcode = Integer.parseInt (String.valueOf(( (SpinnerObject) spinner.getSelectedItem () ).getTestcode ()));

                int interval_time = Integer.parseInt (String.valueOf(( (SpinnerObject) spinner.getSelectedItem () ).getIntervaltime ()));
                int question_amount = Integer.parseInt (String.valueOf(( (SpinnerObject) spinner.getSelectedItem () ).getQuestionamount ()));

                String teacher_name = String.valueOf (( (SpinnerObject) spinner.getSelectedItem () ).getTeachername ());

                editor.putString("subject_name",parent.getItemAtPosition(position).toString());
                editor.putString("teacher_name",teacher_name);
                editor.putInt("testcode", testcode);
                editor.putInt("interval_time", interval_time);
                editor.putInt("question_amount", question_amount);


                editor.commit();


            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                 strStudentUser = edtUser.getText().toString();
                 strStudentPass = edtPass.getText().toString();

                if ((strStudentUser.equals("")) | (strStudentPass.equals(""))) {

                    Toast.makeText(getApplicationContext(), "ກະລຸນາປ້ອນ ຊື່ຜູ້ໃຊ້ ແລະ ລະຫັດຜ່ານໃຫ້ຄົບຖ້ວນ", Toast.LENGTH_LONG).show();




                } else {

                    CheckUserPassword();





                }


            }
        });


    }

    private void CheckUserPassword() {


        try {



            String arrayData[] = getStudent(strStudentUser);
            strTruePass = arrayData[7];
            strStatus = arrayData[0];
            strStd_id = arrayData[1];
            strTest_code = arrayData[2];
            strName = arrayData[3];
            strSurname = arrayData[4];
            strEmail = arrayData[5];
            strPhone = arrayData[8];
            strClass_name = arrayData[9];


            StudentSharedPrefference();



            for (int i = 0; i <= 9; i++) {

                Log.d("arrayData","arrayData[" + i + "]= " + arrayData[i]);


            }


            if (strStudentPass.equals(strTruePass)) {

                Intent intent = new Intent(MainActivity.this, RuleActivity.class);
                startActivity(intent);


            } else {

                new AlertDialog.Builder(this)
                        .setTitle("ເກີດຂໍ້ຜິດພາດ!!!")
                        .setMessage("ບໍ່ມີຊື່ຜູ້ໃຊ້ ຫຼືລະຫັດບໍ່ຖືກຕ້ອງ")
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

            Toast.makeText(MainActivity.this, "No user "+ strTruePass,Toast.LENGTH_LONG).show();

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

    private class SimpleTask extends AsyncTask<String, Void, String> {


        ProgressDialog objPD;
        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar


            objPD = new ProgressDialog(MainActivity.this);
            objPD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            objPD.setTitle("Loading...");
            objPD.setMessage("ກຳລັງໂຫຼດຂໍ້ມູນ...");
            objPD.setCancelable(false);
            objPD.setIndeterminate(false);

            objPD.show();

        }

        protected String doInBackground(String... urls) {



            return JSON();
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar
            Log.d("Emergency", jsonString);
            Toast.makeText(MainActivity.this, jsonString, Toast.LENGTH_LONG).show();

            json_to_sQlite.Student_Illegal(jsonString, MainActivity.this);

            json_to_sQlite.Classrooms(jsonString, MainActivity.this);
            json_to_sQlite.Course(jsonString, MainActivity.this);
            json_to_sQlite.Exam_Question(jsonString, MainActivity.this);
            json_to_sQlite.Exam_Rule(jsonString, MainActivity.this);
            json_to_sQlite.Questions(jsonString, MainActivity.this);
            json_to_sQlite.Students(jsonString, MainActivity.this);
            json_to_sQlite.Student_Answer(jsonString, MainActivity.this);
            json_to_sQlite.Student_Unblock(jsonString, MainActivity.this);
            json_to_sQlite.Subject(jsonString, MainActivity.this);
            json_to_sQlite.Teacher(jsonString, MainActivity.this);
            json_to_sQlite.Answer_Option(jsonString, MainActivity.this);

            SpinnerList();

            objPD.dismiss();


        }
    }

    private void SpinnerList() {

        List<SpinnerObject> label = getAllLabelsSpinner();
        ArrayAdapter<SpinnerObject> spinnerArrayAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, label);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

    }

    public String JSON() {

        InputStream objInputStream = null;
        String strJSON = "";

        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost(URL_JSON);
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

            Log.d("Emergency", "Connected HTTP Success !");


        } catch (Exception e) {
            Log.d("Emergency", "Error Connect to : " + e.toString());
        }


        //create strJSON
        try {

            BufferedReader objBufferesReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStrBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferesReader.readLine()) != null) {
                objStrBuilder.append(strLine);
            }

            objInputStream.close();
            strJSON = objStrBuilder.toString();

            Log.d("Emergency", "Connected JSON Success !");


        } catch (Exception e) {
            Log.d("Emergency", "Error Convert To JSON :" + e.toString());
        }


        return strJSON;

    }




    public List< SpinnerObject> getAllLabelsSpinner(){
        List < SpinnerObject > labels = new ArrayList<SpinnerObject>();
        // Select All Query
            String selectQuery = "SELECT * FROM course c INNER JOIN subject s on c.subject_code = s.subject_code where c.status =?";


        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{"1"});

        // looping through all rows and adding to list
        if ( cursor.moveToFirst () ) {
            do {
                labels.add (new SpinnerObject(cursor.getInt(4),cursor.getString(9),cursor.getString(6),cursor.getInt(2),cursor.getInt(3)));

            } while (cursor.moveToNext());





        }

//        show what inside List<SpinnerObject>

        int listSize = labels.size();

        for (int i = 0; i<listSize; i++){
            Log.d("Member name: ", String.valueOf(labels.get(i)));
        }



//        int testcode = ( (SpinnerObject) spinner.getSelectedItem () ).getTestcode ();
//
//
//        Log.d("Cursor", "cursor 0  = " + testcode);


        Log.d("Cursor", "cursor spinner  = " + cursor.getCount());
//        Log.d("Cursor", "cursor column 0  = " + cursor.getColumnCount());
//        Log.d("Cursor", "cursor columnindex 0  = " + cursor.getColumnIndex("status"));



        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return labels;
    }


    public String[] getStudent(String strUser){
        List < Student > labels = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT * FROM student_illegal s INNER JOIN course c on s.test_code = c.test_code INNER JOIN students st on s.std_id = st.student_id where st.username =?";
//        String selectQuery = "SELECT * FROM course";
//        String selectQuery = "SELECT * FROM subject";

        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{strUser});

        String[] arrayData = null;

        if (cursor != null) {

            if (cursor.moveToFirst()) {

                arrayData = new String[cursor.getColumnCount()];


                arrayData[0] = cursor.getString(cursor.getColumnIndex("status"));
                arrayData[1] = cursor.getString(cursor.getColumnIndex("std_id"));
                arrayData[2] = cursor.getString(cursor.getColumnIndex("test_code"));
                arrayData[3] = cursor.getString(cursor.getColumnIndex("name"));
                arrayData[4] = cursor.getString(cursor.getColumnIndex("surname"));
                arrayData[5] = cursor.getString(cursor.getColumnIndex("email"));
                arrayData[6] = cursor.getString(cursor.getColumnIndex("username"));
                arrayData[7] = cursor.getString(cursor.getColumnIndex("password"));
                arrayData[8] = cursor.getString(cursor.getColumnIndex("phone"));
                arrayData[9] = cursor.getString(cursor.getColumnIndex("class_name"));


            }

        }



        return arrayData;
    }

    public void StudentSharedPrefference() {


        editor.putString("Student_status", strStatus);
        editor.putString("Student_ID", strStd_id);
        editor.putString("Student_Test_Code", strTest_code);
        editor.putString("Student_Name", strName);
        editor.putString("Student_Surname", strSurname);
        editor.putString("Student_Email", strEmail);
        editor.putString("Student_Class_name", strClass_name);
        editor.putString("Student_Phone", strPhone);

        editor.commit();


    }




}
