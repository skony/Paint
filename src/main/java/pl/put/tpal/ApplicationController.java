package pl.put.tpal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.put.tpal.controller.FileController;
import pl.put.tpal.controller.LanguageController;
import pl.put.tpal.controller.ToolController;
import pl.put.tpal.tool.CircleTool;
import pl.put.tpal.tool.ColorFillTool;
import pl.put.tpal.tool.EraserTool;
import pl.put.tpal.tool.PencilTool;
import pl.put.tpal.tool.RectangleTool;
import pl.put.tpal.tool.Tool;

public class ApplicationController implements Initializable {
    
    private LanguageController languageController = new LanguageController(null);
    private Stage stage;
    private File currentFile;
    private List<Tool> tools = new ArrayList<>();
    private Tool currentTool;
    private DrawBoard drawBoard;
    private Color color = Color.WHITE;
    private double size = 1.0;
    
    @FXML
    Canvas canvas;
    
    @FXML
    FlowPane flowPane;
    
    @FXML
    ColorPicker colorPicker;
    
    @FXML
    Slider slider;
    
    @Override
    public void initialize(URL arg0, ResourceBundle resourceBundle) {
        drawBoard = new DrawBoard(canvas.getGraphicsContext2D());
        
        tools.add(new PencilTool());
        tools.add(new CircleTool());
        tools.add(new RectangleTool());
        tools.add(new EraserTool());
        tools.add(new ColorFillTool());
        try {
            tools.addAll(ToolPluginProvider.getPlugedItems());
        } catch (Exception exception) {
            
        }
        for (Tool tool: tools) {
            try {
                tool.setUp(flowPane);
            } catch (IOException exception) {
            }
        }
        
        initDraw();
        initSlider();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setLanguageController(LanguageController languageController) {
        this.languageController = languageController;
    }
    
    public void onOpen(ActionEvent event) {
        currentFile = FileController.open(stage, canvas);
    }
    
    public void onSaveAs(ActionEvent event) throws IOException {
        FileController.saveAs(stage, canvas);
    }
    
    public void onSave(ActionEvent event) throws IOException {
        FileController.save(canvas, currentFile);
    }
    
    public void onMouseClicked(MouseEvent event) {
        DrawParameters drawParameters = new DrawParameters(event.getX(), event.getY(), size, color);
        Optional<DrawOperation> drawOperation = currentTool.onMouseClicked(drawParameters);
        drawOperation.ifPresent(drawBoard::addDrawOperation);
    }
    
    public void onMousePressed(MouseEvent event) {
        DrawParameters drawParameters = new DrawParameters(event.getX(), event.getY(), size, color);
        Optional<DrawOperation> drawOperation = currentTool.onMousePressed(drawParameters);
        drawOperation.ifPresent(drawBoard::addDrawOperation);
    }
    
    public void onMouseDragged(MouseEvent event) {
        DrawParameters drawParameters = new DrawParameters(event.getX(), event.getY(), size, color);
        Optional<DrawOperation> drawOperation = currentTool.onMouseDragged(drawParameters);
        drawOperation.ifPresent(drawBoard::addDrawOperation);
    }
    
    public void onMouseReleased(MouseEvent event) {
        DrawParameters drawParameters = new DrawParameters(event.getX(), event.getY(), size, color);
        Optional<DrawOperation> drawOperation = currentTool.onMouseReleased(drawParameters);
        drawOperation.ifPresent(drawBoard::addDrawOperation);
    }
    
    public void onFlowPaneMouseClicked(MouseEvent event) {
        ToolController.getCurrentTool(event, flowPane, tools).ifPresent(tool -> currentTool = tool);
    }
    
    public void onColorPickerAction(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color = colorPicker.getValue());
        gc.setFill(color);
    }
    
    public void onUndo(ActionEvent event) {
        drawBoard.undo();
    }
    
    public void onRedo(ActionEvent event) {
        drawBoard.redo();
    }
    
    public void onEnglish(ActionEvent event) throws IOException {
        languageController.setLanguage(new Locale("en", "EN"));
        
    }
    
    public void onPolish(ActionEvent event) throws IOException {
        languageController.setLanguage(new Locale("pl", "PL"));
    }
    
    private void initDraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setFill(color);
        gc.setLineWidth(1);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        colorPicker.setValue(color);
    }
    
    private void initSlider() {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                size = new_val.doubleValue() / 10;
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setLineWidth(size);
            }
        });
    }
}
