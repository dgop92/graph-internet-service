package app;


import interfaces.ControlInterface;

public class App {

    public static ControlInterface controlInterface;

    public static void main(String[] args) {

        controlInterface = new ControlInterface();
        controlInterface.setVisible(true);
        

    }
}
