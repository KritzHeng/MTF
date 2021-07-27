package MakeTheFuture.models;

import java.util.ArrayList;

public class DataList {
    private ArrayList<General> generals;
    private ArrayList<Forward> forwards;
    private ArrayList<Project> projects;
    private ArrayList<Weekly> weeklies;
    private ArrayList<Category> categories;




    public DataList () {
        this.forwards = new ArrayList<>();
        this.generals = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.weeklies = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void addProject(Project project){this.projects.add(project);}
    public void addGeneral(General general){this.generals.add(general);}
    public void addForward(Forward forward){this.forwards.add(forward);}
    public void addWeekly(Weekly weekly){this.weeklies.add(weekly);}
    public void addCategories(Category category){this.categories.add(category);}

    public ArrayList<General> toListGeneral (){return generals;}
    public ArrayList<Forward> toListForward (){return forwards;}
    public ArrayList<Project> toListProject (){return projects;}
    public ArrayList<Weekly> toListWeekly (){return  weeklies;}
    public ArrayList<Category> toListCategory (){return categories;}


//Controller
    public boolean checkTimeAll(String startTime, String timeEnd){
        String[] startTimeList = startTime.split(":");
        String[] timeEndList = timeEnd.split(":");
        return (Integer.parseInt(startTimeList[0]) != Integer.parseInt(timeEndList[0])
                || Integer.parseInt(startTimeList[1]) >= Integer.parseInt(timeEndList[1]))
                && Integer.parseInt(startTimeList[0]) >= Integer.parseInt(timeEndList[0]);
//        if (Integer.parseInt(startTimeList[0]) == Integer.parseInt(timeEndList[0])
//                && Integer.parseInt(startTimeList[1]) < Integer.parseInt(timeEndList[1])
//                || Integer.parseInt(startTimeList[0]) < Integer.parseInt(timeEndList[0])) {
//            return false; }
//        return true; } same logic
    }
//Manage
    public boolean checkTimeAll(String startHour, String startMin, String endHour, String endMin) {
        return (Integer.parseInt(startHour) != Integer.parseInt(endHour)
                || Integer.parseInt(startMin) >= Integer.parseInt(endMin))
                && Integer.parseInt(startHour) >= Integer.parseInt(endHour);
    }

    public void addNameWorkCate(String nameWork, String nameCate){
        for(Category category : toListCategory ()){
            if(category.getNameCate().equals(nameCate)){
                category.addNameWorkCate(nameWork); } }
    }

    public boolean checkCategory() {//check first running program
        for (Category category : toListCategory()) {
            if (category.getNameCate().equals("-")||category.getNameCate().equals("none")) {
                return false; }
        }return true;}



    public boolean checkNameCate(String nameCate){
        for(Category category : toListCategory()) {
            if (category.getNameCate().equals(nameCate)) {
                return false; }
        }return true;}




    public boolean checkNameGeneral (String name){
        for(General general: generals){

            if(general.getName().equals(name)) {
              ;return true;}}
        return false; }
    public boolean checkNameForward (String name){
        for(Forward forward: forwards){
            if(forward.getName().equals(name))return true;}
        return false; }
    public boolean checkNameWeekly (String name){
        for(Weekly weekly: weeklies){
            if(weekly.getName().equals(name))return true;}
        return false; }
    public boolean checkNameProject (String name){
        for(Project project: projects){
            if(project.getName().equals(name))return true;}
        return false; }


    public void addNumGeneral(String nameCate){
        for (Category category: categories) {
            if(category.getNameCate().equals(nameCate)){
                category.addCateGen(); } }
    }
    public void addNumForward(String nameCate){
        for (Category category: categories) {
            if(category.getNameCate().equals(nameCate)){
                category.addCateFor(); } }
    }
    public void addNumWeekly(String nameCate){
        for (Category category: categories) {
            if(category.getNameCate().equals(nameCate)){
                category.addCateWeek(); } }
    }
    public void addNumProject(String nameCate){
        for (Category category: categories) {
            if(category.getNameCate().equals(nameCate)){
                category.addCatePro(); } }
    }







}
