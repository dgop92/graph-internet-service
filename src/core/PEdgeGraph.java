package core;

import java.util.ArrayList;

import processing.core.PApplet;

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

    public void addNode(PApplet sketch, String name, int x, int y) {
        nodes.add(new PNode(sketch, name, x, y));
    }
    
    public void addEdge(PApplet sketch, PNode from, PNode to, double weight) {
        edges.add(new PEdge(sketch, from, to, weight));
    }

    public boolean isNodeInGraph(PNode node) {
        return nodes.contains(node);
    }

    @Override
    public ArrayList<Node> getNodes() {
        ArrayList<Node> gnodes = new ArrayList<>();
        gnodes.addAll(nodes);
        return gnodes;
    }
    
    public ArrayList<PNode> getPNodes() {
        return nodes;
    }

    @Override
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> gedges = new ArrayList<>();
        gedges.addAll(edges);
        return gedges;
    }

    
    public ArrayList<PEdge> getPEdges() {
        return edges;
    }

    @Override
    public ArrayList<Component> getAllComponents() {
        ArrayList<Component> components = new ArrayList<>();
        components.addAll(nodes);
        components.addAll(edges);
        return components;
    }
    

}
