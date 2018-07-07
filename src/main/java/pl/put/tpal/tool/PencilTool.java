package pl.put.tpal.tool;

import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;
import pl.put.tpal.DrawOperation;
import pl.put.tpal.DrawParameters;

/**
 * @author Asus
 */
public class PencilTool extends AbstractTool {
    
    private static final String ICON_PATH = "icons/pencil.png";
    
    @Override
    public String getIconPath() {
        return ICON_PATH;
    }
    
    @Override
    public Optional<DrawOperation> onMouseClicked(DrawParameters drawParameters) {
        return Optional.empty();
        // return Optional.of(new PencilOperation(x, y));
    }
    
    @Override
    public Optional<DrawOperation> onMousePressed(DrawParameters drawParameters) {
        return Optional.of(new PencilPressedOperation(drawParameters.getX(), drawParameters.getY()));
    }
    
    @Override
    public Optional<DrawOperation> onMouseDragged(DrawParameters drawParameters) {
        return Optional.of(new PencilDraggedOperation(drawParameters.getX(), drawParameters.getY()));
    }
    
    @Override
    public Optional<DrawOperation> onMouseReleased(DrawParameters drawParameters) {
        return Optional.empty();
    }
    
    private class PencilPressedOperation implements DrawOperation {
        
        private double x;
        private double y;
        
        public PencilPressedOperation(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public void draw(GraphicsContext gc) {
            gc.beginPath();
            gc.moveTo(x, y);
            gc.stroke();
        }
    }
    
    private class PencilDraggedOperation implements DrawOperation {
        
        private double x;
        private double y;
        
        public PencilDraggedOperation(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public void draw(GraphicsContext gc) {
            gc.lineTo(x, y);
            gc.stroke();
        }
    }
}
