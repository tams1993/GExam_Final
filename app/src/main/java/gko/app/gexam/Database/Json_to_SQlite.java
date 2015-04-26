package gko.app.gexam.Database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gko.app.gexam.student.Table;

/**
 * Created by MR.T on 4/3/2015.
 */
public class Json_to_SQlite {


    public void Classrooms(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("classrooms");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int strClassroomsID = jsonObject1.getInt("id");
                String strClassName = jsonObject1.getString("class");

                new Table(context).addClassrooms(strClassroomsID, strClassName);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }

    public void Student_Answer(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("student_answer");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int student_answer_idID = jsonObject1.getInt("id");
                int question_id = jsonObject1.getInt("question_id");
                int ans_option_id = jsonObject1.getInt("ans_option_id");

                new Table(context).addStudent_Answer(student_answer_idID, question_id, ans_option_id);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }


    public void Student_Unblock(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("student_unblock");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int student_unblock_id = jsonObject1.getInt("id");
                String std_id = jsonObject1.getString("std_id");
                String test_code = jsonObject1.getString("test_code");

                new Table(context).addStudent_unblock(student_unblock_id, std_id, test_code);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }


    public void Subject(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("subject");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                String subject_name = jsonObject1.getString("subject_name");
                int credit = jsonObject1.getInt("credit");
                String code = jsonObject1.getString("subject_code");

                new Table(context).addSubject(id, subject_name, credit,code);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }


    public void Student_Illegal(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("student_illegal");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                int status = jsonObject1.getInt("status");
                String std_id = jsonObject1.getString("std_id");
                int test_id = jsonObject1.getInt("test_code");

                new Table(context).addStudent_Illegal(id, status, std_id, test_id);

                Log.d("ERROR", "JSON to SQLITE student_illegal: COMPLETE");


            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }

    public void Course(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("course");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int strCourseID = jsonObject1.getInt("id");
                String strDate = jsonObject1.getString("date");
                String strIntervalTime = jsonObject1.getString("interval_time");
                String test_code = jsonObject1.getString("test_code");
                String teacher_name = jsonObject1.getString("teacher_name");
                String subject_code = jsonObject1.getString("subject_code");
                int question_amount = jsonObject1.getInt("question_amount");
                int status = jsonObject1.getInt("status");

                new Table(context).addCourse(strCourseID, strDate, strIntervalTime, question_amount, test_code, status, teacher_name, subject_code);

                Log.d("ERROR","JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }


    public void Teacher(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("teacher");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                String name = jsonObject1.getString("name");
                String surname = jsonObject1.getString("surname");
                int phone = jsonObject1.getInt("phone");
                String email = jsonObject1.getString("email");
                String username = jsonObject1.getString("username");
                String password = jsonObject1.getString("password");
                String invite_code = jsonObject1.getString("invite_code");
                int active = jsonObject1.getInt("active");

                new Table(context).addTeacher(id, name, surname, phone, email, username, password, invite_code,active);

                Log.d("ERROR","JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }


    public void Exam_Question(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("exam_question");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                int test_code = jsonObject1.getInt("test_code");
                int question_id = jsonObject1.getInt("question_id");
                int student_ans_id = jsonObject1.getInt("student_ans_id");

                new Table(context).addExam_Question(id, test_code,question_id,student_ans_id);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }

     public void Exam_Rule(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("exam_rule");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                int test_code = jsonObject1.getInt("test_code");
                String rule = jsonObject1.getString("rule");
                String teacher_name = jsonObject1.getString("teacher_name");

                new Table(context).addExam_Rule(id, test_code,rule,teacher_name);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }

    public void Questions(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("questions");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                String question = jsonObject1.getString("question");
                String photo = jsonObject1.getString("photo");
                String subject_code = jsonObject1.getString("subject_code");
                String teacher_name = jsonObject1.getString("teacher_name");
                String ans_option_id = jsonObject1.getString("ans_option_id");

                new Table(context).addQuestion(id, question,photo,subject_code,teacher_name,ans_option_id);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }

public void Students(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("students");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                String name = jsonObject1.getString("name");
                String surname = jsonObject1.getString("surname");
                int phone = jsonObject1.getInt("phone");
                String email = jsonObject1.getString("email");
                String username = jsonObject1.getString("username");
                String password = jsonObject1.getString("password");
                String photo = jsonObject1.getString("photo");
                String student_id = jsonObject1.getString("student_id");
                String class_name = jsonObject1.getString("class_name");

                new Table(context).addStudents(id, name,surname,phone,email,username,password,photo,student_id,class_name);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }



}


