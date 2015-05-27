package gko.app.gexam.student;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import gko.app.gexam.Database.Json_to_SQlite;
import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;
import gko.app.gexam.student.generator.Contents;
import gko.app.gexam.student.generator.QRCodeEncoder;

public class QRActivity extends Activity implements OnClickListener{

    private String LOG_TAG = "GenerateQRCode";

    private Handler handler = new Handler();
    public static final String URL_JSON = "http://gexam.esy.es/GExam/db_connect.php";
    private Json_to_SQlite json_to_sQlite = new Json_to_SQlite();

    private Runnable refresh;
    private int Student_ID;
    private int status;

    @Override
    protected void onPause() {
        super.onPause();


        handler.removeCallbacks(refresh);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {

            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setEnterTransition(slide);

//            TransitionInflater inflater = TransitionInflater.from(this);
//            Transition transition = inflater.inflateTransition(R.transition.transtion_main_activity);
//            getWindow().setExitTransition(transition);

        }

        setContentView(R.layout.activity_qr);

        Button btnProceed = (Button) findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        Student_ID = sp.getInt("std_id", -1);

        Log.d("GExam", "std_id = " + Student_ID);


//        Refresh();

        GenerateQRCode(String.valueOf(Student_ID));







    }

    private void Refresh() {

        refresh = new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(QRActivity.this, CoureseDetail_Activity.class));
                handler.postDelayed(refresh, 3000);

            }
        };


        handler.post(refresh);

    }

    private void GenerateQRCode(String qrText) {



        //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3/4;



        //Encode with a QR Code image
        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrText,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.QR_CODE.toString(),
                smallerDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            ImageView myImage = (ImageView) findViewById(R.id.imageView1);
            myImage.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


//        Toast.makeText(getApplicationContext(),qrText,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();

        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        }


    }

    private class SimpleTask extends AsyncTask<String, Void, String> {


        ProgressDialog objPD;
        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar


            objPD = new ProgressDialog(QRActivity.this);
            objPD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            objPD.setTitle("Loading...");
            objPD.setMessage("???????????...");
            objPD.setCancelable(false);
            objPD.setIndeterminate(false);

            objPD.show();

        }

        protected String doInBackground(String... urls) {



            return JSON();
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar
//            Log.d("Emergency", jsonString);
//            Toast.makeText(QRActivity.this, jsonString, Toast.LENGTH_LONG).show();


            deleteAll();


            json_to_sQlite.Student_Unblock(jsonString, QRActivity.this);

            objPD.dismiss();


        }




    }



    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.btnProceed:



                new SimpleTask().execute(URL_JSON);



                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                         status = getStatus(Student_ID);

                Log.d("GExam", "status = " + status);

                if (status == 1) {

                    ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(QRActivity.this, null);

                    Intent intent = new Intent(QRActivity.this, CoureseDetail_Activity.class);
                    startActivity(intent,compat.toBundle());



                } else {

                    TextView txtWait = (TextView) findViewById(R.id.txtWait);
                    txtWait.setText(R.string.wait);


                }
                    }
                }, 3000);






                break;

            // More buttons go here (if any) ...

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

            Log.d("GExam", "Connected HTTP Success !");


        } catch (Exception e) {

            Log.d("GExam", "Error Connect to : " + e.toString());
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

    public int getStatus(int student_id){
        // Select All Query
        String selectQuery = "SELECT * FROM student_unblock where std_id =?";


        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(student_id)});

        cursor.moveToFirst();



        Log.d("GExam", "cursor.size = " + cursor.getCount());



        if (cursor != null) {


            status = cursor.getInt(cursor.getColumnIndex("status"));


        }

        Log.d("GExam", "STATUS = " + String.valueOf(status));


        cursor.close();

        return status;
    }

    public void deleteAll()
    {
        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
            db.execSQL("delete from student_unblock");
//        db.execSQL("TRUNCATE table student_unblock");
                db.close();
    }



}