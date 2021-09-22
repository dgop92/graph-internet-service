package interfaces;

public interface MenuObserver {

    public void onNodeButtonClick(String nodeName);

    public void onEdgeButtonClick(double weight);

    public void onDeleteButtonClick();
    
}
