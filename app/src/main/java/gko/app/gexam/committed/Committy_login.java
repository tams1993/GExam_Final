package gko.app.gexam.committed;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;
import gko.app.gexam.committed.com_fragment.ComFragActivity;
import gko.app.gexam.student.SpinnerObject;

public class Committy_login extends ActionBarActivity {

    private EditText edtComUser, edtComPass;
    private Spinner spnCom;
    private CheckBox chbActiveCourse;
    private Button btnComLogin;
    private String CourseName;

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

        setContentView(R.layout.activity_committy_login);


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
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(getApplicationContext(), "Check Active Please", Toast.LENGTH_LONG).show();


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

    private void InitializeWidget() {

        edtComUser = (EditText) findViewById(R.id.edtComUser);
        edtComPass = (EditText) findViewById(R.id.edtComPass);
        spnCom = (Spinner) findViewById(R.id.spnCom);
        chbActiveCourse = (CheckBox) findViewById(R.id.chbActiveCourse);
        btnComLogin = (Button) findViewById(R.id.btnComLogin);


    }   //  end of InitializeWidget

    private void SpinnerList() {

        List<SpinnerObject> label = getAllLabelsSpinner();
        ArrayAdapter<SpinnerObject> spinnerArrayAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, label);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnCom.setAdapter(spinnerArrayAdapter);

    }

    public List< SpinnerObject> getAllLabelsSpinner(){
        List < SpinnerObject > labels = new ArrayList<SpinnerObject>();
        // Select All Query
        String selectQuery = "SELECT * FROM course c INNER JOIN subject s on c.subject_code = s.subject_code where c.status =?";
//        String selectQuery = "SELECT * FROM course";
//        String selectQuery = "SELECT * FROM subject";

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

//        int listSize = labels.size();
//
//        for (int i = 0; i<listSize; i++){
//            Log.d("Member name: ", String.valueOf(labels.get(i)));
//        }



//        int testcode = ( (SpinnerObject) spinner.getSelectedItem () ).getTestcode ();
//
//
//        Log.d("Cursor", "cursor 0  = " + testcode);


        Log.d("Cursor", "cursor 0  = " + cursor.getCount());
        Log.d("Cursor", "cursor column 0  = " + cursor.getColumnCount());
        Log.d("Cursor", "cursor columnindex 0  = " + cursor.getColumnIndex("status"));



        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return labels;
    }

}
