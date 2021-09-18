package core;

import java.awt.Color;

public abstract class Node {
    
    private static int indexCounter = 0;
    private int index;
    public String name;

    public Node(String name) {
        this.index = indexCounter++;
        this.name = name;
    }

    public abstract void highlightNode(Color color);

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
