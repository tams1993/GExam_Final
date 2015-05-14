package gko.app.gexam.committed.com_fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment {


    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private Button btnSubmit;
    private CheckBox chbPresent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View layout = inflater.inflate(R.layout.fragment_student_list, container, false);

        chbPresent = (CheckBox) layout.findViewById(R.id.chbPresent);
        recyclerView = (RecyclerView) layout.findViewById(R.id.studentList);
        adapter = new StudentAdapter(getActivity(),getAllStudent(),"phetsarath.ttf");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



//        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "phetsarath.ttf");

        btnSubmit = (Button) layout.findViewById(R.id.btnCommitteeSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = "";

                List<Student> studentList = adapter.getStudentList();


                for (int i = 0; i < studentList.size(); i++) {
                    Student singleStudent = studentList.get(i);


                    if (singleStudent.isSelected()) {

                        data = data + "\n" + singleStudent.getStudent();

                    }

                }

                Toast.makeText(getActivity(),
                        "Selected Students: \n" + data, Toast.LENGTH_LONG)
                        .show();

            }
        });


        return layout;
    }




    public List<Student> getAllStudent(){
        List < Student > labels = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT * FROM student_unblock sub INNER JOIN students st ON sub.std_id = st._id INNER JOIN course c ON c._id = sub.course_id";


        OpenHelper openHelper = new OpenHelper(getActivity());
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        Log.d("GExam", "All Student =  " + String.valueOf(cursor.getCount()));

        // looping through all rows and adding to list
        if ( cursor.moveToFirst () ) {
            do {
                labels.add (new Student(cursor.getString(cursor.getColumnIndex("name")),cursor.getInt(3)));

            } while (cursor.moveToNext());





        }

        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return labels;
    }



}
