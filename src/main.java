import processing.core.PApplet;

import java.util.ArrayList;

import static constants.Constants.*;

public class main extends PApplet {

    public static void main(String... args){
        PApplet.main("main");
    }

    ArrayList<Boid> boids = new ArrayList<>();

    public void settings(){
        size(WIDE, HIGH);
    }

    public void setup(){
        frameRate(FRAME_RATE);
        boids.add(new Boid(this));
    }

    public void draw(){
       background(0);

       for(Boid boid : boids){
            boid.show();
        }

    }
}
