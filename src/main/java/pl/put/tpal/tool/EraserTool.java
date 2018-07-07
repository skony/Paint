/* @(#) $Id$
 *
 * Copyright (c) 2000-2018 ComArch S.A. All Rights Reserved. Any usage, duplication or redistribution of this
 * software is allowed only according to separate agreement prepared in written between ComArch and authorized party.
 */
package pl.put.tpal.tool;

import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;
import pl.put.tpal.DrawOperation;
import pl.put.tpal.DrawParameters;

/**
 * @author Asus
 */
public class EraserTool extends AbstractTool {
    
    private static final String ICON_PATH = "icons/draw_eraser.png";
    
    @Override
    public String getIconPath() {
        return ICON_PATH;
    }
    
    @Override
    public Optional<DrawOperation> onMouseClicked(DrawParameters drawParameters) {
        return Optional.of(new EraserOperation(drawParameters.getX(), drawParameters.getY(), drawParameters.getSize()));
    }
    
    @Override
    public Optional<DrawOperation> onMousePressed(DrawParameters drawParameters) {
        return Optional.empty();
    }
    
    @Override
    public Optional<DrawOperation> onMouseDragged(DrawParameters drawParameters) {
        return Optional.empty();
    }
    
    @Override
    public Optional<DrawOperation> onMouseReleased(DrawParameters drawParameters) {
        return Optional.empty();
    }
    
    private class EraserOperation implements DrawOperation {
        
        private double x;
        private double y;
        private double size;
        
        public EraserOperation(double x, double y, double size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
        
        @Override
        public void draw(GraphicsContext gc) {
            gc.clearRect(x, y, size, size);
        }
    }
}
