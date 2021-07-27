package MakeTheFuture.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CreateWorkController {
    @FXML
    public void handleGeneralButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("generalWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า GeneralWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    public void handleForwardButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("forwardWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    public void handleProjectButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("projectWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า projectWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    public void handleWeeklyButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("weeklyWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า weeklyWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    public void handleCategoryButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("category");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า category ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}
