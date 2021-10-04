package core.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import java.awt.Color;

import core.Graph;
import core.GraphState;
import core.Node;

public class AlgorithmManager {
    
    private Graph graph;
    private Node mainTower;

    public AlgorithmManager(Graph graph) {
        this.graph = graph;
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
        GraphState gst = graph.getGraphState();
        int initIndex = gst.getIntegerByNode(mainTower);
        ArrayList<Integer> nodesVisited = getBFS(initIndex, gst);
        ArrayList<Node> nodes = getNodesByArrOfIndeces(gst, nodesVisited);
        hightlightPath(nodes);
        return nodes.toString();
    }

    public String executeDFS() {
        GraphState gst = graph.getGraphState();
        int initIndex = gst.getIntegerByNode(mainTower);
        ArrayList<Integer> nodesVisited = getDFS(initIndex, gst);
        ArrayList<Node> nodes = getNodesByArrOfIndeces(gst, nodesVisited);
        hightlightPath(nodes);
        return nodes.toString();
    }

    private void hightlightPath(ArrayList<Node> nodes){
        for (Node node : nodes) {
            node.highlightNode(new Color(249, 200, 174));
        }
    }

    private ArrayList<Node> getNodesByArrOfIndeces(
        GraphState gst,
        ArrayList<Integer> indices
    ){
        ArrayList<Node> nodes = new ArrayList<>();
        
        for(int indice: indices){
            nodes.add(gst.getNodeByIndex(indice));
        }
        
        return nodes;
    }

    //Breadth First Search
    private ArrayList<Integer> getBFS(int i, GraphState gst) {
        double [][] matrix = gst.getAdjacencyMatrix();
        
        ArrayList<Integer> nodesVisited = new ArrayList<>();
        boolean[] visited = new boolean[matrix.length];
        
        Queue<Integer> queue = new LinkedList<>();
        visited[i] = true;
        queue.add(i);

        while (!queue.isEmpty()) {
            int currIndex = queue.remove();

            nodesVisited.add(currIndex);

            for (int v = 0; v < matrix.length; v++) {
                if (matrix[currIndex][v] != 0) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.add(v);
                    }
                }
            }
        }

        return nodesVisited;
    }

    private ArrayList<Integer> getDFS(int i, GraphState gst) {
        ArrayList<Integer> nodesVisited = new ArrayList<>();
        double [][] matrix = gst.getAdjacencyMatrix();
        boolean[] visited = new boolean[matrix.length];
        DFSRecursive(i, matrix, nodesVisited, visited);
        return nodesVisited;
    }

    private void DFSRecursive(
        int u, 
        double[][] matrix, 
        ArrayList<Integer> nodesVisited,
        boolean[] visited
    ) {
        nodesVisited.add(u);
        visited[u] = true;
        for (int v = 0; v < matrix.length; v++) {
            if (matrix[u][v] != 0) {
                if (!visited[v]) {
                    DFSRecursive(v, matrix, nodesVisited, visited);
                }
            }
        }
    }
}
