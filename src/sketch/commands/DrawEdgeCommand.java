package sketch.commands;

import core.PNode;
import forms.CommonValidators;
import forms.ValidationError;
import sketch.GraphDrawer;

public class DrawEdgeCommand extends DrawCommand {

    private String rawWeight;
    private PNode firstNode;
    private PNode secondNode;

    public DrawEdgeCommand(GraphDrawer graphDrawer, String rawWeight) {
        super(graphDrawer);
        this.rawWeight = rawWeight;
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
                return addEdge();
            }

        }
        
        return true;
    }

    private boolean addEdge() {
        try {
            CommonValidators.validateCanBeDouble(rawWeight);
            double weight = Double.parseDouble(rawWeight);
            CommonValidators.validateDoubleBoundries(weight, 1, 50);

            if (firstNode.equals(secondNode)){
                throw new ValidationError("No esta permitido referencias a si mísmo");
            }

            if (graphDrawer.getGraph().isParallelEdgeInGraph(firstNode, secondNode)){
                throw new ValidationError("No esta permitido aristas paralelas");
            }
            
            graphDrawer.getGraph().addEdge(
                graphDrawer.getSketch(), 
                firstNode, 
                secondNode, 
                weight
            );
            resetSelectedNodes();
        } catch (ValidationError e) {
            setErrorMessage(e.getReason());
            resetSelectedNodes();
            return false;
        }
        return true;
    }

    private void resetSelectedNodes() {
        firstNode = null;
        secondNode = null;
    }
}
