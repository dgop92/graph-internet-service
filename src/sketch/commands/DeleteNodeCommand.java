package sketch.commands;

import sketch.GraphDrawer;

public class DeleteNodeCommand extends DrawCommand{

    public DeleteNodeCommand(GraphDrawer graphDrawer) {
        super(graphDrawer);
    }

    @Override
    public boolean execute(int x, int y) {
        return false;
    }
    

}
