package core;

import java.util.HashMap;

public class GraphState {
    
    private double[][] adjacencyMatrix;
    private HashMap<Integer, Node> indexNode;

    public GraphState(double[][] adjacencyMatrix, HashMap<Integer, Node> indexNode) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.indexNode = indexNode;
    }

    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Node getNodeByIndex(int i) {
        return indexNode.get(i);
    }

}
