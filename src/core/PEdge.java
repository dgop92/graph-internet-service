package core;

import java.awt.Color;

import processing.core.PApplet;

public class PEdge extends Edge{

    public PNode from, to;
    private PApplet sketch;

    public PEdge(PApplet sketch, PNode from, PNode to, double weight) {
        super(weight);
        this.from = from;
        this.to = to;
        this.sketch = sketch;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void highlightEdge(Color color) {
        
    }

    @Override
    public Node getFrom() {
        return this.from;
    }

    @Override
    public Node getTo() {
        return this.to;
    }
    
}
