package MakeTheFuture.models;

public class Forward extends Data{
//    private String name;
    private String nameRes;
////
//    private String date;
    private String time;
//    private String priority;
//    private String status;

    public Forward (String category, String name,String nameRes,String date, String time, String priority, String status) {
        super(category, name, date, priority, status);
        this.nameRes= nameRes;
        this.time= time;
        //this.nameRes = nameRes;
        //this.timeStart = timeStart;
//        this.priority = priority;
//        this.status = status;
    }
    public void addNameRes(String nameRes){
        this.nameRes = getNameRes()+ "-" +nameRes ;
    }

    public String getNameRes () {
        return nameRes;
    }

    public void setNameRes (String nameRes) {
        this.nameRes = nameRes;
    }

    public String getTime () {
        return time;
    }

    public void setTime (String time) {
        this.time = time;
    }
}
