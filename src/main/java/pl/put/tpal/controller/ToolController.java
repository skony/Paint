package pl.put.tpal.controller;

import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import pl.put.tpal.tool.Tool;

/**
 * @author Asus
 */
public class ToolController {
    
    public static Optional<Tool> getCurrentTool(MouseEvent event, FlowPane flowPane, List<Tool> tools) {
        double x = event.getSceneX();
        double y = event.getSceneY();
        ObservableList<Node> childrens = flowPane.getChildren();
        for (Node node: childrens) {
            Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
            if ((boundsInScene.getMaxX() > x) && (boundsInScene.getMinX() < x)) {
                if ((boundsInScene.getMaxY() > y) && (boundsInScene.getMinY() < y)) {
                    for (Tool tool: tools) {
                        if (tool.getId().equals(node.getId())) {
                            return Optional.of(tool);
                        }
                    }
                }
            }
        }
        
        return Optional.empty();
    }
}
