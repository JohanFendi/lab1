package src;

import java.awt.*;

public class Saab95 extends Car {

    private static final int NR_DOORS = 2;
    private static final double turbo = 1.3;
    private static final int ENGINE_POWER = 125;
    private static final String MODEL_NAME = "Saab95";

    public boolean turboOn;

    public Saab95 (Color color){
        super(Saab95.NR_DOORS, color, Saab95.ENGINE_POWER, Saab95.MODEL_NAME);
        this.turboOn = false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = Saab95.turbo;
        return getEnginePower() * 0.01 * turbo;
    }
}
