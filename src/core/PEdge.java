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

    int ajuste = 0;

    @Override
    public void update() {
        sketch.stroke(255);
        sketch.strokeWeight(5);

        sketch.line(from.x + ajuste, from.y + ajuste, to.x - ajuste, to.y - ajuste);

        colocarPeso(from.x, from.y, to.x, to.y);
    }

    public void colocarPeso(int x0, int y0, int x1, int y1) {
      
        int hipotenusa = calcularHipotenusa(x0, y0, x1, y1);
        double angulo = calcularAngulo(x0, y0, x1, y1);
     
        
        int p = hipotenusa/2;
        
        int x, y;
        
        if(x0 < x1){
        x = x0 + (int) (p * (Math.cos(Math.toRadians(angulo))));
        
        } else {
        
         x = x1 + (int) (p * (Math.cos(Math.toRadians(angulo))));
        
        } 
        
        if(y0 < y1){
        y = y0 + (int) (p * (Math.sin(Math.toRadians(angulo))));
        
        } else {
         y = y1 + (int) (p * (Math.sin(Math.toRadians(angulo))));
        
        }
        
        
        sketch.fill(255);
        sketch.circle(x,y,15);
        sketch.fill(0);
        sketch.textSize(9);
        sketch.text(String.valueOf(weight), x - sketch.textWidth(String.valueOf(weight))/2 , y + 3);
        
        
        

    }

    public int calcularHipotenusa(int x0, int y0, int x1, int y1) {

        int a = sketch.abs(x1 - x0);
        int b = sketch.abs(y1 - y0);

        int c = (int) sketch.sqrt( sketch.pow(a, 2) + sketch.pow(b, 2)) ;
        
        
        return c;

    }
    
    public double calcularAngulo(int x0, int y0, int x1, int y1) {

        double a = sketch.abs(x1 - x0);
        double b = sketch.abs(y1 - y0);

       double angulo = Math.toDegrees(Math.atan(b/a));
        
        
        return angulo;

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
