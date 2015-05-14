package gko.app.gexam.committed.com_fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import gko.app.gexam.Database.OpenHelper;
import gko.app.gexam.R;

/**
 * Created by MR.T on 5/12/2015.
 */
    public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

        private LayoutInflater inflator;
    private int status;
    Typeface typeface;

    List<Student> students = Collections.emptyList();

        public StudentAdapter(Context context, List<Student> students, String Font) {

            inflator = LayoutInflater.from(context);
            this.students = students;
            typeface = Typeface.createFromAsset(context.getAssets(), Font);

        }

        @Override
        public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflator.inflate(R.layout.student_row, parent, false);

            StudentViewHolder studentViewHolder = new StudentViewHolder(view);


            String selectQuery = "SELECT * FROM student_unblock where std_id =?";


            OpenHelper openHelper = new OpenHelper(parent.getContext());
            SQLiteDatabase db = openHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, new String[]{"52"});

            cursor.moveToFirst();



            Log.d("GExam", "cursor.size = " + cursor.getCount());



            if (cursor != null) {


                status = cursor.getInt(cursor.getColumnIndex("status"));


            }

            Log.d("GExam", "STATUS = " + String.valueOf(status));


            cursor.close();

            return studentViewHolder;
        }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, final int position) {

        final Student currentStudent = students.get(position);
        holder.studentName.setText(currentStudent.student);

        holder.chbIllegal.setChecked(students.get(position).isSelected());
        holder.chbIllegal.setTag(students.get(position));
        holder.chbIllegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox cb = (CheckBox) v;
                Student contact = (Student) cb.getTag();
                contact.setSelected(cb.isChecked());
                students.get(position).setSelected(cb.isChecked());

                Toast.makeText(v.getContext(), currentStudent.student + "have been check", Toast.LENGTH_LONG).show();

            }
        });


        if (currentStudent.getStatus() == 1) {

            holder.chbPresent.setChecked(true);

        }






    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder{


        TextView studentName;
        CheckBox chbPresent, chbIllegal;

        public StudentViewHolder(View itemView) {

            super(itemView);

            studentName = (TextView) itemView.findViewById(R.id.StudentlistText);

            this.studentName.setTypeface(typeface);

            chbPresent = (CheckBox) itemView.findViewById(R.id.chbPresent);
            chbIllegal = (CheckBox) itemView.findViewById(R.id.chbIllegal);

        }
    }

    public List<Student> getStudentList() {

        return students;

    }





}
