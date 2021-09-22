package sketch.commands;

import core.PNode;
import sketch.GraphDrawer;

public class DrawEdgeCommand extends DrawCommand {

    private double weight;
    private PNode firstNode;
    private PNode secondNode;

    public DrawEdgeCommand(GraphDrawer graphDrawer, double weight) {
        super(graphDrawer);
        this.weight = weight;
    }

    @Override
    public boolean execute(int x, int y) {
        PNode selectedNode = graphDrawer.getGraph().getPNode(x, y);

        if (selectedNode != null) {
            if (firstNode == null) {
                firstNode = selectedNode;
            } else {
                secondNode = selectedNode;
            }

            if (firstNode != null && secondNode != null){
                // TODO must check if edge is already inside edge list
                graphDrawer.getGraph().addEdge(
                    graphDrawer.getSketch(), 
                    firstNode, 
                    secondNode, 
                    weight
                );
                firstNode = null;
                secondNode = null;
            } 

        }
        
        return true;
    }

}
