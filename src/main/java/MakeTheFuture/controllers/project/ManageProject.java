package MakeTheFuture.controllers.project;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import MakeTheFuture.models.DataList;
import MakeTheFuture.models.Project;
import MakeTheFuture.services.CategoryFileDataSource;
import MakeTheFuture.services.DataSource;
import MakeTheFuture.services.ProjectFileDataSource;
import MakeTheFuture.services.StringConfiguration;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManageProject {
    private DataSource dataSourceProject,dataSourceCate;//type is interfacce
    private DataList dataListProject,dataListCategory;
    private Project selectedProject;
    private ObservableList<Project> projectObservableList;

    @FXML
    private TableView<Project> worksTable;
    @FXML
    DatePicker dateStart,dateEnd;

    @FXML ChoiceBox<String> priority,status;
    @FXML private Button updateButton;
    @FXML Label warning,nameLeadTextField,nameProjectTextField;

    @FXML
    public void initialize() {

        updateButton.setDisable(true);
//        updateButton.getStyleClass().setAll("btn", "btn-success");
        dataSourceProject = new ProjectFileDataSource("data", "project.csv");
        dataSourceCate = new CategoryFileDataSource("data","category.csv");
        dataListProject = dataSourceProject.getData();//อ่านแล้ว
        dataListCategory = dataSourceCate.getData();
        status.getItems().addAll("not started","Continue","Completed"); // ระดับความสำคัญ
        priority.getItems().addAll("1.Important","2.Normal","3.Low"); // ระดับความสำคัญ
        showWorkData();
        worksTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedWork(newValue);
            }
        });



    }

    private void showWorkData() {
        projectObservableList= FXCollections.observableArrayList(dataListProject.toListProject());//.toList()  คือการget Arrrayslist
        worksTable.setItems(projectObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category", "field:category"));
        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:NameLead", "field:nameLead"));
        configs.add(new StringConfiguration("title:DateStart", "field:date"));
        configs.add(new StringConfiguration("title:DateEnd", "field:dateEnd"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:status", "field:status"));
        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            worksTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")){
                worksTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.ASCENDING);
//                String[] x = conf.get("field").split("");
//                String
//                for ( String d : x) {
//                    if (d.equals("3")) {
//
//                        }
//                    if (d.equals("2")) {
//
//                    }
//                    if (d.equals("1")) {
//
//                    }
//              }
            }

        }
    }

    private void showSelectedWork(Project project) {
        selectedProject = project;//รับstudent
        nameProjectTextField.setText(project.getName());
        nameLeadTextField.setText(project.getNameLead());
        //set Label
//        nameWorkTextField.setText(general.getName());
//        startHour.get();
//        startHour.setText(String.valueOf(general.getTimeStart()));
//        sidLabel.setText(student.getId());
//        nameLabel.setText(student.getName());
//        nameWork.setText(String.valueOf(general.getName()));
//        attendanceScoreTextField.setText(String.valueOf(student.getScore().get(ScoreType.ATTENDANCE)));
//        midtermScoreTextField.setText(String.valueOf(student.getScore().get(ScoreType.MIDTERM_EXAM)));
//        finalScoreTextField.setText(String.valueOf(student.getScore().get(ScoreType.FINAL_EXAM)));
        updateButton.setDisable(false);
    }

    private void clearSelectedProject() {//Clear
        selectedProject = null;
        nameProjectTextField.setText("");
        nameLeadTextField.setText("");
        dateStart.setValue(null);
        dateEnd.setValue(null);
        status.setValue(null);
        priority.setValue(null);
        updateButton.setDisable(true);
    }
    @FXML
    public void handleUpdateButton(ActionEvent event) {
        if (dateStart.getValue() != null || dateEnd.getValue() != null
                || priority.getValue() != null || status.getValue() != null){
            if (dateStart.getValue() != null && dateEnd.getValue() != null) {
                if (dateStart.getValue().isBefore(dateEnd.getValue())) {

                    selectedProject.setDate(dateStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                    selectedProject.setDateEnd(dateEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    warning.setText("");
                    if (priority.getValue() != null) {
                        selectedProject.setPriority(priority.getValue());
                        warning.setText("");
                    }
                    if (status.getValue() != null) {
                        selectedProject.setStatus(status.getValue());
                        warning.setText("");
                    }


                    clearSelectedProject();
                    worksTable.refresh();
                    worksTable.getSelectionModel().clearSelection();
                    dataSourceProject.setData(dataListProject);}
                else {
                    warning.setText("The finish date must be after Start date.");


                }
            }



            }


    }

    @FXML
    public void handleBackButton (ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}
