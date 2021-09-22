package core;

import java.util.ArrayList;

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
        gnodes.addAll(nodes);
        return gnodes;
    }

    @Override
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> gedges = new ArrayList<>();
        gedges.addAll(edges);
        return gedges;
    }

    @Override
    public ArrayList<Component> getAllComponents() {
        ArrayList<Component> components = new ArrayList<>();
        components.addAll(nodes);
        components.addAll(edges);
        return components;
    }
    

}
