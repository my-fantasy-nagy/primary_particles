package constants;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    //GLOBAL PARAMETERS
    public static final int FRAME_RATE = 60;
    public static final int WIDE = 800;
    public static final int HIGH = 800;

    // BOID PARAMETERS
    public static final int NUM_BOIDS = 250;
    public static final float BOID_SIZE = 5;
    public static final float BOID_COLOR = 255;
    public static final float PERCEPTION_RADIUS_COHESION = 100.0F;
    public static final float PERCEPTION_RADIUS_ALIGN = 100.0F;
    public static final float PERCEPTION_RADIUS_SEPARATION = 100.0F;
    public static final float MAX_FORCE =  0.2F;
    public static final float MAX_SPEED = 4.0F;
    public static final int TAIL_LENGTH = 20;

    //FORCE FIELD PARAMETERS
    public static final float FORCEFIELD_MAG = 0.5F;
    public static final float MOUSE_MAG = 1.0F;
    public static final float NOISE_RATE = 0.1F;
    public static final float Z_RATE = 0.01F;
    public static final float SCALE = 50.0F;

}
