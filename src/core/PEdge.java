package core;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class PEdge extends Edge {

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
        sketch.stroke(128, 64, 0);
        sketch.strokeWeight(5);
        
      
        
        sketch.line(from.x, from.y , to.x , to.y);

    }

    

    // highlight the edge in the search or other thing
    @Override
    public void highlightEdge(Color color) {
        
        sketch.stroke(255, 233, 0);
      

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
