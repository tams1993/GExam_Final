package gko.app.gexam.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by MR.T on 4/2/2015.
 */
public class OpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GExam.db";
    private static final String TABLE_NAME = "classrooms";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE_CLASSROOMS = "create table classrooms (_id integer primary key," + " class VARCHAR(255))";

    private static final String DATABASE_CREATE_COURSE = "create table course (_id integer primary key, " + " date datetime, interval_time time, question_amount int(10), test_code VARCHAR(255),status INT(1), teacher_name VARCHAR(255), subject_code VARCHAR(255))";
    private static final String DATABASE_CREATE_ANSWER_OPTION = "create table answer_option (_id integer primary key, " + " answer INT(1), correct INT(1), question_id int(10))";
    private static final String DATABASE_CREATE_STUDENT_ANSWER = "create table student_answer (_id integer primary key, " + " answer_option_od INT(10), question_id INT(10))";
    private static final String DATABASE_CREATE_STUDENT_UNBLOCK = "create table student_unblock (_id integer primary key, " + " std_id INT(10), test_code VARCHAR(255))";
    private static final String DATABASE_CREATE_SUBJECT= "create table subject (_id integer primary key, " + " subject_name VARCHAR(255), credit INT(5), subject_code VARCHAR(255))";
    private static final String DATABASE_CREATE_STUDENT_ILLEGAL = "create table student_illegal (_id integer primary key, " + " std_id INT(10), test_id INT(10), status INT(10))";
    private static final String DATABASE_CREATE_EXAM_QUESTION = "create table exam_question (_id integer primary key, " + " test_id INT(10), question_id INT(10), student_ans_id int(10))";
    private static final String DATABASE_CREATE_EXAM_RULE = "create table exam_rule (_id integer primary key, " + " test_code INT(10), rule VARCHAR(255), teacher_name VARCHAR(255))";
    private static final String DATABASE_CREATE_QUESTION = "create table questions (_id integer primary key, " + " subject_code VARCHAR(255), question VARCHAR(255), teacher_name int(255),photo VARCHAR(255),ans_option_id INT(10))";
    private static final String DATABASE_CREATE_STUDENTS = "create table students (_id integer primary key, " + " phone INT(10), name VARCHAR(255), surname VARCHAR(255),photo VARCHAR(255),email VARCHAR(255),username VARCHAR(255),password VARCHAR(255), student_id VARCHAR(255),class_name VARCHAR(255))";
    private static final String DATABASE_CREATE_TEACHERS = "create table teachers (_id integer primary key, " + " phone INT(10), name VARCHAR(255), surname VARCHAR(255),photo VARCHAR(255),email VARCHAR(255),username VARCHAR(255),password VARCHAR(255), teacher_id VARCHAR(255),class_id VARCHAR(255), active INT(2))";
    private static final String DROP_TABLE = "DROP TABLE" + TABLE_NAME + "IF EXISTS";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


            db.execSQL(DATABASE_CREATE_CLASSROOMS);
            db.execSQL(DATABASE_CREATE_EXAM_RULE);
            db.execSQL(DATABASE_CREATE_QUESTION);
            db.execSQL(DATABASE_CREATE_STUDENTS);
            db.execSQL(DATABASE_CREATE_TEACHERS);
            db.execSQL(DATABASE_CREATE_SUBJECT);
            db.execSQL(DATABASE_CREATE_STUDENT_ILLEGAL);
            db.execSQL(DATABASE_CREATE_EXAM_QUESTION);
            db.execSQL(DATABASE_CREATE_STUDENT_UNBLOCK);
            db.execSQL(DATABASE_CREATE_ANSWER_OPTION);
            db.execSQL(DATABASE_CREATE_STUDENT_ANSWER);
            db.execSQL(DATABASE_CREATE_COURSE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



//            db.execSQL(DROP_TABLE);



    }
}
