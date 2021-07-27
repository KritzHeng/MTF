package MakeTheFuture.controllers;

import com.github.saacsos.FXRouter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import MakeTheFuture.models.*;
import MakeTheFuture.services.*;

import java.io.IOException;

public class HomeController {
    @FXML
    private ImageView imageView;
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
    Label warning;

    @FXML
    public void initialize() {
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
        if (dataListCategory.checkCategory()) {
            Category category = new Category("-", 0, 0, 0, 0, 0, "-");
            dataListCategory.addCategories(category);
            dataSourceCategory.setData(dataListCategory);
        }
        
    }
        
    @FXML
    public void handleCreatorButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("creator");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Creator ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleHelpButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("help");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Help ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleCreateWorkButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("createWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า createWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleShowButton(ActionEvent actionEvent){

        try {
            FXRouter.goTo("showAll");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า showAll ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}
