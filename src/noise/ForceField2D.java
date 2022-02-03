package noise;
import processing.core.PApplet;
import processing.core.PVector;

import static constants.Constants.*;
import static processing.core.PConstants.TAU;

public class ForceField2D {
    public static void field2D(PApplet pa, int cols, int rows, float zOff, PVector[] flowField){
        // Y OFFSET RESET
        float yOff = 0.0F;

        // LOOP OVER ROWS/COLS
        for (int y = 0; y < rows; y ++){

            float xOff = 0.0F;

            for(int x = 0; x < cols; x ++){

                // SET INDEX
                int index = x + y * cols;

                // ANGLE BASED ON X,Y & Z NOISE VALUES MAPPT TO UNIT CIRCLE
                float angle = pa.noise(xOff, yOff, zOff) * TAU;

                // CREATE VECTOR FROM ANGLE AND ADD TO FLOW FIELD
                PVector v = new PVector().fromAngle(angle);
                v.setMag(FORCEFIELD_MAG);
                flowField[index] = v;

                // INCREMENT X OFFSET
                xOff += NOISE_RATE;

                // DISPLAY FLOW FIELD IF 'a' is PRESSED
                pa.stroke(150, 100);
                pa.push();
                pa.translate(x * SCALE + SCALE/2, y * SCALE + SCALE/2);
                pa.rotate(v.heading());
                pa.strokeWeight(2);
                if(pa.keyPressed == true && pa.key == 'a'){
                    pa.line(0, 0, SCALE, 0);
                }
                pa.pop();
            }
            // INCREMENT Y OFFSET
            yOff += NOISE_RATE;
        }
    }
}
