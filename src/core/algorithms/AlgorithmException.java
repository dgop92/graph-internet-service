package core.algorithms;

public class AlgorithmException extends Exception{
    
    private static final long serialVersionUID = 1L;
    
    private String reason;

    public AlgorithmException(String reason) {
        this.reason = reason;
    }

    public String toString() {
        return reason;
    }

    public String getReason() {
        return reason;
    }

}
