package sketch;

import processing.core.PApplet;
import processing.core.PImage;

public class GraphSketch extends PApplet {

    public final int SKETCH_WIDTH = 800;
    public final int SKETCH_HEIGHT = 500;

    private GraphDrawer graphDrawer;

    public GraphSketch() {
        super();

        graphDrawer = new GraphDrawer(this);
    }

    PImage backgrround, map1, map2, map3;
    public boolean availableToPaint = false;
    public int scenario = 0;
    boolean selectCity = true, activeFollowing = true;
    String city_name_1 = "Los Santos", city_name_2 = "San Fierro",
            city_name_3 = "Las Venturas";
    boolean introIsShown = false;

    @Override
    public void settings() {
        size(SKETCH_WIDTH, SKETCH_HEIGHT);
        backgrround = loadImage("src/images/map_generalview.jpg");
        map1 = loadImage("src/images/los_santos.jpg");
        map2 = loadImage("src/images/san_fierro.jpg");
        map3 = loadImage("src/images/las_venturas.jpg");

    }

    @Override
    public void draw() {
   

        switch (scenario) {
            // selection of the map 
            case 0:
                if(!introIsShown){
                
                    
                   introIsShown = true; 
                }
                
                // image background
                availableToPaint = false;
                activeFollowing = true;

                image(backgrround, 0, 0, width, height);

                animationOfSelection();

                showTheCityName();

                break;

            case 1:
                image(map1, 0, 0, width, height);
                availableToPaint = true;


                break;
            case 2:
                image(map2, 0, 0, width, height);
                availableToPaint = true;
                break;

            case 3:
                image(map3, 0, 0, width, height);
                availableToPaint = true;
                break;

        }
        
        if (availableToPaint) {
            graphDrawer.draw();
        }

    }

    @Override
    public void mouseClicked() {

        if (availableToPaint) {
            graphDrawer.onClickScreen(mouseX, mouseY);
        }
        
        if (scenario == 0) {
            // Select a city
            if ((mouseX >= width / 2 && mouseX <= width) && (mouseY >= height / 2 + 70 && mouseY <= height)) {

                scenario = 1;
                selectCity = false;
                activeFollowing = false;
            }

            // second city; San Fierro
            if ((mouseX >= 0 && mouseX <= width / 2 - 170) && (mouseY >= height / 2 - 136 && mouseY <= height / 2 + 100)) {

                scenario = 2;
                selectCity = false;
                activeFollowing = false;
            }

            // third city; Las Venturas
            if ((mouseX >= width / 2 + 110 && mouseX <= width) && (mouseY >= 0 && mouseY <= height / 2 - 40)) {

                scenario = 3;
                selectCity = false;
                activeFollowing = false;
            }
        }

    }

    boolean high, high2, high3;

    @Override
    public void mouseMoved() {

        if (activeFollowing) {

            if (selectCity) {

                // show the name of the city
                
                // first city; los santos 
                if ((mouseX >= width / 2 && mouseX <= width) && (mouseY >= height / 2 + 70 && mouseY <= height)) {

                    high = true;
                    selectCity = false;
                }

                // second city; San Fierro
                if ((mouseX >= 0 && mouseX <= width / 2 - 170) && (mouseY >= height / 2 - 136 && mouseY <= height / 2 + 100)) {

                    high2 = true;
                    selectCity = false;
                }

                // third city; Las Venturas
                if ((mouseX >= width / 2 + 110 && mouseX <= width) && (mouseY >= 0 && mouseY <= height / 2 - 40)) {

                    high3 = true;
                    selectCity = false;
                }

            }

            // Not show the name of the city out of its space
            if ((mouseX < width / 2 && mouseY < height / 2 + 70)
                    || (mouseX < width / 2 && mouseY > height / 2 + 70)
                    || (mouseX > width / 2 && mouseY < height / 2 + 70)) {

                high = false;
                selectCity = true;
            }

            // second city; San Fierro
            if ((mouseX > width / 2 - 170 && mouseY > height / 2 - 136)
                    || (mouseX > width / 2 - 170 && mouseY < height / 2 + 100)
                    || (mouseY < height / 2 - 136 || mouseY > height / 2 + 100)) {

                high2 = false;
                selectCity = true;
            }

            // third city; Las Venturas
            if ((mouseX < width / 2 + 110 && mouseY > height / 2 - 40)
                    || (mouseX < width / 2 + 110 && mouseY < height / 2 - 40)
                    || (mouseX > width / 2 + 110 && mouseY > height / 2 - 40)) {

                high3 = false;
                selectCity = true;
            }

        }

    }

    public GraphDrawer getGraphDrawer() {
        return graphDrawer;
    }

    public void run() {
        String[] processingArgs = {this.getClass().getName()};
        PApplet.runSketch(processingArgs, this);

    }

    public void animationOfSelection() {

        stroke(200, 0, 0);
        strokeWeight(5);

        float y1 = height / 2 + 70, x1 = width / 2;
        float y2 = height / 2 - 136, x2 = width / 2 - 170, y3 = height / 2 + 100;
        float y4 = height / 2 - 40, x3 = width / 2 + 110;

        // Show the city options
        // los santos city
        line(x1, y1, x1, height);
        line(x1, y1, width, y1);

        // san fierro city
        line(x2, y2, x2, y3);
        line(0, y2, x2, y2);
        line(0, y3, x2, y3);

        // las venturas city
        line(x3, 0, x3, y4);
        line(x3, y4, width, y4);

    }

    public void showTheCityName() {

        // Los santos label
        if (high) {
            noStroke();
            fill(250);
            rect(width / 2 + width / 4 - textWidth(city_name_1) / 2,
                    height - 113, textWidth(city_name_1), 24);
            fill(255, 0, 0);
            textSize(30);
            text(city_name_1,
                    width / 2 + width / 4 - textWidth(city_name_1) / 2,
                    height - 90);

        }

        // San Fierro label
        if (high2) {
            noStroke();
            fill(250);
            rect(height / 2 - 136 - textWidth(city_name_2) / 2,
                    height / 2 - 40, textWidth(city_name_2), 24);
            fill(255, 0, 0);
            textSize(30);
            text(city_name_2,
                    height / 2 - 136 - textWidth(city_name_2) / 2,
                    height / 2 - 18);

        }

        // Las venturas label
        if (high3) {
            noStroke();
            fill(250);
            rect(width / 2 + 250 - textWidth(city_name_3) / 2,
                    height / 4 - 23, textWidth(city_name_3), 24);
            fill(255, 0, 0);
            textSize(30);
            text(city_name_3, width / 2 + 250 - textWidth(city_name_3) / 2,
                    height / 4);

        }

    }

    public boolean isAvailableToPaint() {

        return this.availableToPaint;
    }
    
    public void setAvailable(boolean available){
        this.availableToPaint = available;
    }
    
    public int getActualScene(){
        return scenario;
    }
    
    public void setScene(int scenario){
        this.scenario = scenario;
    }
    
    
}
