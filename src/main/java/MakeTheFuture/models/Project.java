package MakeTheFuture.models;

public class Project extends Data{
    private String nameLead;


    private String dateEnd;
//    private String priority;
//    private String status;

    public Project (String category, String name, String nameLead, String date, String dateEnd, String priority, String status) {
//        this.name = name;

        super(category, name,date,priority,status);
        this.nameLead= nameLead;
        this.dateEnd = dateEnd;
//        this.priority = priority;
//        this.status = status;
    }

    public String getNameLead () {
        return nameLead;
    }

    public void setNameLead (String nameLead) {
        this.nameLead = nameLead;
    }


    public String getDateEnd () {
        return dateEnd;
    }

    public void setDateEnd (String dateEnd) {
        this.dateEnd = dateEnd;
    }

}
