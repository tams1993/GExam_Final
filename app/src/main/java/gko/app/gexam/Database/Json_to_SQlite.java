package gko.app.gexam.Database;

import android.content.Context;
import android.util.Log;

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

    public void Answer_Option(String strJSON, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArrayClassrooms = jsonObject.getJSONArray("answer_option");

            for (int i = 0; i < jsonArrayClassrooms.length(); i++) {

                JSONObject jsonObject1 = jsonArrayClassrooms.getJSONObject(i);
                int Answer_Option_ID = jsonObject1.getInt("id");
                String answer = jsonObject1.getString("answer");
                int correct = jsonObject1.getInt("correct");
                int question_id = jsonObject1.getInt("question_id");

                new Table(context).addAnswer_Option(Answer_Option_ID, answer,correct,question_id);

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
                int std_id = jsonObject1.getInt("std_id");
                int course_id = jsonObject1.getInt("course_id");
                int status = jsonObject1.getInt("status");

                new Table(context).addStudent_unblock(student_unblock_id, std_id, course_id,status);

                Log.d("GExam", "JSON to SQLITE student_unblock: COMPLETE");

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
                String code = jsonObject1.getString("code");

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

                String std_id = jsonObject1.getString("std_id");
                int course_id = jsonObject1.getInt("course_id");

                new Table(context).addStudent_Illegal(id, std_id, course_id);

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
                int strIntervalTime = jsonObject1.getInt("interval_time");
                String test_code = jsonObject1.getString("test_code");
                int teacher_id = jsonObject1.getInt("teacher_id");

                int question_amount = jsonObject1.getInt("question_amount");
                int status = jsonObject1.getInt("status");
                int subject_id = jsonObject1.getInt("subject_id");
                int class_id = jsonObject1.getInt("class_id");

                new Table(context).addCourse(strCourseID, strDate, strIntervalTime, question_amount, status, teacher_id, test_code,subject_id,class_id);

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


                new Table(context).addTeacher(id, name, surname, phone, email, username, password, invite_code);

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
                int course_id = jsonObject1.getInt("course_id");
                int question_id = jsonObject1.getInt("question_id");
                int student_ans_id = jsonObject1.getInt("student_ans_id");

                new Table(context).addExam_Question(id, course_id,question_id,student_ans_id);

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
                int course_id = jsonObject1.getInt("course_id");
                String rule = jsonObject1.getString("rule");
                int teacher_id = jsonObject1.getInt("teacher_id");

                new Table(context).addExam_Rule(id, course_id,rule,teacher_id);

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

                int teacher_id = jsonObject1.getInt("teacher_id");
                int subject_id = jsonObject1.getInt("subject_id");

                new Table(context).addQuestion(id, question,photo,teacher_id,subject_id);

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
//                int phone = jsonObject1.getInt("phone");
                String email = jsonObject1.getString("email");
                String username = jsonObject1.getString("username");
                String password = jsonObject1.getString("password");
                String photo = jsonObject1.getString("photo");
                String student_id = jsonObject1.getString("student_id");
                int class_id = jsonObject1.getInt("class_id");

                new Table(context).addStudents(id, name,surname,0,email,username,password,photo,student_id,class_id);

                Log.d("ERROR", "JSON to SQLITE: COMPLETE");

            }

        } catch (JSONException e) {


            e.printStackTrace();
        }


    }



}


