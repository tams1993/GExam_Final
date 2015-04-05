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

    public static final String COLUMN_QUESTION_ID = "_id";
    public static final String COLUMN_QUESTION_QUESTION = "question";
    public static final String COLUMN_QUESTION_PHOTO = "photo";
    public static final String COLUMN_QUESTION_SUBJECT_CODE= "subject_code";
    public static final String COLUMN_QUESTION_TEAHCER_NAME= "teacher_name";
    public static final String COLUMN_QUESTION_ANS_OPTION_ID= "ans_option_id";

    public static final String COLUMN_EXAM_RULE_ID = "_id";
    public static final String COLUMN_EXAM_RULE_TEST_CODE = "test_code";
    public static final String COLUMN_EXAM_RULE_RULE = "rule";
    public static final String COLUMN_EXAM_RULE_TEACHER_NAME= "teacher_name";

    public static final String COLUMN_STUDENT_ANSWER_ID = "_id";
    public static final String COLUMN_STUDENT_ANSWER_ANS_OPTION_ID = "question_id";
    public static final String COLUMN_STUDENT_ANSWER_QUESTION_ID = "ans_option_id";

    public static final String COLUMN_STUDENT_ILLEGAL_ID = "_id";
    public static final String COLUMN_STUDENT_ILLEGAL_STATUS = "status";
    public static final String COLUMN_STUDENT_ILLEGAL_STD_ID = "std_id";
    public static final String COLUMN_STUDENT_ILLEGAL_TEST_ID = "test_code";



    public static final String COLUMN_STUDENTS_ID = "_id";
    public static final String COLUMN_STUDENTS_NAME = "name";
    public static final String COLUMN_STUDENTS_SURNAME = "surname";
    public static final String COLUMN_STUDENTS_PHONE = "phone";
    public static final String COLUMN_STUDENTS_EMAIL = "email";
    public static final String COLUMN_STUDENTS_USERNAME = "username";
    public static final String COLUMN_STUDENTS_PASSWORD = "password";
    public static final String COLUMN_STUDENTS_PHOTO = "photo";
    public static final String COLUMN_STUDENTS_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENTS_STUDENT_CLASS_NAME = "class_name";


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

    public long addStudent_Answer(int student_answer_id, int question_id, int ans_option_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUDENT_ANSWER_ID, student_answer_id);
        contentValues.put(COLUMN_STUDENT_ANSWER_QUESTION_ID, question_id);
        contentValues.put(COLUMN_STUDENT_ANSWER_ANS_OPTION_ID, ans_option_id);



        return writeSQlite.insert("student_answer", null, contentValues);


    }

    public long addStudent_Illegal(int id, int status, String std_id, int test_code) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUDENT_ILLEGAL_ID, id);
        contentValues.put(COLUMN_STUDENT_ILLEGAL_STATUS, status);
        contentValues.put(COLUMN_STUDENT_ILLEGAL_STD_ID, std_id);
        contentValues.put(COLUMN_STUDENT_ILLEGAL_TEST_ID, test_code);



        return writeSQlite.insert("student_illegal", null, contentValues);


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

public long addExam_Rule(int id, int test_code, String rule, String teacher_name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EXAM_RULE_ID, id);
        contentValues.put(COLUMN_EXAM_RULE_TEST_CODE, test_code);
        contentValues.put(COLUMN_EXAM_RULE_RULE, rule);
        contentValues.put(COLUMN_EXAM_RULE_TEACHER_NAME, teacher_name);



        return writeSQlite.insert("exam_rule", null, contentValues);


    }

public long addQuestion(int id, String question, String photo, String subject_code, String teacher_name, String ans_option_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUESTION_ID, id);
        contentValues.put(COLUMN_QUESTION_PHOTO, photo);
        contentValues.put(COLUMN_QUESTION_QUESTION, question);
        contentValues.put(COLUMN_QUESTION_SUBJECT_CODE, subject_code);
        contentValues.put(COLUMN_QUESTION_TEAHCER_NAME, teacher_name);
        contentValues.put(COLUMN_QUESTION_ANS_OPTION_ID, ans_option_id);



        return writeSQlite.insert("questions", null, contentValues);


    }

public long addStudents(int id, String name, String surname, int phone, String email, String username, String password, String photo, String student_id, String class_name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUDENTS_ID, id);
        contentValues.put(COLUMN_STUDENTS_NAME, photo);
        contentValues.put(COLUMN_STUDENTS_SURNAME, surname);
        contentValues.put(COLUMN_STUDENTS_PHONE, phone);
        contentValues.put(COLUMN_STUDENTS_EMAIL, email);
        contentValues.put(COLUMN_STUDENTS_USERNAME, username);
        contentValues.put(COLUMN_STUDENTS_PASSWORD, password);
        contentValues.put(COLUMN_STUDENTS_PHOTO, photo);
        contentValues.put(COLUMN_STUDENTS_STUDENT_ID, student_id);
        contentValues.put(COLUMN_STUDENTS_STUDENT_CLASS_NAME, class_name);



        return writeSQlite.insert("students", null, contentValues);


    }


}
