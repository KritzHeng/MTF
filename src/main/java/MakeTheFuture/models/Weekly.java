package MakeTheFuture.models;

public class Weekly extends Data{
    private String timeStart;
    private String timeEnd;

    public Weekly (String category, String name, String date, String timeStart, String timeEnd, String priority, String status) {
        super(category, name, date, priority, status);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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







    @Override
    public String toString () {
        return "Weekly{" +"name: "+getName()+ ','+
                " date: "+getDate()+ ','+
                " time= " + timeStart + '-'
                + timeEnd + ',' +
                " priority: "+getPriority()+ ','+
                " status: "+getStatus()+
                '}';
    }

//    private String timeStart;
//    private String timeEnd;
//    private String Priority;
//    private String  status;
//    private String date;

}
