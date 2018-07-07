/* @(#) $Id$
 *
 * Copyright (c) 2000-2018 ComArch S.A. All Rights Reserved. Any usage, duplication or redistribution of this
 * software is allowed only according to separate agreement prepared in written between ComArch and authorized party.
 */
package pl.put.tpal;

import javafx.scene.paint.Color;

/**
 * @author Asus
 */
public class DrawParameters {
    
    private double x;
    private double y;
    private double size;
    private Color color;
    
    public DrawParameters(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getSize() {
        return size;
    }
    
    public Color getColor() {
        return color;
    }
    
}
