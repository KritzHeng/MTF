package MakeTheFuture.controllers.weekly;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import MakeTheFuture.models.DataList;
import MakeTheFuture.models.Weekly;
import MakeTheFuture.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class ManageWeekly {
    private DataSource dataSourceWeekly,dataSourceCate;//type is interfacce
    private DataList dataListWeekly,dataListCategory;
    private Weekly selectedWeekly;
    private ObservableList<Weekly> weeklyObservableList;

    @FXML
    private TableView<Weekly> worksTable;

    @FXML
    CheckBox sun,mon,tue,wen,thu,fri,sat;
    @FXML
    ChoiceBox<String> priority,status,startHour,startMin,endHour,endMin;

    @FXML private Button updateButton;
    @FXML Label warning,nameWork;







    @FXML
    public void initialize() {

        updateButton.setDisable(true);
//        updateButton.getStyleClass().setAll("btn", "btn-success");
        dataSourceWeekly = new WeeklyFileDataSource("data", "weekly.csv");
        dataSourceCate = new CategoryFileDataSource("data","category.csv");

        //dataSource = new StudentHardcodeDataSource();//information hiding
        dataListWeekly = dataSourceWeekly.getData();//อ่านแล้ว
        dataListCategory = dataSourceCate.getData();
        status.getItems().addAll("Has not started yet","Ongoing ","Completed");
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
        showWorkData();

        worksTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedWork(newValue);
            }
        });



    }

    private void showWorkData() {
        weeklyObservableList= FXCollections.observableArrayList(dataListWeekly.toListWeekly());//.toList()  คือการget Arrrayslist
        worksTable.setItems(weeklyObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category", "field:category"));
        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Date", "field:date"));
        configs.add(new StringConfiguration("title:TimeStart", "field:timeStart"));
        configs.add(new StringConfiguration("title:TimeEnd", "field:timeEnd"));
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

    private void showSelectedWork(Weekly weekly) {
        selectedWeekly = weekly;//รับstudent
        sun.setSelected(false);
        mon.setSelected(false);
        tue.setSelected(false);
        wen.setSelected(false);
        thu.setSelected(false);
        fri.setSelected(false);
        sat.setSelected(false);
        String[] dateList = selectedWeekly.getDate().split("-");
        for( int i = 0;i < dateList.length ;i++) {
            if (dateList[i].equals("sun")) {
                sun.setSelected(true);
            }
            if (dateList[i].equals("mon")){
                mon.setSelected(true);
            }
            if (dateList[i].equals("tue")){
                tue.setSelected(true);
            }
            if (dateList[i].equals("wen")){
                wen.setSelected(true);
            }
            if (dateList[i].equals("thu")){
                thu.setSelected(true);
            }
            if (dateList[i].equals("fri")){
                fri.setSelected(true);
            }
            if (dateList[i].equals("sat")){
                sat.setSelected(true); }
        }
//
        //set Label\
        warning.setText("");
        nameWork.setText(weekly.getName());
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

    private void clearSelectedWeekly() {//Clear


        nameWork.setText("");
        selectedWeekly = null;
        startHour.setValue(null);
        startMin.setValue(null);
        endHour.setValue(null);
        endMin.setValue(null);
        priority.setValue(null);
        status.setValue(null);
        sun.setSelected(false);
        mon.setSelected(false);
        tue.setSelected(false);
        wen.setSelected(false);
        thu.setSelected(false);
        fri.setSelected(false);
        sat.setSelected(false);

        updateButton.setDisable(true);
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {

        StringBuilder appToStr = new StringBuilder();
        ArrayList<String> getDay = new ArrayList();
        if (sun.isSelected()) {
            getDay.add("sun");
        }
        if (mon.isSelected()) {
            getDay.add("mon");
        }
        if (tue.isSelected()) {
            getDay.add("tue");
        }
        if (wen.isSelected()) {
            getDay.add("wen");
        }
        if (thu.isSelected()) {
            getDay.add("thu");
        }
        if (fri.isSelected()) {
            getDay.add("fri");
        }
        if (sat.isSelected()) {
            getDay.add("sat");
        }


        int j = 0;
        for (String i : getDay) {

            appToStr.append(i);
            j++;
            if (j == getDay.size()) {

            } else {
                appToStr.append("-");
            }
        }
//
//        if (startHour.getValue()
//                != null || startMin != null ||
//                endHour.getValue() != null || endMin.getValue() != null
//                || status.getValue() != null
//                || priority.getValue() != null || (sun.isSelected()
//                || mon.isSelected() ||
//                tue.isSelected() ||
//                wen.isSelected() ||
//                thu.isSelected() ||
//                fri.isSelected() ||
//                sat.isSelected())) {
//            System.out.println("if1");





        if (startHour.getValue() != null && startMin.getValue() != null &&
                endHour.getValue() != null && endMin.getValue() != null) {

            ///check wrong times

            if (dataListWeekly.checkTimeAll(startHour.getValue(), startMin.getValue(),
                    endHour.getValue(), endMin.getValue())) {//check hour and min of timeStart and timeEnd

                warning.setText("Finish time must be after Start time.");
                clearSelectedWeekly();
            } else {

                selectedWeekly.setTimeStart(startHour.getValue() + ":" + startMin.getValue());
                selectedWeekly.setTimeEnd(endHour.getValue() + ":" + endMin.getValue());

                warning.setText("");
                clearSelectedWeekly();
                worksTable.refresh();
                worksTable.getSelectionModel().clearSelection();

                dataSourceWeekly.setData(dataListWeekly);

            }
        }

        else if (priority.getValue() != null) {

            selectedWeekly.setPriority(priority.getValue());
            warning.setText("");
            clearSelectedWeekly();
            worksTable.refresh();
            worksTable.getSelectionModel().clearSelection();

            dataSourceWeekly.setData(dataListWeekly);

        }
        else if (status.getValue() != null) {

            selectedWeekly.setStatus(status.getValue());
            warning.setText("");
            clearSelectedWeekly();
            worksTable.refresh();
            worksTable.getSelectionModel().clearSelection();

            dataSourceWeekly.setData(dataListWeekly);

        }

        else if (sun.isSelected() || mon.isSelected() ||
                tue.isSelected() || wen.isSelected() || thu.isSelected() ||
                fri.isSelected() || sat.isSelected()) {
            selectedWeekly.setDate(appToStr.toString());

            clearSelectedWeekly();
            worksTable.refresh();
            worksTable.getSelectionModel().clearSelection();

            dataSourceWeekly.setData(dataListWeekly);


        }
        else {

            warning.setText("Unable to Proceed, Selected at least one day.");
            clearSelectedWeekly();
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
