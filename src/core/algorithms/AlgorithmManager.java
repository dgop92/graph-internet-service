package core.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import java.awt.Color;

import core.Edge;
import core.Graph;
import core.Node;

public class AlgorithmManager {
    
    private Graph graph;
    private Node mainTower;
    private final double MAX_VALUE = 10000000;
    private double[][] adjMatrix;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private Integer[] dijPrev;

    public AlgorithmManager(Graph graph) {
        this.graph = graph;
    }

    public void buildMatrix() {
        nodes = graph.getNodes();
        edges = graph.getEdges();
        int n = nodes.size();

        adjMatrix = new double[n][n];
        for (Edge edge : edges) {
            Node from = edge.getFrom();
            Node to = edge.getTo();
            adjMatrix[nodes.indexOf(from)][nodes.indexOf(to)] = edge.weight;
            adjMatrix[nodes.indexOf(to)][nodes.indexOf(from)] = edge.weight;
        }
        dijPrev = null;
    }

    private void ensureGraphExists() throws AlgorithmException {
        if (adjMatrix == null){
            throw new AlgorithmException(
                "El grafo no contiene vertices, " +
                "debes crear por lo menos un nodo, para hacer las operaciones");
        }
    }

    public boolean setMainTowerName(String mainTowerName) {
        ArrayList<Node> nodes = graph.getNodes();
        for (Node node : nodes) {
            if (node.name.equals(mainTowerName)){
                this.mainTower = node;
                dijPrev = null;
                return true;
            }
        }
        return false;
    }

    public String executeBFS() throws AlgorithmException {
        ensureGraphExists();
        
        int initIndex = getIntegerByNode(mainTower);
        ArrayList<Integer> nodesVisited = getBFS(initIndex);
        ArrayList<Node> nodes = getNodesByArrOfIndeces(nodesVisited);
        hightlightPath(nodes);
        return nodes.toString();
    }

    public String executeDFS() throws AlgorithmException {
        ensureGraphExists();

        int initIndex = getIntegerByNode(mainTower);
        ArrayList<Integer> nodesVisited = getDFS(initIndex);
        ArrayList<Node> nodes = getNodesByArrOfIndeces(nodesVisited);
        hightlightPath(nodes);
        return nodes.toString();
    }

    public String executeShortestPath(String targetNodeName) throws AlgorithmException {
        ensureGraphExists();

        Node targetNode = null;
        for (Node n : nodes) {
            if (n.name.equals(targetNodeName)){
                targetNode = n;
            }
        }

        if (targetNode != null){
            int source = getIntegerByNode(mainTower);
            int target = getIntegerByNode(targetNode);

            ArrayList<Integer> path = getShortestPath(source, target);
            ArrayList<Node> nodePath = getNodesByArrOfIndeces(path);
            ArrayList<Edge> edgesPath = getEdgesFromNodePath(nodePath);
            hightlightEdges(edgesPath);
            return nodePath.toString();
        } else {
            throw new AlgorithmException(
                "El nodo seleccionado como final, " +
                "no existe. Por favor, Elige un nodo que sí exista"
            );
        }
        
    }

    public String executeShortestPathToAllNodes() throws AlgorithmException{
        ensureGraphExists();

        String output = "";

        ArrayList<Edge> allEdges = new ArrayList<>();
        
        int source = getIntegerByNode(mainTower);
        int n = graph.getNodes().size();
        for (int i = 0; i < n; i++) {
            if (i != source) {
                ArrayList<Integer> path = getShortestPath(source, i);
                ArrayList<Node> nodePath = getNodesByArrOfIndeces(path);
                output += String.format(
                    "%s : %s \n", 
                    getNodeByIndex(i).name, nodePath.toString()
                );
                allEdges.addAll(getEdgesFromNodePath(nodePath)); 
            }
        }

        hightlightEdges(allEdges);

        return output;
    }

    /* private void highlightPath(ArrayList<Edge> edgesPath) {
        for (Edge edge : edgesPath) {
            edge.highlightEdge(new Color(174, 249, 241));
            edge.getFrom().highlightNode(new Color(249, 200, 174));
            edge.getTo().highlightNode(new Color(249, 200, 174));
        }
        waitHighlight(1500);
        for (Edge edge : edgesPath) {
            edge.deHighlightEdge();
            edge.getFrom().deHighlightNode();
            edge.getTo().deHighlightNode();
        }
    } */

    public ArrayList<Edge> getEdgesFromNodePath(ArrayList<Node> currNodes){
        ArrayList<Edge> edgesPath = new ArrayList<>();
        for (int i = 0; i < currNodes.size() - 1; i++) {
            Edge edge = getEdgeFromNodes(currNodes.get(i), currNodes.get(i + 1));
            edgesPath.add(edge);
        }
        return edgesPath;
    }

    private Edge getEdgeFromNodes(Node from, Node to) {
        for (Edge edge : edges) {
            if (edge.getFrom().equals(from) && edge.getTo().equals(to)){
                return edge;
            }
            if (edge.getFrom().equals(to) && edge.getTo().equals(from)){
                return edge;
            }
        }
        return null;
    }

    private void hightlightPath(ArrayList<Node> nodes){
        for (Node node : nodes) {
            node.highlightNode(new Color(249, 200, 174));
            waitHighlight(500);
            node.deHighlightNode();
        }
    }

    private void hightlightEdges(ArrayList<Edge> edgePath){
        for (Edge edge : edgePath) {
            edge.highlightEdge(new Color(174, 249, 241));
            edge.getFrom().highlightNode(new Color(249, 200, 174));
            edge.getTo().highlightNode(new Color(249, 200, 174));
            waitHighlight(500);
        }
        waitHighlight(1000);
        for (Edge edge : edgePath) {
            edge.deHighlightEdge();
            edge.getFrom().deHighlightNode();
            edge.getTo().deHighlightNode();
        }
    }

    private ArrayList<Node> getNodesByArrOfIndeces(ArrayList<Integer> indices){
        ArrayList<Node> nodes = new ArrayList<>();
        
        for(int indice: indices){
            nodes.add(getNodeByIndex(indice));
        }
        
        return nodes;
    }

    //Breadth First Search
    private ArrayList<Integer> getBFS(int i) {
        ArrayList<Integer> nodesVisited = new ArrayList<>();
        boolean[] visited = new boolean[adjMatrix.length];
        
        Queue<Integer> queue = new LinkedList<>();
        visited[i] = true;
        queue.add(i);

        while (!queue.isEmpty()) {
            int currIndex = queue.remove();

            nodesVisited.add(currIndex);

            for (int v = 0; v < adjMatrix.length; v++) {
                if (adjMatrix[currIndex][v] != 0) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.add(v);
                    }
                }
            }
        }

        return nodesVisited;
    }

    private ArrayList<Integer> getDFS(int i) {
        ArrayList<Integer> nodesVisited = new ArrayList<>();
        boolean[] visited = new boolean[adjMatrix.length];
        DFSRecursive(i, nodesVisited, visited);
        return nodesVisited;
    }

    private void DFSRecursive(
        int u, 
        ArrayList<Integer> nodesVisited,
        boolean[] visited
    ) {
        nodesVisited.add(u);
        visited[u] = true;
        for (int v = 0; v < adjMatrix.length; v++) {
            if (adjMatrix[u][v] != 0) {
                if (!visited[v]) {
                    DFSRecursive(v, nodesVisited, visited);
                }
            }
        }
    }

    private ArrayList<Integer> getShortestPath(int source, int target) {
        if (dijPrev == null) {
            dijPrev = getDijkstraPrevArr(source);
        }
        ArrayList<Integer> path = reconstructPath(source, target, dijPrev);
        return path;
    }

    private ArrayList<Integer> reconstructPath(int source, int target, Integer[] prev) {
        ArrayList<Integer> path = new ArrayList<>();
        
        for (Integer at = target; at != null; at = prev[at]) path.add(at);
        
        Collections.reverse(path);

        if (path.get(0) == source) return path;

        path.clear();
        return path;
    }

    private Integer[] getDijkstraPrevArr(int source){

        boolean[] visited = new boolean[adjMatrix.length];
        Integer[] prev = new Integer[adjMatrix.length];
        double[] distance = new double[adjMatrix.length];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = MAX_VALUE;
        }
        distance[source] = 0;

        PriorityQueue<DijTuple> pq = new PriorityQueue<>(); 
        pq.offer(new DijTuple(source, 0));

        while (!pq.isEmpty()){
            DijTuple dijTuple = pq.poll();
            visited[dijTuple.i] = true;

            Node currNode = getNodeByIndex(dijTuple.i);
            currNode.highlightNode(new Color(249, 200, 174));

            for (int v = 0; v < adjMatrix.length; v++) {
                double weight = adjMatrix[dijTuple.i][v];

                Node toNode = getNodeByIndex(v);

                if (weight != 0) {
                    double newDist = distance[dijTuple.i] + weight;
                    Edge edge = getEdgeFromNodes(currNode, toNode);
                    edge.highlightEdge(new Color(213, 238, 27));
                    toNode.highlightNode(new Color(197, 174, 249));

                    waitHighlight(1500);
                    
                    if (newDist < distance[v]){
                        toNode.highlightNode(new Color(247, 249, 174));
                        waitHighlight(500);
                        toNode.deHighlightNode();
                        distance[v] = newDist;
                        prev[v] = dijTuple.i;
                        pq.offer(new DijTuple(v, newDist));
                    }

                    edge.deHighlightEdge();
                    toNode.deHighlightNode();
                }
            }

            currNode.deHighlightNode();

        }

        return prev;
    }

    private int getIntegerByNode(Node node){
        int index = nodes.indexOf(node);
        return index == -1 ? 0 : index;
    }

    private Node getNodeByIndex(int i){
        return nodes.get(i);
    }

    public void waitHighlight(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class DijTuple implements Comparable<DijTuple>{

        public int i;
        public double weight;

        public DijTuple(int i, double weight) {
            this.i = i;
            this.weight = weight;
        }

        @Override
        public int compareTo(DijTuple o) {
            if (this.i < o.i) {
                return -1;
            }else if (this.i > o.i) {
                return 1;
            }else{
                return 0;
            }
        }

    }
}
