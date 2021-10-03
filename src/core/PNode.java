package core;

import java.awt.Color;

import processing.core.PApplet;

public class PNode extends Node {

    public static final int RADIUS = 30;
    private PApplet sketch;
    public int x;
    public int y;

    public PNode(PApplet sketch, String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
        this.sketch = sketch;
//        sketch.loadImage(name);
        
    }

    @Override
    public void highlightNode(Color color) {

    }

    
    // 
    @Override
    public void update() {
        sketch.fill(255, 0, 0);
        sketch.stroke(0);
        sketch.strokeWeight(2);
        sketch.circle(x, y, RADIUS);

        sketch.fill(0);
        sketch.textSize(15);
        sketch.text(name, x - sketch.textWidth(name)/2, y  + 6);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
