package sketch;

import processing.core.PApplet;

public class GraphSketch extends PApplet{

    public final int SKETCH_WIDTH = 800;
    public final int SKETCH_HEIGHT = 500;


    @Override
    public void settings() {
        size(SKETCH_WIDTH, SKETCH_HEIGHT);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}