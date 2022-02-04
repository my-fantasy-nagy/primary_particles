import noise.ForceField2D;
import processing.core.PApplet;
import processing.core.PVector;

import java.time.zone.ZoneRules;
import java.util.ArrayList;
import static noise.ForceField2D.*;
import static constants.Constants.*;

public class main extends PApplet {

    public static void main(String... args){
        PApplet.main("main");
    }

    int cols, rows;
    float zOff = 0.0F;
    float mouseRad = 50.0F;
    ArrayList<Boid> boids = new ArrayList<>();
    PVector flowField[];


    public void settings(){
        size(WIDE, HIGH);
    }

    public void setup(){

        //GLOBAL SETTINGS
        pixelDensity(1);
        frameRate(FRAME_RATE);
        smooth();

        // CREATE FORCEFIELD ARRAY
        cols = floor(width/SCALE);
        rows = floor(height/SCALE);
        flowField = new PVector[cols*rows];

        // ADD BOIDS TO ARRAYLIST
        for(int i =0; i < NUM_BOIDS; i ++) {
            boids.add(new Boid(this));
        }
    }

    public void draw() {
        background(0);

        // UPDATE FORCEFIELD
        ForceField2D.field2D(this, rows, cols, zOff, flowField);
        zOff += Z_RATE;

        // CREATE MOUSE VECTOR
        PVector mouseVector = new PVector(mouseX, mouseY);
        mouseVector.setMag(MOUSE_MAG);


        // UPDATE BOIDS
        for (Boid boid : boids) {
            boid.edges();
            boid.flock(boids);
            boid.followForceField(flowField, cols);
            // IF MOUSE PRESSED
            if (mousePressed) {
                boid.followMouse(false);
                mouseRad -= MOUSE_RATE_GO;
                if(mouseRad <= MOUSE_RAD_MIN){
                    mouseRad = MOUSE_RAD_MIN;
                }
            }

            if (keyPressed == true && key == ' ') {
                boid.followMouse(true);
                mouseRad += MOUSE_RATE_GO;
                if(mouseRad >= MOUSE_RAD_MAX){
                    mouseRad = MOUSE_RAD_MAX;
                }
            }

            if(! mousePressed && !(keyPressed == true && key == ' ') ) {
                if (mouseRad < MOUSE_RAD) {
                    mouseRad += MOUSE_RATE_RETURN;
                }

                if (mouseRad >= MOUSE_RAD) {
                    mouseRad -= MOUSE_RATE_RETURN;
                }
            }

            boid.update();
            boid.show();

            drawCursor();
        }
    }

    void drawCursor(){
        noCursor();
        stroke(255);
        strokeWeight(1);
        noFill();

        ellipse(mouseX, mouseY, mouseRad, mouseRad);
    }
}
