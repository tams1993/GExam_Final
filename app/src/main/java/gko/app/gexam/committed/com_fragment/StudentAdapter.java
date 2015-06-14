package gko.app.gexam.committed.com_fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
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


        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(final StudentViewHolder holder, final int position) {





        final Student currentStudent = students.get(position);
        holder.studentName.setText(currentStudent.student);
        holder.chbIllegal.setTag(students.get(position));
        holder.editText.setTag(students.get(position));


holder.editText.setText(currentStudent.getAgaints_rule());

        if (currentStudent.isSelected()) {
            holder.editText.setEnabled(true);
           // holder.editText.setText(currentStudent.getAgaints_rule());
        }else{
            holder.editText.setEnabled(false);
        }




        holder.chbIllegal.setChecked(students.get(position).isSelected());

       


//        holder.chbIllegal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                CheckBox cb = (CheckBox) v;
//
//            final     Student contact = (Student) cb.getTag();
//
//                contact.setSelected(cb.isChecked());
//                students.get(position).setSelected(cb.isChecked());
//
//
//
//                if (cb.isChecked()) {
//
//
//                    holder.editText.setEnabled(true);
//                    holder.editText.addTextChangedListener(new TextWatcher() {
//                        @Override
//                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                        }
//
//                        @Override
//                        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                        }
//
//                        @Override
//                        public void afterTextChanged(Editable s) {
//
//
//                            String Illegal = holder.editText.getText().toString();
//                            contact.setAgaints_rule(s.toString());
//                            Log.d("GExam", currentStudent.student + " " + Illegal);
//                           // currentStudent.setAgaints_rule(Illegal);
//
//                            Log.e("Test", "Current " + position);
//
//                          // students.get((Integer) holder.editText.getTag()).setAgaints_rule(Illegal);
//
//
//                        }
//                    });
//
//
////                    holder.chbIllegal.setTag(students.get(position));
////                    holder.editText.setTag(students.get(position));
//
//
//                }
//
//
//            }
//        });




        if (currentStudent.getStatus() == 1) {


            holder.chbPresent.setChecked(true);



        } else if (currentStudent.getStatus() == 0) {

            holder.chbPresent.setChecked(false);

        }


    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {


        TextView studentName;
        CheckBox chbPresent, chbIllegal;
        EditText editText;

        public StudentViewHolder(View itemView) {

            super(itemView);

            studentName = (TextView) itemView.findViewById(R.id.StudentlistText);

            this.studentName.setTypeface(typeface);

            editText = (EditText) itemView.findViewById(R.id.edtAgaintsRule);
            chbPresent = (CheckBox) itemView.findViewById(R.id.chbPresent);
            chbIllegal = (CheckBox) itemView.findViewById(R.id.chbIllegal);

//            editText.setEnabled(false);

            chbIllegal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = Integer.parseInt( v.getTag().toString());
//                    CheckBox cb = (CheckBox) v;
//                    students.get(position).setSelected(cb.isChecked());
                    CheckBox cb = (CheckBox) v;
                    Student s = (Student) v.getTag();
                    Log.e("GExam", String.valueOf(cb.isChecked()));
                    s.setSelected(cb.isChecked());
                    StudentViewHolder.this.editText.setEnabled(cb.isChecked());

                }
            });

            editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                            Student student = (Student) StudentViewHolder.this.editText.getTag();

                           student.setAgaints_rule(s.toString());
                        }
                    });



        }
    }

    public List<Student> getStudentList() {

        return students;

    }
}
