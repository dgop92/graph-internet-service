package sketch.commands;

import core.PNode;
import sketch.GraphDrawer;

public class UnDrawNodeCommand extends DrawCommand{

    public UnDrawNodeCommand(GraphDrawer graphDrawer) {
        super(graphDrawer);
        graphDrawer.sendFeebackMessage("Haz click en un nodo para eliminarlo");
    }

    @Override
    public boolean execute(int x, int y) {
        
        PNode selectedNode = graphDrawer.getGraph().getPNode(x, y);
        if (selectedNode != null) {
            graphDrawer.getGraph().deleteNode(selectedNode);
        }
        
        return true;
    }
    

}
