package sketch.commands;

import forms.CommonValidators;
import forms.ValidationError;
import sketch.GraphDrawer;

public class DrawNodeCommand extends DrawCommand{

    private String nodeName;

    public DrawNodeCommand(GraphDrawer graphDrawer, String nodeName) {
        super(graphDrawer);
        this.nodeName = nodeName;
        graphDrawer.sendFeebackMessage("Haz click en el lienzo para crear un nodo");
    }

    @Override
    public boolean execute(int x, int y) {
        try {
            CommonValidators.validateEmptyString(nodeName);
            CommonValidators.validateStringBoundries(nodeName, 1, 4);
            
            if (graphDrawer.getGraph().isNameAlreadyInUse(nodeName)){
                throw new ValidationError("El Nombre del nodo esta en uso");
            }
            if (graphDrawer.getGraph().isNodeColliding(x, y)){
                throw new ValidationError("El Nodo a crear esta muy cerca de otro");
            }
        } catch (ValidationError e) {
            setErrorMessage(e.getReason());
            return false;
        }
        graphDrawer.getGraph().addNode(graphDrawer.getSketch(), nodeName, x, y);
        return true;
    }
    
}
