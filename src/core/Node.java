package core;

import java.awt.Color;

public abstract class Node implements Component {
    
    public String name;

    public Node(String name) {
        this.name = name;
    }

    public abstract void highlightNode(Color color);

    public abstract void deHighlightNode();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)){
            return false;
        }
        Node node = (Node) obj;
        return this.name.equals(node.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
