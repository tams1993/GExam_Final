package gko.app.gexam.student;

/**
 * Created by MR.T on 4/26/2015.
 */
public class SpinnerObject {


    private int id;
    private int date;
    private int intervaltime;
    private int questionamount;
    private int course_id;
    private int status;
    private String teachername;
    private String subject_code;
    private String subject_name;

    public SpinnerObject(int course_id, String subject_name, String teachername, int Intervaltime, int questionamount) {
        this.course_id = course_id;
        this.subject_name = subject_name;
        this.teachername = teachername;
        this.intervaltime = Intervaltime;
        this.questionamount = questionamount;
    }

    public int getId() {
        return id;
    }

    public int getDate() {
        return date;
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

    public int getStatus() {
        return status;
    }

    public String getTeachername() {
        return teachername;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public String getSubject_name() {

        return subject_name;
    }

    @Override
    public String toString()
    {
        return subject_name;
    }


}
