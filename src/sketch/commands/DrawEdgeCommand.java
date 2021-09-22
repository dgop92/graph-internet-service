package sketch.commands;

import sketch.GraphDrawer;

public class DrawEdgeCommand extends DrawCommand{

    private double weight;

    public DrawEdgeCommand(GraphDrawer graphDrawer, double weight) {
        super(graphDrawer);
        this.weight = weight;
    }

    @Override
    public boolean execute(int x, int y) {
        
        return false;
    }
    
}
