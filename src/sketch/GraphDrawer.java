package sketch;

import java.util.ArrayList;

import core.Component;
import core.PEdgeGraph;
import interfaces.MenuObserver;
import processing.core.PApplet;
import sketch.commands.DeleteNodeCommand;
import sketch.commands.DrawCommand;
import sketch.commands.DrawEdgeCommand;
import sketch.commands.DrawNodeCommand;

public class GraphDrawer implements MenuObserver, SketchScreenObserver{
    
    private PEdgeGraph graph;
    private DrawMode drawMode;
    private DrawCommand currentCommand;

    public GraphDrawer(PApplet sketch) {
        drawMode = DrawMode.DRAW_NODE;
        currentCommand = new DrawNodeCommand(this, "Torre Principal");
    }

    public void draw() {
        ArrayList<Component> components = graph.getAllComponents();
        for (Component c : components) {
            c.update();
        }
    }

    @Override
    public void onClickScreen(int x, int y) {
        currentCommand.execute(x, y);
    }

    @Override
    public void onNodeButtonClick(String nodeName) {
        drawMode = DrawMode.DRAW_NODE;
        currentCommand = new DrawNodeCommand(this, nodeName);
    }

    @Override
    public void onEdgeButtonClick(double weight) {
        drawMode = DrawMode.DRAW_EDGE;
        currentCommand = new DrawEdgeCommand(this, weight);
    }

    @Override
    public void onDeleteButtonClick() {
        drawMode = DrawMode.DELETE_NODE;
        currentCommand = new DeleteNodeCommand(this);
    }

    public DrawMode getDrawMode() {
        return drawMode;
    }
}
