package core;

import java.util.ArrayList;
import java.util.Collections;

public class PEdgeGraph implements Graph{

    private ArrayList<PEdge> edges;
    private ArrayList<PNode> nodes;

    public PEdgeGraph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public void getNeighbors(Node node) {
        // still thinking
    }

    public void addNode(PNode node) {
        nodes.add(node);
    }
    
    public void addEdge(PEdge edge) {
        if (!nodes.contains(edge.from)){
            nodes.add(edge.from);
        }
        if (!nodes.contains(edge.getTo())){
            nodes.add(edge.to);
        }
        edges.add(edge);
    }

    @Override
    public ArrayList<Node> getNodes() {
        ArrayList<Node> gnodes = new ArrayList<>();
        Collections.copy(gnodes, nodes);
        return gnodes;
    }

    @Override
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> gedges = new ArrayList<>();
        Collections.copy(gedges, edges);
        return gedges;
    }

    @Override
    public ArrayList<Component> getAllComponents() {
        ArrayList<Component> components = new ArrayList<>();
        Collections.copy(components, edges);
        Collections.copy(components, nodes);
        return components;
    }
    

}
