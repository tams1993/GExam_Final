package gko.app.gexam.committed.com_fragment;

/**
 * Created by MR.T on 5/12/2015.
 */
public class Student {

    String student;
    String againts_rule;
    private boolean isSelected;
    private int status;
    private int std_id;

    public Student(String student, int status, int std_id) {
        this.student = student;
        this.status = status;
        this.std_id = std_id;
    }

    public int getStatus() {
        return status;
    }

    public String getStudent() {
        return student;
    }

    public int getStd_id()

    {
        return std_id;
    }

    public boolean isSelected() {

        return isSelected;
    }

    public void setSelected(boolean isSelected) {


        this.isSelected = isSelected;
    }

    public String getAgaints_rule() {
        return againts_rule;
    }

    public void setAgaints_rule(String againts_rule) {

        this.againts_rule = againts_rule;
    }

    @Override
    public String toString() {
        return student;
    }
}
