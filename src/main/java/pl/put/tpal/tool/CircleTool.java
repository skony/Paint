package pl.put.tpal.tool;

import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;
import pl.put.tpal.DrawOperation;
import pl.put.tpal.DrawParameters;

/**
 * @author Asus
 */
public class CircleTool extends AbstractTool {
    
    private static final String ICON_PATH = "icons/oval.png";
    private double startX;
    private double startY;
    
    @Override
    public String getIconPath() {
        return ICON_PATH;
    }
    
    @Override
    public Optional<DrawOperation> onMouseClicked(DrawParameters drawParameters) {
        return Optional.empty();
    }
    
    @Override
    public Optional<DrawOperation> onMousePressed(DrawParameters drawParameters) {
        startX = drawParameters.getX();
        startY = drawParameters.getY();
        return Optional.empty();
    }
    
    @Override
    public Optional<DrawOperation> onMouseDragged(DrawParameters drawParameters) {
        return Optional.empty();
    }
    
    @Override
    public Optional<DrawOperation> onMouseReleased(DrawParameters drawParameters) {
        return Optional.of(
                new CircleOperation(startX, startY, Math.abs(drawParameters.getX() - startX), Math.abs(drawParameters.getY() - startY)));
    }
    
    private class CircleOperation implements DrawOperation {
        
        private double x;
        private double y;
        private double w;
        private double h;
        
        public CircleOperation(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        
        @Override
        public void draw(GraphicsContext gc) {
            gc.strokeOval(x, y, w, h);
        }
    }
}
