package gko.app.gexam.Database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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




}


