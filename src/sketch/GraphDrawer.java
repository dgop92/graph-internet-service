package sketch;

import java.util.ArrayList;

import core.Component;
import core.PEdgeGraph;
import interfaces.MenuObserver;
import processing.core.PApplet;
import sketch.commands.UnDrawNodeCommand;
import sketch.commands.DrawCommand;
import sketch.commands.DrawEdgeCommand;
import sketch.commands.DrawNodeCommand;
import inevaup.dialogs.InfoDialog;

public class GraphDrawer implements MenuObserver, SketchScreenObserver{
    
    private PEdgeGraph graph;
    private DrawMode drawMode;
    private DrawCommand currentCommand;
    private PApplet sketch;
    private FeedBackObserver feedBackObserver;

    public GraphDrawer(PApplet sketch) {
        graph = new PEdgeGraph();
        drawMode = DrawMode.DRAW_NODE;
        currentCommand = new DrawNodeCommand(this, "");
        this.sketch = sketch;
    }

    public void draw() {
        ArrayList<Component> components = graph.getAllComponents();
        for (Component c : components) {
            c.update();
        }
    }

    @Override
    public void onClickScreen(int x, int y) {
        boolean isSuccessful = currentCommand.execute(x, y);
        if (!isSuccessful){
            InfoDialog infoDialog = new InfoDialog(
                null,
                "Error",
                currentCommand.getErrorMessage(), 
                InfoDialog.TypeInfoDialog.ERROR_DIALOG
            );
            infoDialog.setVisible(true);
        }else{
            feedBackObserver.onGraphChange();
        }
    }

    @Override
    public void onChangeDrawMode(DrawMode drawMode, String commandInput) {
        switch (drawMode) {
            case DRAW_NODE:
                currentCommand = new DrawNodeCommand(this, commandInput);
                break;
            case DRAW_EDGE:
                currentCommand = new DrawEdgeCommand(this, commandInput);  
                break;
            case UNDRAW_NODE:
                currentCommand = new UnDrawNodeCommand(this);   
                break;
        }
        this.drawMode = drawMode; 
        
    }

    public void setFeedBackObserver(FeedBackObserver feedBackObserver) {
        this.feedBackObserver = feedBackObserver;
    }

    public void sendFeebackMessage(String message) {
        if (feedBackObserver != null){
            feedBackObserver.onNewMessage(message);
        }
    }

    public DrawMode getDrawMode() {
        return drawMode;
    }

    public PEdgeGraph getGraph() {
        return graph;
    }

    public PApplet getSketch() {
        return sketch;
    }
}
