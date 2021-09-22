package core;

import java.awt.Color;

import processing.core.PApplet;

public class PNode extends Node {

    public static final int RADIUS = 25;
    private PApplet sketch;
    public int x;
    public int y;

    public PNode(PApplet sketch, String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
        this.sketch = sketch;
    }

    @Override
    public void highlightNode(Color color) {
        
    }

    @Override
    public void update() {
        sketch.fill(255);
        sketch.circle(x, y, RADIUS);
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
