package pl.put.tpal.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Asus
 */
public class LanguageController {
    
    static int x = 0;
    private static Stage stage;
    
    public LanguageController(Stage stage) {
        if (stage != null) {
            LanguageController.stage = stage;
        }
    }
    
    public void setLanguage(Locale locale) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Paint.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("languages.Paint", locale));
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
        if (x == 0) {
            anchorPane.getChildren().remove(0);
        } else {
            anchorPane.getChildren().remove(4);
        }
        anchorPane.getChildren().add(root.getChildren().get(0));
        ++x;
    }
}
