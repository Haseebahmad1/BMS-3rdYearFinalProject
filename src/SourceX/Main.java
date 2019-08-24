package SourceX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage LogginPage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScr.fxml"));
        primaryStage.setTitle("Banking System - LOGIN");
        primaryStage.setScene(new Scene(root, 600, 430));

        Image image = new Image("Icons/Bank.png");
        primaryStage.getIcons().add(image);

        primaryStage.show();
        LogginPage = primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
