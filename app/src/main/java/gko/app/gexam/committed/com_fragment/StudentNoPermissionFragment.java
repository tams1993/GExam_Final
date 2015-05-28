package gko.app.gexam.committed.com_fragment;


import android.app.ProgressDialog;
import android.content.Context;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentNoPermissionFragment extends Fragment {


    private RecyclerView recyclerView;
    private StudentNoPermissionAdapter adapter;
    private Button btnSubmit;
    private CheckBox chbPresent;
    private String ALERT_TITLE = "????????????????", ALERT_MESSAGE= "????????????????????. ???????????????????????? ???????????????????????????????????????????????";



//    public static final String URL_JSON = "http://192.168.1.5/gexam/db_connect.php";

    private SharedPreferences sp;

    private int class_id;

//    private Json_to_SQlite json_to_sQlite = new Json_to_SQlite();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        View layout = inflater.inflate(R.layout.fragment_student_no_permission, container, false);

        sp = this.getActivity().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);


        class_id = sp.getInt("class_id", -1);
//
        Log.d("GExam","class_id = " + class_id);



        recyclerView = (RecyclerView) layout.findViewById(R.id.studentNoPerRecycler);
        adapter = new StudentNoPermissionAdapter(getActivity(), getAllStudentNoPermission(class_id));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));







//        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "phetsarath.ttf");




        return layout;
    }

    public void deleteAll() {

        OpenHelper openHelper = new OpenHelper(getActivity());
        SQLiteDatabase db = openHelper.getReadableDatabase();
        db.execSQL("delete from student_unblock");
//        db.execSQL("TRUNCATE table student_unblock");
        db.close();

    }

//    public static List<StudentNoPermission> getData() {
//
//        List<StudentNoPermission> studentNoPermissions = new ArrayList<>();
//
//        String[] strings = {"hello", "hi", "test"};
//        for (int i = 0; i < strings.length; i++) {
//
//            StudentNoPermission studentNoPermission = new StudentNoPermission();
//            studentNoPermission.student = strings[i];
//
//            studentNoPermissions.add(studentNoPermission);
//
//
//        }
//
//        return studentNoPermissions;
//
//    }


    public List<StudentNoPermission> getAllStudentNoPermission(int classID){
        List < StudentNoPermission > labels = new ArrayList<StudentNoPermission>();
        // Select All Query
        String selectQuery = "SELECT st.name, st.surname FROM students st LEFT JOIN student_unblock su ON su.std_id = st._id Where class_id = "+classID +" AND su.status IS NULL";


        OpenHelper openHelper = new OpenHelper(getActivity());
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        Log.d("GExam", "All Student =  " + String.valueOf(cursor.getCount()));

        // looping through all rows and adding to list
        if ( cursor.moveToFirst () ) {
            do {
                labels.add (new StudentNoPermission(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("surname"))));

            } while (cursor.moveToNext());





        }


//        deleteAll();

//        new SimpleTask().execute(URL_JSON);

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
//            objPD.setMessage("???????????????...");
//            objPD.setCancelable(false);
//            objPD.setIndeterminate(false);
//
//            objPD.show();
//
//        }
//
//        protected String doInBackground(String... urls) {
//
//
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


//    }



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
            HttpPost objHttpPost = new HttpPost("http://192.168.1.3/GExam/db_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);



        } catch (Exception e) {

            Log.d("GExam", "Connect and Post Error ====>" + e.toString());

        }

    }   //  end of AddScoreToMySQL



}
