package core;

import java.awt.Color;

public abstract class Edge implements Component {
    
    public double weight;
    
    public Edge(double weight) {
        this.weight = weight;
    }

    public abstract void highlightEdge(Color color);

    public abstract void deHighlightEdge();

    public abstract Node getFrom();

    public abstract Node getTo();

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isNodeInEdge(Node node) {
        return getFrom().equals(node) || getTo().equals(node);
    }
    
}
