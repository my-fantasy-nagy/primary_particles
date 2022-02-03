import processing.core.PApplet;
import processing.core.PVector;
import static constants.Constants.*;

public class Boid {
        PVector position;
        PVector velocity;
        PVector acceleration;
        PApplet pa;


    public Boid(PApplet pa){
        this.pa = pa;
        this.position = new PVector(WIDE/2, HIGH/2);
        this.velocity = new PVector();
        this.acceleration = new PVector();
    }

    void show(){
        pa.strokeWeight(16);
        pa.stroke(255);
        pa.point(position.x, position.y);
    }

}
