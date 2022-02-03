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
        this.velocity = PVector.random2D();
        this.velocity.setMag(pa.random(0.5F, 1.5F));
//        this.velocity = new PVector(pa.random(0,2), pa.random(0,2));
        this.acceleration = new PVector();
    }

    public void update(){
        position.add(velocity);
        velocity.add(acceleration);
    }


    public void show(){
        pa.strokeWeight(16);
        pa.stroke(255);
        pa.point(position.x, position.y);
    }

}
