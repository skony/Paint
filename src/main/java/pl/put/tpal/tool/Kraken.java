package pl.put.tpal.tool;

import java.util.Stack;
import javafx.geometry.Point2D;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * @author Asus
 */
public class Kraken {
    private final WritableImage image;
    private final Color colorToFill;
    
    // tolerance for color matching (on a scale of 0 to 1);
    private final double E = 0.3;
    
    public Kraken(WritableImage image, Color colorToFill) {
        this.image = image;
        this.colorToFill = colorToFill;
    }
    
    public void unleash(Point2D start, Color color) {
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = image.getPixelWriter();
        
        Stack<Point2D> stack = new Stack<>();
        stack.push(start);
        
        while (!stack.isEmpty()) {
            Point2D point = stack.pop();
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (filled(reader, x, y)) {
                continue;
            }
            
            writer.setColor(x, y, color);
            
            push(stack, x - 1, y - 1);
            push(stack, x - 1, y);
            push(stack, x - 1, y + 1);
            push(stack, x, y + 1);
            push(stack, x + 1, y + 1);
            push(stack, x + 1, y);
            push(stack, x + 1, y - 1);
            push(stack, x, y - 1);
        }
    }
    
    private void push(Stack<Point2D> stack, int x, int y) {
        if ((x < 0) || (x > image.getWidth()) ||
                (y < 0) || (y > image.getHeight())) {
            return;
        }
        
        stack.push(new Point2D(x, y));
    }
    
    private boolean filled(PixelReader reader, int x, int y) {
        Color color = reader.getColor(x, y);
        
        return !withinTolerance(color, colorToFill, E);
    }
    
    private boolean withinTolerance(Color a, Color b, double epsilon) {
        return withinTolerance(a.getRed(), b.getRed(), epsilon) &&
                withinTolerance(a.getGreen(), b.getGreen(), epsilon) &&
                withinTolerance(a.getBlue(), b.getBlue(), epsilon);
    }
    
    private boolean withinTolerance(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }
}
