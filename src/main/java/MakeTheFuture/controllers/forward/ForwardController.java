package MakeTheFuture.controllers.forward;

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
import MakeTheFuture.models.Forward;
import MakeTheFuture.services.CategoryFileDataSource;
import MakeTheFuture.services.DataSource;
import MakeTheFuture.services.ForwardFileDataSource;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ForwardController {
    @FXML
    DatePicker datePicker;
    @FXML
    TextField nameWorkTextField, nameResTextField;
//    @FXML
//    ChoiceBox<Integer> startHour, startMin;
    @FXML
    ChoiceBox<String> priority, status, categoryName, timeHour, timeMin;
    @FXML
    Label warning;


    private DataSource dataSourceForward,dataSourceCategory;//type is interfacce
    private DataList dataListForward,dataListCategory;

    @FXML
    public void initialize () {
        Platform.runLater((Runnable) new Runnable() {
            @Override
            public void run () {
                dataSourceForward = new ForwardFileDataSource("data", "forward.csv");
              dataSourceCategory =new CategoryFileDataSource("data","category.csv");
//                dataSourceCategory = new CategoryFileDataSource("data","category.csv");
                //dataSource = new StudentHardcodeDataSource();//information hiding
                dataListForward = dataSourceForward.getData();//อ่านแล้ว
                dataListCategory = dataSourceCategory.getData();

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
                        timeMin.getItems().add(String.valueOf(i));
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
        if (nameWorkTextField.getText().isEmpty() || nameResTextField.getText().isEmpty() ||
                timeHour.getValue() == null || timeMin.getValue() == null ||
                datePicker.getValue() == null || priority.getValue() == null ||
                status.getValue() == null) {
            warning.setText("Please fill in the empty box before proceed."); }
        else if (dataListForward.checkNameForward(nameWorkTextField.getText())) { warning.setText("This Work Name name has been taken."); }
        else{
//            if (categoryName.getValue()!= null){
                Forward forward = new Forward(categoryName.getValue(),nameWorkTextField.getText(), nameResTextField.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        timeHour.getValue() + ":" + timeMin.getValue(), priority.getValue(), status.getValue());
                dataListCategory.addNameWorkCate(nameWorkTextField.getText(),categoryName.getValue());
                dataListCategory.addNumForward(categoryName.getValue());
                dataListForward.addForward(forward);
                dataSourceCategory.setData(dataListCategory);
                dataSourceForward.setData(dataListForward);
//        }
//            else {
//                Forward forward = new Forward("-", nameWorkTextField.getText(), nameResTextField.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                        timeHour.getValue() + ":" + timeMin.getValue(), priority.getValue(), status.getValue());
//                dataListForward.addForward(forward);
//                dataSourceForward.setData(dataListForward);
//            }
            try {
                FXRouter.goTo("manageForwardWork"); } catch (IOException e) {
                System.err.println("ไปที่หน้า manageGeneralWork ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }



    }

}
