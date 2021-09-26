package interfaces;

import sketch.DrawMode;

public interface MenuObserver {

    public void onChangeDrawMode(DrawMode drawMode, String commandInput);
    
}
