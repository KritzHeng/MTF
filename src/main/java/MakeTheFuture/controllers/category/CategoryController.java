package MakeTheFuture.controllers.category;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import MakeTheFuture.models.Category;
import MakeTheFuture.models.DataList;
import MakeTheFuture.services.CategoryFileDataSource;
import MakeTheFuture.services.DataSource;

import java.io.IOException;

public class CategoryController {
    @FXML
    TextField categoryTextField;

    private DataSource dataSource;
    private DataList dataList;
    @FXML
    Label warning;


    @FXML
    public void initialize(){
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSource = new CategoryFileDataSource("data", "category.csv");
                //dataSource = new StudentHardcodeDataSource();//information hiding
                dataList = dataSource.getData();//อ่านแล้ว
            }
        });


    }
    public void handleSubmitButton(ActionEvent actionEvent){

        Category category = new Category(categoryTextField.getText(),0,0,0,0,0,"-");
        if (dataList.checkNameCate(category.getNameCate())) {
            dataList.addCategories(category);
            dataSource.setData(dataList);
            try {
                FXRouter.goTo("home");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า manageCategoryWork ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else{
            warning.setText("This Category name has been taken, please choose a new name.");
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
