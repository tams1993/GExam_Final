package com.example.tams1993.scanner.Scanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause

    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Toast.makeText(this,rawResult.getText(),Toast.LENGTH_LONG).show();
//        Toast.makeText(this,rawResult.getBarcodeFormat().toString(),Toast.LENGTH_LONG).show();


        if (rawResult.getText().equals("admin")) {

            Intent intent = new Intent(ScannerActivity.this, ExamPage.class);
            startActivity(intent);
            finish();


        } else {

            Toast.makeText(this,"Wrong QR Code",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ScannerActivity.this, ScannerActivity.class);
            startActivity(intent);
            finish();


        }



        SharedPreferences sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        String qrText = sp.getString("QR_Code", String.valueOf(-1));
        Toast.makeText(this,qrText,Toast.LENGTH_LONG).show();


        Log.v("Scan", rawResult.getText()); // Prints scan results
        Log.v("Scan", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
    }
}
