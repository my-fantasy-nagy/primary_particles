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
        for(int i =0; i < 100; i ++) {
            boids.add(new Boid(this));
        }
    }

    public void draw(){
       background(0);

       for(Boid boid : boids){
           boid.update();
            boid.show();
        }

    }
}
