package MakeTheFuture.controllers.weekly;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import MakeTheFuture.models.*;
import MakeTheFuture.services.CategoryFileDataSource;
import MakeTheFuture.services.DataSource;
import MakeTheFuture.services.WeeklyFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class WeeklyController {

    @FXML
    TextField nameWorkTextField;
    @FXML
    CheckBox sun,mon,tue,wen,thu,fri,sat;
//    @FXML
//    ChoiceBox<Integer> startHour,startMin,endHour,endMin;
    @FXML
    ChoiceBox<String> priority,categoryName,status,startHour,startMin,endHour,endMin;
    @FXML
    Label warning;


    private DataSource dataSourceWeekly,dataSourceCategory;//type is interfacce
    private DataList dataListWeekly,dataListCategory;

    @FXML
    public void initialize(){
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSourceWeekly = new WeeklyFileDataSource("data", "weekly.csv");
                dataSourceCategory = new CategoryFileDataSource("data","category.csv");

                //dataSource = new StudentHardcodeDataSource();//information hiding
                dataListWeekly = dataSourceWeekly.getData();//อ่านแล้ว
                dataListCategory = dataSourceCategory.getData();
                status.getItems().addAll("not started","Ongoing ","Completed");
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
        if (nameWorkTextField.getText().equals("") || startHour.getValue()
                == null || startMin == null ||
                endHour.getValue() == null || endMin.getValue() == null
                || status.getValue() == null
                || priority.getValue() == null || (!sun.isSelected()
                && !mon.isSelected() &&
                !tue.isSelected() &&
                !wen.isSelected() &&
                !thu.isSelected() &&
                !fri.isSelected() &&
                !sat.isSelected())) {
            warning.setText("Please fill in the empty box before proceed.");
        }
        else {
            Weekly weekly = new Weekly(categoryName.getValue(),
                    nameWorkTextField.getText(),
                    appToStr.toString(), startHour.getValue() + ":" +
                    startMin.getValue(), endHour.getValue() + ":" + endMin.getValue(),
                    priority.getValue(), status.getValue());
            if (dataListWeekly.checkTimeAll(weekly.getTimeStart(), weekly.getTimeEnd())) {//check hour and min of timeStart and timeEnd
                warning.setText("Unable to Proceed, Start Time is after finish time.");
            }
            else if (dataListWeekly.checkNameWeekly(nameWorkTextField.getText())) {//Condition isn't same name
                warning.setText("This Work Name name has been taken.");
            }
            else{
                dataListCategory.addNameWorkCate(nameWorkTextField.getText(),categoryName.getValue());
                dataListWeekly.addWeekly(weekly);
                dataListCategory.addNumWeekly(categoryName.getValue());
                dataSourceCategory.setData(dataListCategory);
                dataSourceWeekly.setData(dataListWeekly);
                try {
                    FXRouter.goTo("manageWeeklyWork");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า manageWeeklyWork ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }
   }
//            else {
//                weekly.setCategory("-");
//                dataListWeekly.addWeekly(weekly);
//                dataSourceWeekly.setData(dataListWeekly);
//                try {
//                    FXRouter.goTo("manageWeeklyWork");
//                } catch (IOException e) {
//                    System.err.println("ไปที่หน้า manageWeeklyWork ไม่ได้");
//                    System.err.println("ให้ตรวจสอบการกำหนด route");
//                }
//
//
//
//            }
        }
    }

}
