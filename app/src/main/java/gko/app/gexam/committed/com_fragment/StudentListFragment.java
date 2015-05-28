package gko.app.gexam.committed.com_fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity;
import com.melnykov.fab.FloatingActionButton;
import com.xgc1986.ripplebutton.widget.RippleButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.Database.Json_to_SQlite;
import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;
import gko.app.gexam.student.FontsOverride;
import gko.app.gexam.student.generator.AlertDialoge;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment {


    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private RippleButton btnSubmit;
    private TextView txtAllStudent;
    private CheckBox chbPresent;
    private String ALERT_TITLE = "????????????????", ALERT_MESSAGE= "????????????????. ??????????????????????????????????????????????????????.";



    public static final String URL_JSON = "http://gexam.esy.es/GExam/db_connect.php";

    private SharedPreferences sp;

    private int course_id;

    private Json_to_SQlite json_to_sQlite = new Json_to_SQlite();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        View layout = inflater.inflate(R.layout.fragment_student_list, container, false);

        sp = this.getActivity().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);


        course_id = sp.getInt("course_id_committee", -1);

        Log.d("GExam", "course_id = " + course_id);



        txtAllStudent = (TextView) layout.findViewById(R.id.txtAllStudent);
        chbPresent = (CheckBox) layout.findViewById(R.id.chbPresent);
        recyclerView = (RecyclerView) layout.findViewById(R.id.studentList);
        adapter = new StudentAdapter(getActivity(),getAllStudent(course_id),"phetsarath.ttf");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);



        fab.setImageResource(R.drawable.ic_basic3_086_qr_code_search_scan_128);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity(), CaptureActivity.class));
                getActivity().finish();
            }
        });








//        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "phetsarath.ttf");

        btnSubmit = (RippleButton) layout.findViewById(R.id.btnCommitteeSubmit);
        int buttonColor = getResources().getColor(R.color.ColorPrimaryDark);
        int rippleColor = getResources().getColor(R.color.accentColor);

        btnSubmit.setColors(buttonColor,rippleColor);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InsertDataTask().execute();


            }
        });


        return layout;
    }

    public void deleteAll() {

        OpenHelper openHelper = new OpenHelper(getActivity());
        SQLiteDatabase db = openHelper.getReadableDatabase();
        db.execSQL("delete from student_unblock");
//        db.execSQL("TRUNCATE table student_unblock");
        db.close();

    }


    public List<Student> getAllStudent(int course_id){




//        new SimpleTask().execute(URL_JSON);

        List < Student > labels = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT * FROM student_unblock sub INNER JOIN students st ON sub.std_id = st._id INNER JOIN course c ON c._id = sub.course_id where course_id =" + course_id;


        OpenHelper openHelper = new OpenHelper(getActivity());
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        Log.d("GExam", "All Student =  " + String.valueOf(cursor.getCount()));
        txtAllStudent.setText(String.valueOf(cursor.getCount()));


        // looping through all rows and adding to list
        if ( cursor.moveToFirst () ) {
            do {
                labels.add (new Student(cursor.getString(cursor.getColumnIndex("name")),cursor.getInt(3),cursor.getInt(cursor.getColumnIndex("std_id"))));

            } while (cursor.moveToNext());





        }






        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return labels;
    }

//    private class SimpleTask extends AsyncTask<String, Void, String> {
//
//
//        ProgressDialog objPD;
//        @Override
//        protected void onPreExecute() {
//            // Create Show ProgressBar
//
//
//            objPD = new ProgressDialog(getActivity());
//            objPD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            objPD.setTitle("Loading...");
//            objPD.setMessage("???????????...");
//            objPD.setCancelable(false);
//            objPD.setIndeterminate(false);
//
//
//
//
//            objPD.show();
//
//        }
//
//        protected String doInBackground(String... urls) {
//
//        deleteAll();
//
//            return JSON();
//        }
//
//        protected void onPostExecute(String jsonString)  {
//            // Dismiss ProgressBar
////            Log.d("Emergency", jsonString);
////            Toast.makeText(getActivity(), jsonString, Toast.LENGTH_LONG).show();
//
//
//
//
//
//            json_to_sQlite.Student_Unblock(jsonString, getActivity());
//
//            objPD.dismiss();
//
//
//        }
//
//
//
//
//
//
//
//        public String JSON() {
//
//            InputStream objInputStream = null;
//            String strJSON = "";
//
//            try {
//
//                HttpClient objHttpClient = new DefaultHttpClient();
//                HttpPost objHttpPost = new HttpPost(URL_JSON);
//                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
//                HttpEntity objHttpEntity = objHttpResponse.getEntity();
//                objInputStream = objHttpEntity.getContent();
//
//                Log.d("GExam", "Connected HTTP Success !");
//
//
//            } catch (Exception e) {
//
//                Log.d("GExam", "Error Connect to : " + e.toString());
//            }
//
//
//            //create strJSON
//            try {
//
//                BufferedReader objBufferesReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
//                StringBuilder objStrBuilder = new StringBuilder();
//                String strLine = null;
//
//                while ((strLine = objBufferesReader.readLine()) != null) {
//                    objStrBuilder.append(strLine);
//                }
//
//                objInputStream.close();
//                strJSON = objStrBuilder.toString();
//
//                Log.d("Emergency", "Connected JSON Success !");
//
//
//            } catch (Exception e) {
//                Log.d("Emergency", "Error Convert To JSON :" + e.toString());
//            }
//
//
//            return strJSON;
//
//        }
//
//
//
//
//
//
//
//    }


    public class InsertDataTask extends AsyncTask<String, Void, String> {


        ProgressDialog objPD;
        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar


            objPD = new ProgressDialog(getActivity());
            objPD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            objPD.setTitle("Loading...");
            objPD.setMessage("???????????...");
            objPD.setCancelable(false);
            objPD.setIndeterminate(false);

            objPD.show();

        }

        protected String doInBackground(String... urls) {


            String data = "";

            List<Student> studentList = adapter.getStudentList();


            for (int i = 0; i < studentList.size(); i++) {
                Student singleStudent = studentList.get(i);


                if (singleStudent.isSelected()) {

                    data = data + "\n" + singleStudent.getStd_id();


                    AddStudentIllegalToMySQL(1,singleStudent.getStd_id(),course_id);

                }

            }


//                Toast.makeText(getActivity(),
//                        "Selected Students: \n" + data, Toast.LENGTH_LONG)
//                        .show();

            String testcode = sp.getString("testcode", "No value");

            UpdateCourseStatus(testcode);


            return null;
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar


            AlertDialoge.AlertExit(getActivity(), ALERT_TITLE, ALERT_MESSAGE);


            objPD.dismiss();


        }







    }



    public void AddStudentIllegalToMySQL(int status, int std_id, int course_id) {

        if (Build.VERSION.SDK_INT > 7) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }

        //  Connect and Post

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("status", String.valueOf(status)));
            objNameValuePairs.add(new BasicNameValuePair("std_id", String.valueOf(std_id)));
            objNameValuePairs.add(new BasicNameValuePair("course_id", String.valueOf(course_id)));



            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://gexam.esy.es/GExam/db_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);



        } catch (Exception e) {

            Log.d("GExam", "Connect and Post Error ====>" + e.toString());

        }

    }   //  end of AddScoreToMySQL


    public void UpdateCourseStatus(String testcode) {

        if (Build.VERSION.SDK_INT > 7) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }

        //  Connect and Post

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("testcode", testcode));




            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://gexam.esy.es/GExam/db_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);



        } catch (Exception e) {

            Log.d("GExam", "Connect and Post Error ====>" + e.toString());

        }

    }   //  end of AddScoreToMySQL



}
