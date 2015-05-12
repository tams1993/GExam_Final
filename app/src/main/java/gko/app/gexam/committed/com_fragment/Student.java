package gko.app.gexam.committed.com_fragment;

/**
 * Created by MR.T on 5/12/2015.
 */
public class Student {

    String student;
    private boolean isSelected;

    public Student(String student) {
        this.student = student;
    }

    public String getStudent() {
        return student;
    }

    public boolean isSelected() {

        return isSelected;
    }

    public void setSelected(boolean isSelected) {


        this.isSelected = isSelected;
    }



    @Override
    public String toString() {
        return student;
    }
}
