package core;

import java.util.HashMap;

public class GraphState {
    
    private double[][] adjacencyMatrix;
    private HashMap<Integer, Node> indexNode;
    private HashMap<Node, Integer> nodeIndex;

    public GraphState(
        double[][] adjacencyMatrix, 
        HashMap<Integer, Node> indexNode, 
        HashMap<Node, Integer> nodeIndex
    ) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.indexNode = indexNode;
        this.nodeIndex = nodeIndex;
    }

    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Node getNodeByIndex(int i) {
        return indexNode.get(i);
    }

    public int getIntegerByNode(Node node) {
        return nodeIndex.getOrDefault(node, 0);
    }

}
