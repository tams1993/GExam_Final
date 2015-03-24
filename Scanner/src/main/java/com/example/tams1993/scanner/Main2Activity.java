package com.example.tams1993.scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.dd.CircularProgressButton;
import com.example.tams1993.scanner.generator.GenerateQRCodeActivity;
import com.google.zxing.client.android.CaptureActivity;



public class Main2Activity extends Activity {


    private Button btnScanner, btnGenerator;

    private CircularProgressButton circularProgressButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        btnGenerator = (Button) findViewById(R.id.btnGenerator);
        btnScanner = (Button) findViewById(R.id.btnScanner);
        circularProgressButton = (CircularProgressButton) findViewById(R.id.btnWithText);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                circularProgressButton.setProgress(99);
                circularProgressButton.setIndeterminateProgressMode(true);
            }
        });


        btnGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Main2Activity.this, GenerateQRCodeActivity.class);
                startActivity(intent);


            }
        });


        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main2Activity.this, CaptureActivity.class);
                startActivity(intent);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
