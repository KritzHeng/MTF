package MakeTheFuture.controllers.forward;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import MakeTheFuture.models.Forward;
import MakeTheFuture.models.DataList;
import MakeTheFuture.services.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManageForward {
    private DataSource dataSourceForward,dataSourceCate;//type is interfacce
    private DataList dataListForward,dataListCategory;
    private Forward selectedForward;
    private ObservableList<Forward> forwardObservableList;

    @FXML
    private TableView<Forward> worksTable;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField nameResTextField;
//    @FXML
//    ChoiceBox<Integer> startHour,startMin;
    @FXML ChoiceBox<String> priority,status, timeHour,timeMin;
    @FXML private Button updateButton;
    @FXML Label warning, nameWorkTextField;

    @FXML
    public void initialize() {

        updateButton.setDisable(true);
//        updateButton.getStyleClass().setAll("btn", "btn-success");
        dataSourceForward = new ForwardFileDataSource("data", "forward.csv");
        dataSourceCate = new CategoryFileDataSource("data","category.csv");
        dataListForward = dataSourceForward.getData();//อ่านแล้ว
        dataListCategory = dataSourceCate.getData();
        status.getItems().addAll("Has not started yet","Ongoing ","Completed");
        priority.getItems().addAll("1.Important","2.Normal","3.Low"); // ระดับความสำคัญ
        for (int i = 0; i <= 23; i++) {
            if (i <= 9) {
                timeHour.getItems().add("0" + i);
            } else {
                timeHour.getItems().add(String.valueOf(i));
            }
        }
        for (int i = 0; i <= 59; i++) {
            if (i <= 9) {
                timeMin.getItems().add("0" + i);
            } else {
                timeMin.getItems().add(String.valueOf(i)); }
        }
        showWorkData();
        worksTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedWork(newValue);
            }
        });



    }

    private void showWorkData() {
        forwardObservableList= FXCollections.observableArrayList(dataListForward.toListForward());//.toList()  คือการget Arrrayslist
        worksTable.setItems(forwardObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category", "field:category"));
        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:NameRes", "field:nameRes"));
        configs.add(new StringConfiguration("title:Day", "field:date"));
        configs.add(new StringConfiguration("title:Time", "field:time"));
        configs.add(new StringConfiguration("title:Priority", "field:Priority"));
        configs.add(new StringConfiguration("title:status", "field:status"));
        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            worksTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
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

    private void showSelectedWork(Forward forward) {
        selectedForward = forward;//รับwork
        updateButton.setDisable(false);
        nameWorkTextField.setText(forward.getName());
    }

    private void clearSelectedForward() {//Clear
        selectedForward = null;
        nameResTextField.clear();
        datePicker.setValue(null);
        timeHour.setValue(null);
        timeMin.setValue(null);
        status.setValue(null);
        priority.setValue(null);
        updateButton.setDisable(true);
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {
//        selectedForward.addNameRes(nameResTextField.getText());//add ชื่อ
        if (datePicker.getValue() != null) {
            selectedForward.setDate(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (priority.getValue()!= null){ selectedForward.setPriority(priority.getValue());
        }
        if(status.getValue() != null){ selectedForward.setStatus(status.getValue());

        }
        if (timeHour.getValue()!= null &&timeMin.getValue() != null){
            selectedForward.setTime(timeHour.getValue()+":"+timeMin.getValue());
             }
        if (!nameResTextField.getText().equals("")){
            selectedForward.addNameRes(nameResTextField.getText());
        }




        clearSelectedForward();
        worksTable.refresh();
        worksTable.getSelectionModel().clearSelection();
        dataSourceForward.setData(dataListForward);
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
