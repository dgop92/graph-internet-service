package core.algorithms;

import java.util.ArrayList;
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

    public AlgorithmManager(Graph graph) {
        this.graph = graph;
    }

    private void buildMatrix() {
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
    }

    public boolean setMainTowerName(String mainTowerName) {
        ArrayList<Node> nodes = graph.getNodes();
        for (Node node : nodes) {
            if (node.name.equals(mainTowerName)){
                this.mainTower = node;
                return true;
            }
        }
        return false;
    }

    public String executeBFS() {
        buildMatrix();
        
        int initIndex = getIntegerByNode(mainTower);
        ArrayList<Integer> nodesVisited = getBFS(initIndex);
        ArrayList<Node> nodes = getNodesByArrOfIndeces(nodesVisited);
        hightlightPath(nodes);
        return nodes.toString();
    }

    public String executeDFS() {
        buildMatrix();

        int initIndex = getIntegerByNode(mainTower);
        ArrayList<Integer> nodesVisited = getDFS(initIndex);
        ArrayList<Node> nodes = getNodesByArrOfIndeces(nodesVisited);
        hightlightPath(nodes);
        return nodes.toString();
    }

    private void hightlightPath(ArrayList<Node> nodes){
        for (Node node : nodes) {
            node.highlightNode(new Color(249, 200, 174));
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

    private void getShortestPath(int source, int target) {

    }

    private int[] getDijkstraPrevArr(int source){

        boolean[] visited = new boolean[adjMatrix.length];
        int[] prev = new int[adjMatrix.length];
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

            for (int v = 0; v < adjMatrix.length; v++) {
                double weight = adjMatrix[dijTuple.i][v];

                if (weight != 0) {
                    double newDist = distance[dijTuple.i] + weight;
                    if (newDist < distance[v]){
                        distance[v] = newDist;
                        prev[v] = dijTuple.i;
                        pq.offer(new DijTuple(v, newDist));
                    }
                }
            }
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
