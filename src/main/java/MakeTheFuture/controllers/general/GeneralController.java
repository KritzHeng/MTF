package MakeTheFuture.controllers.general;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import MakeTheFuture.models.Category;
import MakeTheFuture.models.General;
import MakeTheFuture.models.DataList;
import MakeTheFuture.services.CategoryFileDataSource;
import MakeTheFuture.services.DataSource;
import MakeTheFuture.services.GeneralFileDataSource;


import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class GeneralController{
//    ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();
//
//
//
//    @FXML
//    void getDate(MouseEvent event) {//for submit
//        for (LocalDate date : selectedDates) {
//            System.out.println(date);
//        }
//    }
    @FXML
    Label warning;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField nameWorkTextField;
//    @FXML
//    ChoiceBox<Integer> startHour,startMin,endHour,endMin;
    @FXML
    ChoiceBox<String> startHour,startMin,endHour,endMin,priority,status,categoryName;
    @FXML Button updateButton;




    private DataSource dataSourceGeneral,dataSourceCategory;//type is interfacce
    private DataList dataListGeneral,dataListCategory;

    @FXML
    public void initialize(){

        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSourceGeneral = new GeneralFileDataSource("data", "general.csv");
                dataSourceCategory = new CategoryFileDataSource("data","category.csv");

                //dataSource = new StudentHardcodeDataSource();//information hiding
                dataListGeneral = dataSourceGeneral.getData();//อ่านแล้ว
                dataListCategory = dataSourceCategory.getData();

                status.getItems().addAll("Has not started yet","Ongoing","Completed");
                priority.getItems().addAll("1.Important","2.Normal","3.Low"); // ระดับความสำคัญ
                for (int i = 0; i <= 23; i++) {
                    if (i <= 9) {
                        startHour.getItems().add("0" + i);
                    } else {
                        startHour.getItems().add(String.valueOf(i));
                    }
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

                for (Category category : dataListCategory.toListCategory()) {
                    categoryName.getItems().add(category.getNameCate());
                }
            }
        });
        categoryName.setValue("-");

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
    public void handleSubmitButton (ActionEvent actionEvent) {
        if (nameWorkTextField.getText().equals("") || datePicker.getValue() == null
                || startHour.getValue() == null ||
                endHour.getValue() == null || endMin.getValue() == null
                || status.getValue() == null || priority.getValue() == null) {
            warning.setText("Please fill in the empty box before proceed.");
        } else {


            General general = new General(categoryName.getValue(), nameWorkTextField.getText(),
                    datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    startHour.getValue() + ":" + startMin.getValue()
                    , endHour.getValue() + ":" + endMin.getValue(), priority.getValue(),
                    status.getValue());

            if (dataListGeneral.checkTimeAll(general.getTimeStart(), general.getTimeEnd())) {//check hour and min of timeStart and timeEnd
                warning.setText("The finish time must be after Start time.");
            }
            else if (dataListGeneral.checkNameGeneral(nameWorkTextField.getText())) {//Condition isn't same name
                warning.setText("This Work Name has been taken.");
            }
            else {

                dataListCategory.addNameWorkCate(nameWorkTextField.getText(), categoryName.getValue());
                dataListCategory.addNumGeneral(categoryName.getValue());
                dataListGeneral.addGeneral(general);
                dataSourceGeneral.setData(dataListGeneral);
                dataSourceCategory.setData(dataListCategory);
                try {
                    FXRouter.goTo("manageGeneralWork");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า manageGeneralWork ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route"); }

            }


//            else {
//                general.setCategory("-");
////               dataListCategory.addNameWorkNoneCate(nameWorkTextField.getText(),categoryName.getValue());
//                dataListGeneral.addGeneral(general);
//                dataSourceGeneral.setData(dataListGeneral);
//                try {
//                    FXRouter.goTo("manageGeneralWork");
//                } catch (IOException e) {
//                    System.err.println("ไปที่หน้า manageGeneralWork ไม่ได้");
//                    System.err.println("ให้ตรวจสอบการกำหนด route"); }
//            }
//
      }

    }




}