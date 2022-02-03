import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static constants.Constants.*;

public class main extends PApplet {

    public static void main(String... args){
        PApplet.main("main");
    }

    ArrayList<Boid> boids = new ArrayList<>();
    int cols, rows;
    float zOff = 0.0F;
    PVector flowField[];


    public void settings(){
        size(WIDE, HIGH);
    }

    public void setup(){

        //GLOBAL SETTINGS
        pixelDensity(1);
        frameRate(FRAME_RATE);

        // CREATE FORCEFIELD ARRAY
        cols = floor(width/SCALE);
        rows = floor(height/SCALE);
        flowField = new PVector[cols*rows];

        // ADD BOIDS TO ARRAYLIST
        for(int i =0; i < NUM_BOIDS; i ++) {
            boids.add(new Boid(this));
        }
    }

    public void draw(){
       background(0);

       field2D();

       for(Boid boid : boids){
           boid.edges();
           boid.flock(boids);
           boid.update();
           boid.show();
        }

    }

    public void field2D(){
        // Y OFFSET RESET
        float yOff = 0.0F;

        // LOOP OVER ROWS/COLS
        for (int y = 0; y < rows; y ++){

            float xOff = 0.0F;

            for(int x = 0; x < cols; x ++){

                // SET INDEX
                int index = x + y * cols;

                // ANGLE BASED ON X,Y & Z NOISE VALUES MAPPT TO UNIT CIRCLE
                float angle = noise(xOff, yOff, zOff) * TAU;

                // CREATE VECTOR FROM ANGLE AND ADD TO FLOW FIELD
                PVector v = new PVector().fromAngle(angle);
                v.setMag(FORCEFIELD_MAG);
                flowField[index] = v;

                // INCREMENT X OFFSET
                xOff += NOISE_RATE;

                // DISPLAY FLOW FIELD IF 'a' is PRESSED
                stroke(150, 100);
                push();
                translate(x * SCALE, y * SCALE);
                rotate(v.heading());
                strokeWeight(2);
                if(keyPressed == true && key == 'a'){
                    line(0, 0, SCALE, 0);
                }
                pop();
            }
            // INCREMENT Y OFFSET
            yOff += NOISE_RATE;
        }
        zOff += Z_RATE;
    }
}
