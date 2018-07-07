package pl.put.tpal;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.put.tpal.controller.LanguageController;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Paint.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("languages.Paint", Locale.getDefault()));
            Parent root = (Parent) fxmlLoader.load();
            ((ApplicationController) fxmlLoader.getController()).setStage(primaryStage);
            ((ApplicationController) fxmlLoader.getController()).setLanguageController(new LanguageController(primaryStage));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
