package core;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class PEdgeGraph implements Graph{

    private ArrayList<PEdge> edges;
    private ArrayList<PNode> nodes;

    public PEdgeGraph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
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

    public boolean isParallelEdgeInGraph(PNode a, PNode b) {
        for (PEdge pEdge : edges) {
            if (pEdge.from.equals(a) && pEdge.to.equals(b)){
                return true;
            }
            if (pEdge.from.equals(b) && pEdge.to.equals(a)){
                return true;
            }
        }
        return false;
    }

    public void addNode(PApplet sketch, String name, int x, int y) {
        nodes.add(new PNode(sketch, name, x, y));
    }
    
    public void addEdge(PApplet sketch, PNode from, PNode to, double weight) {
        edges.add(new PEdge(sketch, from, to, weight));
    }

    public boolean isNameAlreadyInUse(String nodeName) {
        for (PNode node : nodes) {
            if (node.getName().equals(nodeName)){
                return true;
            }
        }
        return false;
    }

    public boolean isNodeColliding(int x, int y) {
        double offset = 30;
        double diameter = 2 * PNode.RADIUS + offset;
        for (PNode node : nodes) {
            double pt1 = Math.pow(x - node.x, 2) + Math.pow(y - node.y, 2);
            double pt2 = Math.pow(diameter, 2);
            if (pt1 < pt2){
                return true;
            }
        }
        return false;
    }

    public PNode getPNode(int x, int y) {
        double radius = PNode.RADIUS;
        for (PNode node : nodes) {
            double pt1 = Math.pow(x - node.x, 2) + Math.pow(y - node.y, 2);
            double pt2 = Math.pow(radius, 2);
            if (pt1 < pt2){
                return node;
            }
        }
        return null;
    }

    public void deleteNode(PNode node) {
        edges.removeIf(pEdge -> pEdge.isNodeInEdge(node));
        nodes.remove(node);
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
        components.addAll(edges);
        components.addAll(nodes);
        return components;
    }

}
