import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

import static constants.Constants.*;

public class Boid {
        PVector position;
        PVector velocity;
        PVector acceleration;
        PApplet pa;


    public Boid(PApplet pa){
        this.pa = pa;
        this.position = new PVector(pa.random(0, WIDE), pa.random(0, HIGH));
        this.velocity = PVector.random2D();
        this.velocity.setMag(pa.random(0.5F, 1.5F));
//        this.velocity = new PVector(pa.random(0,2), pa.random(0,2));
        this.acceleration = new PVector();
    }

    public void edges(){
        if (position.x >= pa.width) position.x = 0;
        if (position.x < 0) position.x = pa.width- 0.0001F;
        if (position.y >= pa.height) position.y = 0;
        if (position.y < 0) position.y = pa.height - 0.0001F;

    }

    public void flock(ArrayList<Boid> boids, PVector[] flowField, int cols){
        //
        PVector alignment = align(boids);
        PVector cohesion = cohesion(boids);
        PVector separation = separation(boids);
        PVector forceField = followForceField(flowField, cols);
        // reset acceleration to 0;
        this.acceleration.mult(0);

        //force accumulation
        acceleration.add(alignment);
        acceleration.add(cohesion);
        acceleration.add(separation);
        acceleration.add(forceField);
    }

    private PVector followForceField(PVector[] flowField, int cols){

        //CALCULATE INDEX
        int x = pa.floor(position.x / SCALE);
        int y = pa.floor(position.y / SCALE);
        int index = x + y * cols;

        //GET FORCE AT NEAREST FLOWFIELD VECTOR
        PVector force = flowField[index];

        //APPLY FORCE
        acceleration.add(force);
        return force;
    }

    public PVector separation(ArrayList<Boid> boids){
        PVector steering = new PVector();
        int total = 0;
        for(Boid boid : boids){
            float distance = pa.dist(this.position.x,
                                     this.position.y,
                                     boid.position.x,
                                     boid.position.y
            );

            if( boid != this && distance < PERCEPTION_RADIUS_SEPARATION){
                PVector diff =new PVector(0,0).sub(this.position, boid.position);
                diff.div(distance);
                steering.add(diff);
                total ++;
            }
        }
        if(total > 0) {
            steering.div(total);
            steering.setMag(MAX_SPEED);
            steering.sub(this.velocity);
            steering.limit(MAX_FORCE);
        }
        return steering;
    }

    public PVector cohesion(ArrayList<Boid> boids){
        PVector steering = new PVector();
        int total = 0;
        for(Boid boid : boids){
            float distance = pa.dist(this.position.x, this.position.y, boid.position.x, boid.position.y);
            if(boid != this && distance < PERCEPTION_RADIUS_COHESION){
                steering.add(boid.position);
                total ++;
            }
        }
        if(total > 0) {
            steering.div(total);
            steering.sub(this.position);
            steering.setMag(MAX_SPEED);
            steering.sub(this.velocity);
            steering.limit(MAX_FORCE);
        }
        return steering;
    }

    public PVector align(ArrayList<Boid> boids){
        PVector steering = new PVector();
        int total = 0;
        for(Boid boid : boids){
            float distance = pa.dist(this.position.x, this.position.y, boid.position.x, boid.position.y);
            if(boid != this && distance < PERCEPTION_RADIUS_ALIGN){
                steering.add(boid.velocity);
                total ++;
            }
        }
        if(total > 0) {
            steering.div(total);
            steering.setMag(MAX_SPEED);
            steering.sub(this.velocity);
            steering.limit(MAX_FORCE);
        }
        return steering;
    }

    public void update(){
        position.add(velocity);
        velocity.add(acceleration);
        velocity.limit(MAX_SPEED);
    }

    public void show(){
        pa.strokeWeight(BOID_SIZE);
        pa.stroke(BOID_COLOR);
        pa.point(position.x, position.y);
    }

}
