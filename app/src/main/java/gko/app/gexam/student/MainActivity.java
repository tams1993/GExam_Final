package gko.app.gexam.student;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import gko.app.gexam.Database.Json_to_SQlite;
import gko.app.gexam.Database.Table;
import gko.app.gexam.R;
import gko.app.gexam.committed.Committy_login;
import gko.app.gexam.committed.com_fragment.ComFragActivity;


public class MainActivity extends ActionBarActivity {

    private Button btnLogin;
    private Spinner spinner,spnCom;
    private EditText edtUser, edtPass;
    private TextView txtCom;
    private Handler mHandler = new Handler();
    private CheckBox chbActiveCourse;
    private String CourseName;


    private Json_to_SQlite json_to_sQlite = new Json_to_SQlite();

    public static final String URL_JSON = "http://192.168.1.9/gexam/db_connect.php";

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




        new SimpleTask().execute(URL_JSON);

        Table classroomsTable = new Table(this);










        FontsOverride.setDefaultFont(this, "DEFAULT", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "phetsarath.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "phetsarath.ttf");

        SharedPreferences sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();


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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editor.putString("USER", edtUser.getText().toString());
                editor.commit();

                if (edtUser.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Type Something", Toast.LENGTH_LONG).show();

                } else {

                    Intent intent = new Intent(MainActivity.this, RuleActivity.class);
                    startActivity(intent);


                }


            }
        });


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

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
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



        }
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

//

    private void callLoginDialog()
    {
        Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.activity_committy_login);
        myDialog.setCancelable(true);
        Button login = (Button) myDialog.findViewById(R.id.btnComLogin);

        edtUser = (EditText) myDialog.findViewById(R.id.edtComUser);
        edtPass = (EditText) myDialog.findViewById(R.id.edtComPass);
        myDialog.show();


        new Committy_login();

        spnCom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CourseName = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chbActiveCourse.isChecked() && CourseName != null) {


                    Intent intent = new Intent(MainActivity.this, ComFragActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(getApplicationContext(), "Check Active Please", Toast.LENGTH_LONG).show();


                }

            }
        });


    }









}
