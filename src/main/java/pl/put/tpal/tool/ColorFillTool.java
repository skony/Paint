/* @(#) $Id$
 *
 * Copyright (c) 2000-2018 ComArch S.A. All Rights Reserved. Any usage, duplication or redistribution of this
 * software is allowed only according to separate agreement prepared in written between ComArch and authorized party.
 */
package pl.put.tpal.tool;

import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import pl.put.tpal.DrawOperation;
import pl.put.tpal.DrawParameters;

/**
 * @author Asus
 */
public class ColorFillTool extends AbstractTool {
    
    private static final String ICON_PATH = "icons/color-fill-icon.png";
    
    @Override
    public String getIconPath() {
        return ICON_PATH;
    }
    
    @Override
    public Optional<DrawOperation> onMouseClicked(DrawParameters drawParameters) {
        return Optional.of(new ColorFillOperation(drawParameters.getX(), drawParameters.getY(), drawParameters.getColor()));
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
    
    private class ColorFillOperation implements DrawOperation {
        
        private double x;
        private double y;
        private Color color;
        
        public ColorFillOperation(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
        
        @Override
        public void draw(GraphicsContext gc) {
            Canvas canvas = gc.getCanvas();
            WritableImage writableImage =
                    new WritableImage((int) Math.rint(1 * canvas.getWidth()), (int) Math.rint(1 * canvas.getHeight()));
            SnapshotParameters spa = new SnapshotParameters();
            spa.setTransform(Transform.scale(1, 1));
            canvas.snapshot(spa, writableImage);
            
            Kraken kraken = new Kraken(writableImage, Color.WHITE);
            kraken.unleash(new Point2D(x, y), color);
            
            gc.drawImage(writableImage, 0, 0);
        }
    }
}
