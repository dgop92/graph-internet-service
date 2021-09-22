package sketch.commands;

import sketch.GraphDrawer;

public class DrawNodeCommand extends DrawCommand{

    private String nodeName;

    public DrawNodeCommand(GraphDrawer graphDrawer, String nodeName) {
        super(graphDrawer);
        this.nodeName = nodeName;
    }

    @Override
    public boolean execute(int x, int y) {
        if (graphDrawer.getGraph().isNameAlreadyInUse(nodeName)){
            setErrorMessage("Nombre en uso");
            return false;
        }
        if (graphDrawer.getGraph().isNodeColliding(x, y)){
            setErrorMessage("Nodo colisionando");
            return false;
        }
        graphDrawer.getGraph().addNode(graphDrawer.getSketch(), nodeName, x, y);
        
        return true;
    }
    
}
