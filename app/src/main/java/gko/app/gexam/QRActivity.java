package gko.app.gexam;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import gko.app.gexam.generator.Contents;
import gko.app.gexam.generator.QRCodeEncoder;

public class QRActivity extends Activity implements OnClickListener{

    private String LOG_TAG = "GenerateQRCode";

    private Handler handler = new Handler();

    private Runnable refresh;

    @Override
    protected void onPause() {
        super.onPause();


        handler.removeCallbacks(refresh);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        String UserName = sp.getString("USER", String.valueOf(-1));

        GenerateQRCode(UserName);


//                SharedPreferences.Editor editor = sp.edit();
//                editor.remove("QR_Code");
//                editor.commit();


//        refresh = new Runnable() {
//            @Override
//            public void run() {
//
//
//
//                if (qrText.equals("admin")) {
//
//                    startActivity(new Intent(GenerateQRCodeActivity.this, ExamPage.class));
//                    finish();
//
//
//                } else {
//
//                    Toast.makeText(getApplicationContext(),"Wrong QR code", Toast.LENGTH_SHORT).show();
//
//                }
//
//                handler.postDelayed(refresh, 3000);
//
//            }
//        };
//
//
//        handler.post(refresh);






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


        Toast.makeText(getApplicationContext(),qrText,Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.button1:
                EditText qrInput = (EditText) findViewById(R.id.qrInput);
                String qrInputText = qrInput.getText().toString();

//                SharedPreferences sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString("QR_Code", qrInputText);
//                editor.commit();

                Log.v(LOG_TAG, qrInputText);

                GenerateQRCode(qrInputText);


                break;

            // More buttons go here (if any) ...

        }
    }



}