package core;

import java.awt.Color;

import processing.core.PApplet;

public class PEdge extends Edge implements Component{

    private PNode from, to;
    private PApplet sketch;

    public PEdge(PApplet sketch, PNode from, PNode to, double weight) {
        super(from, to, weight);
        this.to = to;
        this.from = from;
        this.sketch = sketch;
    }

    @Override
    public void update() {

    }

    @Override
    public void highlightEdge(Color color) {
        
    }
    
}
