package gko.app.gexam.committed.com_fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import gko.app.gexam.R;

/**
 * Created by MR.T on 5/12/2015.
 */
    public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

        private LayoutInflater inflator;
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

            return studentViewHolder;
        }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {

        Student currentStudent = students.get(position);
        holder.studentName.setText(currentStudent.student);

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

}
