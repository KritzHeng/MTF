package MakeTheFuture.models;


public class General extends Data{
//    private String name;
    private String timeStart;
    private String timeEnd;
//    private String priority;
//    private String status;
//    private String date;


    public General (String category, String name, String date, String timeStart, String timeEnd, String priority, String status) {
        super(category, name, date, priority, status);
        this.timeStart=timeStart;
        this.timeEnd= timeEnd;
//        this.name = name;
//        this.priority = priority;
//        this.status = status;
//        this.date = date;
    }

    public String getTimeStart () {
        return timeStart;
    }

    public void setTimeStart (String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd () {
        return timeEnd;
    }

    public void setTimeEnd (String timeEnd) {
        this.timeEnd = timeEnd;
    }

//    public void updateAllWork(double nameWork, double date, double startHour, double startMin) {
//        work.set(WorkType.NAME_WORK, nameWork);
//        score.set(ScoreType.ATTENDANCE, attendance);
//        score.set(ScoreType.MIDTERM_EXAM, midtermExam);
//        score.set(ScoreType.FINAL_EXAM, finalExam);
//    }
}



