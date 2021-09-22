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
        
        return false;
    }
    
}
