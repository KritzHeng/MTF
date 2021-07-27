package MakeTheFuture.models;

public class Data {
    private String name;
    private String date;
    private String priority;
    private String status;
    private String category;


    public Data (String category,String name, String date, String priority, String status) {
        this.category = category;
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.status = status;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getPriority () {
        return priority;
    }

    public void setPriority (String priority) {
        this.priority = priority;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getCategory () { return category; }

    public void setCategory (String category) { this.category = category; }
}






