package sketch.commands;

import sketch.GraphDrawer;

public abstract class DrawCommand {

    protected String errorMessage;
    protected GraphDrawer graphDrawer;

    public DrawCommand(GraphDrawer graphDrawer) {
        this.graphDrawer = graphDrawer;
    }

    public abstract boolean execute(int x, int y);

    public String getErrorMessage() {
        return errorMessage;
    }
}
