package MakeTheFuture.controllers;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import MakeTheFuture.models.*;
import MakeTheFuture.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class ShowAllController {
    private DataList dataListGeneral,dataListForward, dataListWeekly,dataListProject, dataListCategory;
    private DataSource dataSourceGeneral,dataSourceForward, dataSourceWeekly, dataSourceProject, dataSourceCategory;//type is interface
    private Category selectedCategory;
    private General selectedGeneral;
    private Forward selectedForward;
    private Weekly selectedWeekly;
    private Project selectedProject;
    private ObservableList<Category> categoryObservableList;
    private ObservableList<General> generalObservableList;
    private ObservableList<Forward> forwardObservableList;
    private ObservableList<Weekly> weeklyObservableList;
    private ObservableList<Project> projectObservableList;

    @FXML
    TextArea workNameTextArea;
    @FXML
    TableView worksGeneralTable,worksForwardTable,worksWeeklyTable, worksProjectTable;


    @FXML
    private TableView<Category> numTableCategory;
//    @FXML private Button updateButton;


    @FXML
    public void initialize () {


        dataSourceGeneral = new GeneralFileDataSource("data", "general.csv");
        dataSourceForward = new ForwardFileDataSource("data","forward.csv");
        dataSourceWeekly = new WeeklyFileDataSource("data","weekly.csv");
        dataSourceCategory = new CategoryFileDataSource("data", "category.csv");
        dataSourceProject = new ProjectFileDataSource("data","project.csv");
        //dataSource = new StudentHardcodeDataSource();//รinformation hiding
        dataListCategory = dataSourceCategory.getData();
        dataListGeneral = dataSourceGeneral.getData();//อ่านแล้ว
        dataListForward = dataSourceForward.getData();
        dataListWeekly = dataSourceWeekly.getData();
        dataListProject = dataSourceProject.getData();


            showWorkData();
            showGeneralWorkData();
            showForwardWorkData();
            showWeeklyWorkData();
            showProjectWorkData();
        numTableCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                showSelectedWork(newValue);
            }
        });
        worksGeneralTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                showSelectedGeneral((General) newValue);
            }
        });
        worksForwardTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                showSelectedForward((Forward) newValue);
            }
        });
        worksWeeklyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedWeekly((Weekly) newValue);
            }
        });
        worksProjectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                showSelectedProject((Project) newValue);
            }
        });

    }



    private void showWorkData () {
        categoryObservableList = FXCollections.observableArrayList(dataListCategory.toListCategory());//.toList()  คือการget Arrrayslist
        numTableCategory.setItems(categoryObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:CategoryName", "field:nameCate"));
        configs.add(new StringConfiguration("title:Total", "field:numCateTotal"));
        configs.add(new StringConfiguration("title:General", "field:numCateGeneral"));
        configs.add(new StringConfiguration("title:Forward", "field:numCateForward"));
        configs.add(new StringConfiguration("title:Weekly", "field:numCateWeekly"));
        configs.add(new StringConfiguration("title:Project", "field:numCateProject"));

        for (StringConfiguration conf : configs) {//วงลูมในconfigs
            TableColumn col = new TableColumn(conf.get("title"));//หัวตาราง
            col.prefWidthProperty().bind(numTableCategory.widthProperty().multiply(0.17));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            numTableCategory.getColumns().add(col);

        }

    }

    private void showGeneralWorkData () {
        generalObservableList = FXCollections.observableArrayList(dataListGeneral.toListGeneral());//.toList()  คือการget Arrrayslist
        worksGeneralTable.setItems(generalObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();

        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Priority", "field:Priority"));
        for (StringConfiguration conf : configs) {//วงลูมในconfigs
            TableColumn col = new TableColumn(conf.get("title"));//หัวตาราง
            col.prefWidthProperty().bind(worksGeneralTable.widthProperty().multiply(0.5));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            worksGeneralTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                worksGeneralTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.ASCENDING);
            }
        }
    }

    private void showForwardWorkData () {
        forwardObservableList = FXCollections.observableArrayList(dataListForward.toListForward());//.toList()  คือการget Arrrayslist
        worksForwardTable.setItems(forwardObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();

        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Priority", "field:Priority"));
        for (StringConfiguration conf : configs) {//วงลูมในconfigs
            TableColumn col = new TableColumn(conf.get("title"));//หัวตาราง
            col.prefWidthProperty().bind(worksForwardTable.widthProperty().multiply(0.5));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            worksForwardTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                worksForwardTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.ASCENDING);
            }
        }
    }

    private void showWeeklyWorkData () {
        weeklyObservableList = FXCollections.observableArrayList(dataListWeekly.toListWeekly());//.toList()  คือการget Arrrayslist
        worksWeeklyTable.setItems(weeklyObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();

        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Priority", "field:Priority"));
        for (StringConfiguration conf : configs) {//วงลูมในconfigs
            TableColumn col = new TableColumn(conf.get("title"));//หัวตาราง
            col.prefWidthProperty().bind(worksWeeklyTable.widthProperty().multiply(0.5));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            worksWeeklyTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                worksWeeklyTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.ASCENDING);
            }
        }
    }

    private void showProjectWorkData () {
        projectObservableList = FXCollections.observableArrayList(dataListProject.toListProject());//.toList()  คือการget Arrrayslist
        worksProjectTable.setItems(projectObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();

        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Priority", "field:Priority"));
        for (StringConfiguration conf : configs) {//วงลูมในconfigs
            TableColumn col = new TableColumn(conf.get("title"));//หัวตาราง
            col.prefWidthProperty().bind(worksProjectTable.widthProperty().multiply(0.5));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            worksProjectTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                worksProjectTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.ASCENDING);
            }
        }
    }


    private void showSelectedForward(Forward forward) {
        workNameTextArea.clear();
        selectedForward= forward;
        showDetailsForward();}
    private void showDetailsForward(){
        workNameTextArea.clear();
        workNameTextArea.appendText("\t\t\t\t\t\bForward\n");
        workNameTextArea.appendText("Category: "+selectedForward.getCategory()+"\n\n");
        workNameTextArea.appendText("NameWork: "+selectedForward.getName()+"\n\n");
        workNameTextArea.appendText("Responsible person: "+selectedForward.getNameRes()+"\n\n");
        workNameTextArea.appendText("Date: "+selectedForward.getDate()+"\n\n");
        workNameTextArea.appendText("Time: "+selectedForward.getTime()+"\n\n");
        workNameTextArea.appendText("Priority: "+selectedForward.getPriority()+"\n\n");
        workNameTextArea.appendText("Status: "+selectedForward.getStatus()+"\n\n");

    }


    private void showSelectedGeneral(General general) {
        selectedGeneral = general;
        showDetailsGeneral();}
    private void showDetailsGeneral(){
        workNameTextArea.clear();
        workNameTextArea.appendText("\t\t\t\t\t\bGeneral\n");
        workNameTextArea.appendText("Category: "+selectedGeneral.getCategory()+"\n\n");
        workNameTextArea.appendText("NameWork: "+selectedGeneral.getName()+"\n\n");
        workNameTextArea.appendText("Date: "+selectedGeneral.getDate()+"\n\n");
        workNameTextArea.appendText("TimeStart: "+selectedGeneral.getTimeStart()+"\n\n");
        workNameTextArea.appendText("TimeEnd: "+selectedGeneral.getTimeEnd()+"\n\n");
        workNameTextArea.appendText("Priority: "+selectedGeneral.getPriority()+"\n\n");
        workNameTextArea.appendText("Status: "+selectedGeneral.getStatus()+"\n\n");


    }

    private void showSelectedWeekly(Weekly weekly) {
        selectedWeekly = weekly;
        showDetailsWeekly();}
    private void showDetailsWeekly(){
        workNameTextArea.clear();
        workNameTextArea.appendText("\t\t\t\t\t\bWeekly\n");
        workNameTextArea.appendText("Category: "+selectedWeekly.getCategory()+"\n\n");
        workNameTextArea.appendText("NameWork: "+selectedWeekly.getName()+"\n\n");
        workNameTextArea.appendText("Date: "+selectedWeekly.getDate()+"\n\n");
        workNameTextArea.appendText("TimeStart: "+selectedWeekly.getTimeStart()+"\n\n");
        workNameTextArea.appendText("TimeEnd: "+selectedWeekly.getTimeEnd()+"\n\n");
        workNameTextArea.appendText("Priority: "+selectedWeekly.getPriority()+"\n\n");
        workNameTextArea.appendText("Status: "+selectedWeekly.getStatus()+"\n\n");

    }

    private void showSelectedProject(Project project) {
        selectedProject= project;
        showDetailsProject();}
    private void showDetailsProject(){
        workNameTextArea.clear();
        workNameTextArea.appendText("\t\t\t\t\t\bProject\n");
        workNameTextArea.appendText("Category: "+selectedProject.getCategory()+"\n\n");
        workNameTextArea.appendText("NameWork: "+selectedProject.getName()+"\n\n");
        workNameTextArea.appendText("Responsible person: "+selectedProject.getNameLead()+"\n\n");
        workNameTextArea.appendText("DateStart: "+selectedProject.getDate()+"\n\n");
        workNameTextArea.appendText("Time: "+selectedProject.getDateEnd()+"\n\n");
        workNameTextArea.appendText("Priority: "+selectedProject.getPriority()+"\n\n");
        workNameTextArea.appendText("Status: "+selectedProject.getStatus()+"\n\n");

    }


    private void showSelectedWork(Category category) {
        selectedCategory = category;//รับstudent
        showWorkNameCate();
    }
    private void showWorkNameCate(){
        String[] nameCate = selectedCategory.getNameAllCate().split("/");
        workNameTextArea.clear();
        workNameTextArea.appendText("\t\t\t\t\tCategory\n");
        workNameTextArea.appendText("CategoryName:\n"+"\t\t"+selectedCategory.getNameCate()+"\n");
        workNameTextArea.appendText("Total Work:\n"+"\t\t"+selectedCategory.getNumCateTotal()+" Work.\n");
        workNameTextArea.appendText("Generic:\n"+"\t\t"+selectedCategory.getNumCateGeneral()+" Work.\n"+"Forward:\n"+"\t\t"+selectedCategory.getNumCateForward()+" Work.\n");
        workNameTextArea.appendText("Weekly:\n"+"\t\t"+selectedCategory.getNumCateWeekly()+ " Work.\n"+"Project:\n"+"\t\t"+selectedCategory.getNumCateProject()+" Work.\n\n");
        workNameTextArea.appendText("\t\t\t\t\t"+"Work List"+"\n");
        int i =1;
        for (String d: nameCate) {
        workNameTextArea.appendText(i+ ") "+ d +"\n");
        i++;
    }



    }




    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleManageGeneralButton (ActionEvent actionEvent) {
        try {
            FXRouter.goTo("manageGeneralWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า manageGeneralWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleManageWeeklyButton (ActionEvent actionEvent) {
        try {
            FXRouter.goTo("manageWeeklyWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า manageWeeklyWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleManageProjectButton (ActionEvent actionEvent) {
        try {
            FXRouter.goTo("manageProjectWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า manageProjectWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }  @FXML
    public void handleManageForwardButton (ActionEvent actionEvent) {
        try {
            FXRouter.goTo("manageForwardWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า manageGeneralWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}
