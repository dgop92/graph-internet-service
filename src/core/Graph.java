package core;

import java.util.ArrayList;

public interface Graph {
    
    public void getNeighbors(Node node);
    
    public ArrayList<Node> getNodes();
    
    public ArrayList<Edge> getEdges();

    public ArrayList<Component> getAllComponents();
}
