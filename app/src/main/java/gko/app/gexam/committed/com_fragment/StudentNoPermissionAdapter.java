package gko.app.gexam.committed.com_fragment;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import gko.app.gexam.R;

/**
 * Created by MR.T on 5/23/2015.
 */
public class StudentNoPermissionAdapter extends RecyclerView.Adapter<StudentNoPermissionAdapter.StudentNoPermissionVH> {

    private LayoutInflater inflater;

    List<StudentNoPermission> data = Collections.emptyList();

    public StudentNoPermissionAdapter(Context context, List<StudentNoPermission> data) {

        inflater=LayoutInflater.from(context);
        this.data = data;


    }

    @Override
    public StudentNoPermissionVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.student_no_per_row, parent, false);
        StudentNoPermissionVH vh = new StudentNoPermissionVH(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(StudentNoPermissionVH holder, int position) {

        StudentNoPermission current = data.get(position);

        holder.name.setText(current.student);
        holder.surname.setText(current.surname);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class StudentNoPermissionVH extends RecyclerView.ViewHolder {

        TextView name;
        TextView surname;

        public StudentNoPermissionVH(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.studentNoPerlistName);
            surname = (TextView) itemView.findViewById(R.id.studentNoPerListSurname);


        }
    }

}
