package gko.app.gexam.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.Database.OpenHelper;

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
    public static final String COLUMN_COURSE_COURSE_ID = "course_id";
    public static final String COLUMN_COURSE_STATUS = "status";
    public static final String COLUMN_COURSE_TEACHER_ID = "teacher_id";
    public static final String COLUMN_COURSE_TEST_CODE = "test_code";
    public static final String COLUMN_COURSE_SUBJECT_ID = "subject_id";
    public static final String COLUMN_COURSE_CLASS_ID = "class_id";

    public static final String COLUMN_EXAM_QUESTION_ID = "_id";
    public static final String COLUMN_EXAM_QUESTION_TEST_CODE = "course_id";
    public static final String COLUMN_EXAM_QUESTION_QUESTION_ID = "question_id";
    public static final String COLUMN_EXAM_QUESTION_STUDENT_ANS_ID = "student_ans_id";


    public static final String COLUMN_SUBJECT_ID= "_id";
    public static final String COLUMN_SUBJECT_SUBJECT_NAME = "subject_name";
    public static final String COLUMN_SUBJECT_CREDIT = "credit";
    public static final String COLUMN_SUBJECT_CODE = "code";


    public static final String COLUMN_QUESTION_ID = "_id";
    public static final String COLUMN_QUESTION_QUESTION = "question";
    public static final String COLUMN_QUESTION_PHOTO = "photo";
    public static final String COLUMN_QUESTION_SUBJECT_ID= "subject_id";
    public static final String COLUMN_QUESTION_TEAHCER_ID= "teacher_id";


    public static final String COLUMN_EXAM_RULE_ID = "_id";
    public static final String COLUMN_EXAM_RULE_TEST_CODE = "course_id";
    public static final String COLUMN_EXAM_RULE_RULE = "rule";
    public static final String COLUMN_EXAM_RULE_TEACHER_ID= "teacher_id";

    public static final String COLUMN_STUDENT_ANSWER_ID = "_id";
    public static final String COLUMN_STUDENT_ANSWER_ANS_OPTION_ID = "question_id";
    public static final String COLUMN_STUDENT_ANSWER_QUESTION_ID = "ans_option_id";

    public static final String COLUMN_STUDENT_UNBLOCK_ID = "_id";
    public static final String COLUMN_STUDENT_UNBLOCK_STD_ID = "std_id";
    public static final String COLUMN_STUDENT_UNBLOCK_TESTCODE= "course_id";

    public static final String COLUMN_STUDENT_ILLEGAL_ID = "_id";
    public static final String COLUMN_STUDENT_ILLEGAL_STATUS = "status";
    public static final String COLUMN_STUDENT_ILLEGAL_STD_ID = "std_id";
    public static final String COLUMN_STUDENT_ILLEGAL_TEST_ID = "course_id";



    public static final String COLUMN_STUDENTS_ID = "_id";
    public static final String COLUMN_STUDENTS_NAME = "name";
    public static final String COLUMN_STUDENTS_SURNAME = "surname";
    public static final String COLUMN_STUDENTS_PHONE = "phone";
    public static final String COLUMN_STUDENTS_EMAIL = "email";
    public static final String COLUMN_STUDENTS_USERNAME = "username";
    public static final String COLUMN_STUDENTS_PASSWORD = "password";
    public static final String COLUMN_STUDENTS_PHOTO = "photo";
    public static final String COLUMN_STUDENTS_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENTS_STUDENT_CLASS_ID = "class_id";


    public static final String COLUMN_TEACHER_ID = "_id";
    public static final String COLUMN_TEACHER_TEACHER_NAME = "name";
    public static final String COLUMN_TEACHER_SURNAME = "surname";
    public static final String COLUMN_TEACHER_PHONE = "phone";
    public static final String COLUMN_TEACHER_EMAIL = "email";
    public static final String COLUMN_TEACHER_USERNAME = "username";
    public static final String COLUMN_TEACHER_PASSWORD = "password";
    public static final String COLUMN_TEACHER_INVITE_CODE = "invite_code";
    public static final String COLUMN_TEACHER_ACTIVE = "active";




    public static final String COLUMN_ANSWER_OPTION_ID = "_id";
    public static final String COLUMN_ANSWER_OPTION_ANSWER = "answer";
    public static final String COLUMN_ANSWER_OPTION_CORRECT = "correct";
    public static final String COLUMN_ANSWER_OPTION_QUESTION_ID = "question_id";




    public Table(Context context) {

        openHelper = new OpenHelper(context);
        writeSQlite = openHelper.getWritableDatabase();
        readSQlite = openHelper.getReadableDatabase();

    }


    public long addAnswer_Option(int id, String answer, int correct, int question_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ANSWER_OPTION_ID, id);
        contentValues.put(COLUMN_ANSWER_OPTION_ANSWER, answer);
        contentValues.put(COLUMN_ANSWER_OPTION_CORRECT, correct);
        contentValues.put(COLUMN_ANSWER_OPTION_QUESTION_ID, question_id);



        return writeSQlite.insert("answer_option", null, contentValues);


    }


    public long addClassrooms(int strClassroomsID, String strClassName) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CLASSROOMS_ID, strClassroomsID);
        contentValues.put(COLUMN_CLASS_NAME, strClassName);



        return writeSQlite.insert("classrooms", null, contentValues);


    }

    public long addStudent_unblock(int id, String std_id, int course_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUDENT_UNBLOCK_ID, id);
        contentValues.put(COLUMN_STUDENT_UNBLOCK_STD_ID, std_id);
        contentValues.put(COLUMN_STUDENT_UNBLOCK_TESTCODE, course_id);



        return writeSQlite.insert("student_unblock", null, contentValues);


    }

    public long addStudent_Answer(int student_answer_id, int question_id, int ans_option_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUDENT_ANSWER_ID, student_answer_id);
        contentValues.put(COLUMN_STUDENT_ANSWER_QUESTION_ID, question_id);
        contentValues.put(COLUMN_STUDENT_ANSWER_ANS_OPTION_ID, ans_option_id);



        return writeSQlite.insert("student_answer", null, contentValues);


    }

    public long addStudent_Illegal(int id, int status, String std_id, int course_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUDENT_ILLEGAL_ID, id);
        contentValues.put(COLUMN_STUDENT_ILLEGAL_STATUS, status);
        contentValues.put(COLUMN_STUDENT_ILLEGAL_STD_ID, std_id);
        contentValues.put(COLUMN_STUDENT_ILLEGAL_TEST_ID, course_id);



        return writeSQlite.insert("student_illegal", null, contentValues);


    }

    public long addSubject(int id, String subject_name, int credit, String code) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SUBJECT_ID, id);
        contentValues.put(COLUMN_SUBJECT_SUBJECT_NAME, subject_name);
        contentValues.put(COLUMN_SUBJECT_CREDIT, credit);
        contentValues.put(COLUMN_SUBJECT_CODE, code);



        return writeSQlite.insert("subject", null, contentValues);


    }

    public long addCourse(int id, String strdate, int strInterval_time, int question_amount, int status, int teacher_id, String code, int subject_id, int class_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE_ID, id);
        contentValues.put(COLUMN_COURSE_DATE, strdate);
        contentValues.put(COLUMN_COURSE_INTERVAL_TIME, strInterval_time);
        contentValues.put(COLUMN_COURSE_QUESTION_AMOUNT, question_amount);

        contentValues.put(COLUMN_COURSE_STATUS, status);
        contentValues.put(COLUMN_COURSE_TEACHER_ID, teacher_id);
        contentValues.put(COLUMN_COURSE_TEST_CODE, code);
        contentValues.put(COLUMN_COURSE_SUBJECT_ID, subject_id);
        contentValues.put(COLUMN_COURSE_CLASS_ID, class_id);



        return writeSQlite.insert("course", null, contentValues);


    }

    public long addTeacher(int id, String name, String surname, int phone,String email, String username, String password, String invite_code, int active) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEACHER_ID, id);
        contentValues.put(COLUMN_TEACHER_TEACHER_NAME, name);
        contentValues.put(COLUMN_TEACHER_SURNAME, surname);
        contentValues.put(COLUMN_TEACHER_PHONE, phone);
        contentValues.put(COLUMN_TEACHER_EMAIL, email);
        contentValues.put(COLUMN_TEACHER_USERNAME, username);
        contentValues.put(COLUMN_TEACHER_PASSWORD, password);
        contentValues.put(COLUMN_TEACHER_INVITE_CODE, invite_code);
        contentValues.put(COLUMN_TEACHER_ACTIVE, active);



        return writeSQlite.insert("teacher", null, contentValues);


    }

    public long addExam_Question(int id, int course_id, int question_id, int student_ans_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EXAM_QUESTION_ID, id);
        contentValues.put(COLUMN_EXAM_QUESTION_TEST_CODE, course_id);
        contentValues.put(COLUMN_EXAM_QUESTION_QUESTION_ID, question_id);
        contentValues.put(COLUMN_EXAM_QUESTION_STUDENT_ANS_ID, student_ans_id);



        return writeSQlite.insert("exam_question", null, contentValues);


    }

public long addExam_Rule(int id, int course_id, String rule, int teacher_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EXAM_RULE_ID, id);
        contentValues.put(COLUMN_EXAM_RULE_TEST_CODE, course_id);
        contentValues.put(COLUMN_EXAM_RULE_RULE, rule);
        contentValues.put(COLUMN_EXAM_RULE_TEACHER_ID, teacher_id);



        return writeSQlite.insert("exam_rule", null, contentValues);


    }

public long addQuestion(int id, String question, String photo, int teacher_id, int subject_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUESTION_ID, id);
        contentValues.put(COLUMN_QUESTION_PHOTO, photo);
        contentValues.put(COLUMN_QUESTION_QUESTION, question);

        contentValues.put(COLUMN_QUESTION_TEAHCER_ID, teacher_id);
        contentValues.put(COLUMN_QUESTION_SUBJECT_ID, subject_id);



        return writeSQlite.insert("questions", null, contentValues);


    }

public long addStudents(int id, String name, String surname, int phone, String email, String username, String password, String photo, String student_id, int class_id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUDENTS_ID, id);
        contentValues.put(COLUMN_STUDENTS_NAME, name);
        contentValues.put(COLUMN_STUDENTS_SURNAME, surname);
        contentValues.put(COLUMN_STUDENTS_PHONE, phone);
        contentValues.put(COLUMN_STUDENTS_EMAIL, email);
        contentValues.put(COLUMN_STUDENTS_USERNAME, username);
        contentValues.put(COLUMN_STUDENTS_PASSWORD, password);
        contentValues.put(COLUMN_STUDENTS_PHOTO, photo);
        contentValues.put(COLUMN_STUDENTS_STUDENT_ID, student_id);
        contentValues.put(COLUMN_STUDENTS_STUDENT_CLASS_ID, class_id);



        return writeSQlite.insert("students", null, contentValues);


    }

    public String[] AuthenStudent(String strUser) {


        try {


            String arrayData[] = null;

            Cursor objCursor = readSQlite.query("students" , new String[]
                    {COLUMN_STUDENTS_ID,COLUMN_STUDENTS_USERNAME,COLUMN_STUDENTS_PASSWORD},COLUMN_STUDENTS_USERNAME+"=?",new String[]{String.valueOf(strUser)},null,null,null,null);

            Log.d("Cursor count", "Cursor Columncount:" + objCursor.getColumnCount());
            Log.d("Cursor count", "Cursor count:" + objCursor.getCount());



            if (objCursor != null) {

                if (objCursor.moveToFirst()) {

                    arrayData = new String[objCursor.getColumnCount()];

                    arrayData[0] = objCursor.getString(0);
                    arrayData[1] = objCursor.getString(1);
                    arrayData[2] = objCursor.getString(2);

                }

            }
//            objCursor.close();
            Log.d("Cursor count", "Cursor Columncount:" + objCursor.getColumnCount());
            Log.d("Cursor count", "Cursor count:" + objCursor.getCount());
            return arrayData;

        } catch (Exception e) {
            Log.d("Error Authen", "No user in database");



            return null;
        }



    }

    public List<Rule> rule(Context context) {




            String selectQuery = "SELECT rule FROM exam_rule r INNER JOIN course c on r.course_id = c._id";




            OpenHelper openHelper = new OpenHelper(context);
            openHelper.getWritableDatabase();
            Cursor c = writeSQlite.rawQuery(selectQuery,null);
            Log.e("Data Count", String.valueOf(c.getCount()));


        List<Rule> RuleArray = new ArrayList<>();
        c.moveToFirst();

        while (!c.isAfterLast()) {

            Rule current = new Rule();
            current.title = c.getString(c.getColumnIndex("rule"));

            RuleArray.add(current);

            c.moveToNext();


        }

        Log.e("Data Count", String.valueOf(RuleArray));

        return RuleArray;


    }








    public Cursor ReadAllDataCourse() {

        Cursor objCursor = readSQlite.query("course",
                new String[]{COLUMN_COURSE_ID, COLUMN_COURSE_COURSE_ID,COLUMN_COURSE_INTERVAL_TIME,COLUMN_COURSE_QUESTION_AMOUNT,COLUMN_COURSE_STATUS, COLUMN_COURSE_TEST_CODE},
                null,null,null,null,null);
        if (objCursor != null) {
            objCursor.moveToFirst();

        }

        return objCursor;

    }   //  end of ReadAllDataCourse


//    public List<StudentsObject> Authentication(Context context, String strUser){
//        List < StudentsObject > labels = new ArrayList<StudentsObject>();
//        // Select All Query
//        String selectQuery = "SELECT * FROM student_illegal s INNER JOIN course c on s.test_code = c.test_code INNER JOIN students st on s.std_id = st.student_id where c.status =? AND st.username =?";
//
//
//        OpenHelper openHelper = new OpenHelper(context);
//        SQLiteDatabase db = openHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery,new String[]{"1",strUser});
//
//        // looping through all rows and adding to list
//        if ( cursor.moveToFirst () ) {
//            do {
//                labels.add (new StudentsObject(cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(13),cursor.getString(14),cursor.getString(16),cursor.getString(17),cursor.getString(18),cursor.getInt(15),cursor.getString(21)));
//
//                StudentsObject Showdata = labels.get(1);
//                Log.d("Show data", "Showdata" + Showdata);
//            } while (cursor.moveToNext());
//
//
//
//
//
//        }
//
//
//
////        show what inside List<SpinnerObject>
//
////        int listSize = labels.size();
////
////        for (int i = 0; i<listSize; i++){
////            Log.d("Member name: ", String.valueOf(labels.get(i)));
////        }
//
//
//
////        int testcode = ( (SpinnerObject) spinner.getSelectedItem () ).getTestcode ();
////
////
////        Log.d("Cursor", "cursor 0  = " + testcode);
//
//
//        Log.d("Cursor", "cursor 0  = " + cursor.getCount());
//        Log.d("Cursor", "cursor column 0  = " + cursor.getColumnCount());
//        Log.d("Cursor", "cursor columnindex 0  = " + cursor.getColumnIndex("status"));
//
//
//
//        // closing connection
//        cursor.close();
//        db.close();
//
//        // returning labels
//        return labels;
//    }


}
