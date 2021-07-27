package MakeTheFuture.controllers.project;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import MakeTheFuture.models.Category;
import MakeTheFuture.models.DataList;
import MakeTheFuture.models.Project;
import MakeTheFuture.services.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ProjectController {
    @FXML
    DatePicker dateStart, dateEnd;
    @FXML
    TextField nameProjectTextField, nameLeadTextField;

    @FXML
    ChoiceBox<String> priority, status,categoryName;

    @FXML
    Label warning;


    private DataSource dataSourceProject,dataSourceCategory;//type is interfacce
    private DataList dataListProject,dataListCategory;


    @FXML
    public void initialize () {
        Platform.runLater((Runnable) new Runnable() {
            @Override
            public void run () {
                dataSourceProject = new ProjectFileDataSource("data", "project.csv");
                dataSourceCategory = new CategoryFileDataSource("data","category.csv");

                //dataSource = new StudentHardcodeDataSource();//information hiding
                dataListProject = dataSourceProject.getData();//อ่านแล้ว
                dataListCategory = dataSourceCategory.getData();

                status.getItems().addAll("Has not started yet","Ongoing ","Completed");
                priority.getItems().addAll("1.Important","2.Normal","3.Low"); // ระดับความสำคัญ
                for (Category category : dataListCategory.toListCategory()) {
                    categoryName.getItems().add(category.getNameCate());
                }

            }
        });
        categoryName.setValue("-");

    }

    public void handleSubmitButton (ActionEvent actionEvent) {
        if (nameProjectTextField.getText().equals("") || nameLeadTextField.getText() == null ||
                dateStart.getValue() == null || dateEnd.getValue() == null ||
                priority.getValue() == null || status.getValue() == null) {
            System.out.println("if1");
            warning.setText("Please fill in the empty box before proceed.");
        }
        else {
             if (dataListProject.checkNameProject(nameProjectTextField.getText())) {
            System.out.println("el 1");
            warning.setText("This Project Name name has been taken.");
        }   else if (dateStart.getValue().isAfter(dateEnd.getValue())) {
            System.out.println("el 2");
            warning.setText("The finish date must be after Start date.");
        }
//            if (categoryName.getValue()!= null){
            else {
                 Project project = new Project(categoryName.getValue(), nameProjectTextField.getText(),
                         nameLeadTextField.getText(),
                         dateStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                         dateEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                         priority.getValue(), status.getValue());
//                if(categoryName.equals("-")){
//                    project.setCategory("none");
                 dataListCategory.addNameWorkCate(nameProjectTextField.getText(), categoryName.getValue());
                 dataListCategory.addNumProject(categoryName.getValue());
                 dataListProject.addProject(project);
                 dataSourceCategory.setData(dataListCategory);
                 dataSourceProject.setData(dataListProject);
                 try {
                     FXRouter.goTo("manageProjectWork");
                 } catch (IOException e) {
                     System.err.println("ไปที่หน้า manageProjectWork ไม่ได้");
                     System.err.println("ให้ตรวจสอบการกำหนด route");
                 }
//                }

             }
//            }
//            else {
//                Project project = new Project("-", nameProjectTextField.getText(), nameLeadTextField.getText(), dateStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), dateEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), priority.getValue(), status.getValue());
//
//                dataListProject.addProject(project);
//                dataSourceProject.setData(dataListProject);

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




