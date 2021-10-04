package core;

import java.awt.Color;

import processing.core.PApplet;

public class PNode extends Node {

    public static final int RADIUS = 38;
    private PApplet sketch;
    public int x;
    public int y;
    private Color currNodeColor;
    private final Color DEFAULT_COLOR = new Color(255, 255, 255);

    public PNode(PApplet sketch, String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
        this.sketch = sketch;
        currNodeColor = DEFAULT_COLOR;
    }

    @Override
    public void highlightNode(Color color) {
        this.currNodeColor = color;
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
        this.currNodeColor = DEFAULT_COLOR;
    }

    @Override
    public void update() {
        sketch.fill(
            currNodeColor.getRed(), 
            currNodeColor.getGreen(), 
            currNodeColor.getBlue()
        );
        sketch.stroke(0);
        sketch.strokeWeight(2);
        sketch.circle(x, y, RADIUS);

        sketch.fill(0);
        sketch.textSize(15);
        sketch.text(name, x - sketch.textWidth(name) / 2, y + 6);
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
