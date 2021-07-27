package MakeTheFuture;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("../../resources/home.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        FXRouter.bind(this, primaryStage, "Make The Future", 1280, 960);

        configRoute();

        FXRouter.goTo("home");
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root));

        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private static void configRoute () {
        FXRouter.when("home", "home.fxml");
        FXRouter.when("creator","creator.fxml");
        FXRouter.when("showAll","showAll.fxml");
        FXRouter.when("category","category.fxml");
        FXRouter.when("help", "help.fxml",620,570);
        FXRouter.when("generalWork", "generalWork.fxml");
        FXRouter.when("weeklyWork","weeklyWork.fxml");
        FXRouter.when("forwardWork","forwardWork.fxml");
        FXRouter.when("projectWork","projectWork.fxml");
        FXRouter.when("createWork", "createWork.fxml");
        FXRouter.when("manageGeneralWork","manage_General_Work.fxml");
        FXRouter.when("manageForwardWork","manage_Forward_Work.fxml");
        FXRouter.when("manageWeeklyWork","manage_Weekly_Work.fxml");
        FXRouter.when("manageProjectWork","manage_Project_Work.fxml");
        FXRouter.when("manageCategoryWork","manage_Category_Work.fxml");
    }




    public static void main (String[] args) {
        launch(args);
    }
}
