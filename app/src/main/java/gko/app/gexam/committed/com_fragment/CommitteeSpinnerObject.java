package gko.app.gexam.committed.com_fragment;

/**
 * Created by MR.T on 5/19/2015.
 */
public class CommitteeSpinnerObject {




    private int intervaltime;
    private int questionamount;
    private int course_id;

    private int teacher_id;
    private int subject_id;
    private int class_id;
    private String teachername;
    private String classname;

    private String subject_name;

    public CommitteeSpinnerObject(int course_id, String subject_name, String teachername, int Intervaltime, int questionamount, int subject_id , int teacher_id, int class_id, String classname) {
        this.course_id = course_id;
        this.subject_name = subject_name;
        this.teachername = teachername;
        this.intervaltime = Intervaltime;
        this.questionamount = questionamount;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.class_id = class_id;
        this.classname = classname;
    }

    public String getClassname() {
        return classname;
    }

    public int getClass_id() {
        return class_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }




    public int getIntervaltime() {
        return intervaltime;

    }

    public int getQuestionamount() {
        return questionamount;
    }

    public int getCourse_id() {
        return course_id;
    }


    public String getTeachername() {
        return teachername;
    }


    public String getSubject_name() {

        return subject_name;
    }

    @Override
    public String toString()
    {
        return subject_name+" by: "+teachername+ " class: " + classname;
    }

}
