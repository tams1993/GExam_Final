package gko.app.gexam.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MR.T on 4/2/2015.
 */
public class Table {

    private OpenHelper openHelper;
    private SQLiteDatabase writeSQlite, readSQlite;
    public static final String COLUMN_CLASSROOMS_ID = "_id";
    public static final String COLUMN_CLASS_NAME = "class";

    public static final String COLUMN_COURSE_ID = "_id";
    public static final String COLUMN_COURSE_DATE = "date";
    public static final String COLUMN_COURSE_INTERVAL_TIME = "interval_time";
    public static final String COLUMN_COURSE_QUESTION_AMOUNT = "question_amount";
    public static final String COLUMN_COURSE_TEST_CODE = "test_code";
    public static final String COLUMN_COURSE_STATUS = "status";
    public static final String COLUMN_TEACHER_NAME = "teacher_name";
    public static final String COLUMN_SUBJECT_CODE = "subject_code";

    public static final String COLUMN_EXAM_QUESTION_ID = "_id";
    public static final String COLUMN_EXAM_QUESTION_TEST_CODE = "test_id";
    public static final String COLUMN_EXAM_QUESTION_QUESTION_ID = "question_id";
    public static final String COLUMN_EXAM_QUESTION_STUDENT_ANS_ID = "student_ans_id";


    public Table(Context context) {

        openHelper = new OpenHelper(context);
        writeSQlite = openHelper.getWritableDatabase();
        readSQlite = openHelper.getReadableDatabase();

    }

    public long addClassrooms(int strClassroomsID, String strClassName) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CLASSROOMS_ID, strClassroomsID);
        contentValues.put(COLUMN_CLASS_NAME, strClassName);



        return writeSQlite.insert("classrooms", null, contentValues);


    }

    public long addCourse(int course_id, String strdate, String strInterval_time, int question_amount, String test_code, int status, String teacher_name, String subject_code) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE_ID, course_id);
        contentValues.put(COLUMN_COURSE_DATE, strdate);
        contentValues.put(COLUMN_COURSE_INTERVAL_TIME, strInterval_time);
        contentValues.put(COLUMN_COURSE_QUESTION_AMOUNT, question_amount);
        contentValues.put(COLUMN_COURSE_TEST_CODE, test_code);
        contentValues.put(COLUMN_COURSE_STATUS, status);
        contentValues.put(COLUMN_TEACHER_NAME, test_code);
        contentValues.put(COLUMN_SUBJECT_CODE, subject_code);



        return writeSQlite.insert("course", null, contentValues);


    }

    public long addExam_Question(int id, int test_code, int question_id, int student_ans_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EXAM_QUESTION_ID, id);
        contentValues.put(COLUMN_EXAM_QUESTION_TEST_CODE, test_code);
        contentValues.put(COLUMN_EXAM_QUESTION_QUESTION_ID, question_id);
        contentValues.put(COLUMN_EXAM_QUESTION_STUDENT_ANS_ID, student_ans_id);



        return writeSQlite.insert("exam_question", null, contentValues);


    }


}
