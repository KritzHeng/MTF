package MakeTheFuture.models;

public class Category {
    private String nameCate;
    private int numCateTotal;
    private int numCateGeneral;
    private int numCateWeekly;
    private int numCateProject;
    private int numCateForward;
    private String nameAllCate;
    private String nameNoneCate;

    public Category (String nameCate, int numCateTotal, int numCateGen, int numCateForward, int numCateWeekly, int numCateProject, String nameAllCate) {
        this.nameCate = nameCate;
        this.numCateTotal = numCateTotal;
        this.numCateGeneral = numCateGen;
        this.numCateForward = numCateForward;
        this.numCateWeekly = numCateWeekly;
        this.numCateProject = numCateProject;
        this.nameAllCate = nameAllCate;
//        this.nameNoneCate = nameNoneCate;

    }

    public String getNameCate () {
        return nameCate;
    }

    public void setNameCate (String nameCate) {
        this.nameCate = nameCate;
    }

    public int getNumCateTotal () {
        this.numCateTotal= numCateForward+ numCateWeekly + numCateProject + numCateGeneral;
        return numCateTotal;
    }


    public void setNumCateTotal (int numCateTotal) {
        this.numCateTotal = numCateTotal;
    }

    public int getNumCateGeneral () {
        return numCateGeneral;
    }

    public void setNumCateGeneral (int numCateGeneral) {
        this.numCateGeneral = numCateGeneral;
    }

    public int getNumCateWeekly () {
        return numCateWeekly;
    }

    public void setNumCateWeekly (int numCateWeekly) {
        this.numCateWeekly = numCateWeekly;
    }

    public int getNumCateProject () {
        return numCateProject;
    }

    public void setNumCateProject (int numCateProject) {
        this.numCateProject = numCateProject;
    }

    public int getNumCateForward () {
        return numCateForward;
    }

    public void setNumCateForward (int numCateForward) {
        this.numCateForward = numCateForward;
    }

    public String getNameAllCate () {
        return nameAllCate;
    }


    public void setNameAllCate (String nameAllCate) {
        this.nameAllCate = nameAllCate;
    }

    public void addNameWorkCate (String nameWorkCate){
        if(this.nameAllCate.equals("-")){
            this.nameAllCate = nameWorkCate;
        }
        else{
            this.nameAllCate += "/" + nameWorkCate;
        }
    }
//    public void addNameWorkNoneCate(String nameWorkCate){
//        if(this.nameNoneCate.equals("-")){
//            this.nameNoneCate = nameWorkCate;
//        }
//        else{
//            this.nameNoneCate += "/" + nameWorkCate;
//        }
//    }


    public void addCateGen(){
        this.numCateGeneral += 1;
    }
    public void addCateFor(){
        this.numCateForward += 1;
    }
    public void addCateWeek(){
        this.numCateWeekly +=1;
    }
    public void addCatePro(){
        this.numCateProject +=1;
    }





}
