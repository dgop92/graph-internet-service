package sketch;

public interface FeedBackObserver {
    
    public abstract void onNewMessage(String message);
    
    public abstract void onGraphChange();

}
