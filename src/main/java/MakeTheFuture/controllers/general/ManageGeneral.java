package MakeTheFuture.controllers.general;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import MakeTheFuture.models.General;
import MakeTheFuture.models.DataList;
import MakeTheFuture.services.DataSource;
import MakeTheFuture.services.GeneralFileDataSource;
import MakeTheFuture.services.StringConfiguration;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManageGeneral {
    private DataSource dataSourceGeneral,dataSourceCate;//type is interfacce
    private DataList dataListGeneral,dataListCategory;
    private General selectedGeneral;
    private ObservableList<General> generalObservableList;

    @FXML private TableView<General> worksTable;
    @FXML DatePicker datePicker;
    @FXML TextField nameWorkTextField;
//    @FXML ChoiceBox<Integer> startHour,startMin,endHour,endMin;
    @FXML ChoiceBox<String> priority,status,startHour,startMin,endHour,endMin;
    @FXML private Button updateButton;
    @FXML Label warning,nameWorkLabel;

    @FXML
    public void initialize() {

        updateButton.setDisable(true);
//        updateButton.getStyleClass().setAll("btn", "btn-success");
        dataSourceGeneral = new GeneralFileDataSource("data", "general.csv");
//        dataSourceCate = new CategoryFileDataSource("data","category.csv");

        //dataSource = new StudentHardcodeDataSource();//information hiding
        dataListGeneral = dataSourceGeneral.getData();//อ่านแล้ว
//        dataListCategory = dataSourceCate.getData();
        status.getItems().addAll("Has not started yet","Ongoing","Completed");
        priority.getItems().addAll("1.Important","2.Normal","3.Low"); // ระดับความสำคัญ

        for (int i = 0; i <= 23; i++) {
            if (i <= 9) {
                startHour.getItems().add("0" + i);
            } else {
                startHour.getItems().add(String.valueOf(i)); }
        }
        for (int i = 0; i <= 59; i++) {
            if (i <= 9) {
                startMin.getItems().add("0" + i);
            } else {
                startMin.getItems().add(String.valueOf(i));
            }
        }
        for (int i = 0; i <= 23; i++) {
            if (i <= 9) {
                endHour.getItems().add("0" + i);
            } else {
                endHour.getItems().add(String.valueOf(i));
            }
        }
        for (int i = 0; i <= 59; i++) {
            if (i <= 9) {
                endMin.getItems().add("0" + i);
            } else {
                endMin.getItems().add(String.valueOf(i));
            }
        }
//        for (Category category : dataListCategory.toListCategory()) {
//            categoryName.getItems().add(category.getNameCate());
//        }
        showWorkData();

        worksTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedWork(newValue);
            }
        });





    }

    private void showWorkData() {
        worksTable.getColumns().clear();
        generalObservableList= FXCollections.observableArrayList(dataListGeneral.toListGeneral());//.toList()  คือการget Arrrayslist
        worksTable.setItems(generalObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category", "field:category"));
        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Day", "field:date"));
        configs.add(new StringConfiguration("title:Start", "field:timeStart"));
        configs.add(new StringConfiguration("title:End", "field:timeEnd"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

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
//      for ( int j=0; j<configs.size(); j++ ) {
//        System.out.println("element " + j + ": " + configs.get(j));
//        configs.get(j).set("Important");
//    }

    private void showSelectedWork(General general) {
        selectedGeneral = general;//รับstudent
        //set Label
        nameWorkLabel.setText(general.getName());
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

    private void clearSelectedGeneral() {//Clear
        selectedGeneral = null;
        datePicker.setValue(null);
        startHour.setValue(null);
        startMin.setValue(null);
        endHour.setValue(null);
        endMin.setValue(null);
        status.setValue(null);
        priority.setValue(null);
        updateButton.setDisable(true);
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {
        if (datePicker.getValue() != null || startHour.getValue() != null ||
                endHour.getValue() != null || endMin.getValue() != null ||
                status.getValue() != null || priority.getValue() != null) {
//            if (!nameWorkTextField.getText().isEmpty()) {
//                if (dataListGeneral.checkNameGeneral(nameWorkTextField.getText())) {
//                    warning.setText("ชื่อซ้ำนะจ๊ะ"); }
//                else {
//                    selectedGeneral.setName(nameWorkTextField.getText());
//                    warning.setText(""); }
//            }
//            if (categoryName.getValue() != null ){
//                dataListCategory.addNumGeneral(categoryName.getValue());
//                selectedGeneral.setCategory(categoryName.getValue());
//            }
            if (datePicker.getValue() != null) {
                selectedGeneral.setDate(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                warning.setText("");
            }
            if (priority.getValue() != null) {
                selectedGeneral.setPriority(priority.getValue());
                warning.setText("");
            }
            if (status.getValue() != null) {
                selectedGeneral.setStatus(status.getValue());
                warning.setText("");
            }
            //check Times input
            if (startHour.getValue() != null && startMin.getValue() != null &&
                    endHour.getValue() !=null &&endMin.getValue() !=null) {

                ///check wrong times
                if (dataListGeneral.checkTimeAll(startHour.getValue(),startMin.getValue(),
                        endHour.getValue(),
                        endMin.getValue())){//check hour and min of timeStart and timeEnd

                    warning.setText("The finish time must be after Start time."); }
                //select Times
                 else {

                    selectedGeneral.setTimeStart(startHour.getValue() + ":" + startMin.getValue());
                    selectedGeneral.setTimeEnd(endHour.getValue() + ":" + endMin.getValue());
                    warning.setText(""); }
            }


            clearSelectedGeneral();
            worksTable.refresh();
            worksTable.getSelectionModel().clearSelection();
            dataSourceGeneral.setData(dataListGeneral);
//            dataSourceCate.setData(dataListCategory);
        }
    }


//
//
//
//
//
//

//            else{ warning.setText("ชื่อซ้ำนะจ๊ะ"); } }
//        else if (nameWorkTextField.getText()!=null){//ใส่แค่nameWork
//            if (dataList.checkNameGeneral(nameWorkTextField.getText())) {
//                selectedGeneral.setName(nameWorkTextField.getText());
//
//
//            } }
//        else if (datePicker.getValue()!=null){//ใส่แค่วัน
//            selectedGeneral.setDate(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            warning.setText("");
//            clearSelectedGeneral();
//            worksTable.refresh();
//            worksTable.getSelectionModel().clearSelection();
//            dataSource.setData(dataList);}
//        else if (startHour.getValue()==endHour.getValue()&&startMin.getValue()>endMin.getValue() ||
//                startHour.getValue()>endHour.getValue()) {
//            warning.setText("เวลาไม่ถูกต้อง");
//            warning.setText("");}
//        else if (priority.getValue()!=null){//ใส่แค่ความสำคัญ
//            selectedGeneral.setPriority(priority.getValue());
//            warning.setText("");
//            clearSelectedGeneral();
//            worksTable.refresh();
//            worksTable.getSelectionModel().clearSelection();
//            dataSource.setData(dataList);
//        }
//        else if (status.getValue()!=null){//ใส่แค่สเตตัส
//            selectedGeneral.setStatus(status.getValue());
//            warning.setText("");
//            clearSelectedGeneral();
//            worksTable.refresh();
//            worksTable.getSelectionModel().clearSelection();
//            dataSource.setData(dataList);
//        }
//        else{ warning.setText("กรุณาใส่ข้อมูลให้ครบ"); }



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
