package core;

import java.util.ArrayList;

public interface Graph {

    public GraphState getGraphState();
    
    public ArrayList<Node> getNodes();
    
    public ArrayList<Edge> getEdges();

    public ArrayList<Component> getAllComponents();
}
