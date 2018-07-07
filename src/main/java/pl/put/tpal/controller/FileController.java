package pl.put.tpal.controller;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class FileController {
    
    static final FileChooser fileChooser = new FileChooser();
    
    public static File open(Window stage, Canvas canvas) {
        configureFileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file, canvas);
            return file;
        }
        return null;
    }
    
    public static void saveAs(Window stage, Canvas canvas) throws IOException {
        WritableImage writableImage = canvas.snapshot(null, null);
        File outFile = fileChooser.showSaveDialog(stage);
        ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", outFile);
    }
    
    public static void save(Canvas canvas, File file) throws IOException {
        WritableImage writableImage = canvas.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
    }
    
    private static void configureFileChooser() {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }
    
    private static void openFile(File file, Canvas canvas) {
        Image image = new Image(file.toURI().toString());
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(image, 0, 0);
    }
}
