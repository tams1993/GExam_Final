package gko.app.gexam.student.generator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import gko.app.gexam.student.FontsOverride;
import gko.app.gexam.student.MainActivity;

/**
 * Created by tams1993 on 3/27/2015.
 */
public  class AlertDialoge extends Activity {


public static void Alert(final Activity activity,String title, String message ) {

    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
    builder1.setTitle(title);
    builder1.setMessage(message);
    builder1.setCancelable(true);
    builder1.setPositiveButton("OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });



    AlertDialog alert11 = builder1.create();
    alert11.show();

}

    public static void AlertExit(final Activity activity, String title, String message) {




        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        activity.finish();

                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);




                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();


    }






    public static void AlertConnection(final Activity activity, String title, String message) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        activity.finish();




                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    public static void AlertBackPress(final Activity activity, String title, String message) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();




                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

}
