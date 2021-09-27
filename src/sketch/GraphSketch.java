package sketch;

import processing.core.PApplet;

public class GraphSketch extends PApplet{

    public final int SKETCH_WIDTH = 800;
    public final int SKETCH_HEIGHT = 500;

    private GraphDrawer graphDrawer;

    public GraphSketch() {
        super();
        
        graphDrawer = new GraphDrawer(this);
    }

    @Override
    public void settings() {
        size(SKETCH_WIDTH, SKETCH_HEIGHT);
    }

    @Override
    public void draw() {
        background(0);
        
        graphDrawer.draw();
    }

    @Override
    public void mouseClicked() {
        graphDrawer.onClickScreen(mouseX, mouseY);
        super.mouseClicked();
    }

    public GraphDrawer getGraphDrawer() {
        return graphDrawer;
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }
}