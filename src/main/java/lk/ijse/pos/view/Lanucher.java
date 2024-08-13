package lk.ijse.pos.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lanucher extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent parent =  FXMLLoader.load(getClass().getResource("/view/view/login.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Login");
        stage.centerOnScreen();

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
