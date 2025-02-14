package src;

import java.awt.*;

public class Scania extends Vehicle{

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 50;
    private static final String MODEL_NAME = "Scania";
    private static final double ANGLE_MAX = 70;
    private static final double ANGLE_MIN = 0;

    private double rampAngle = 0.0;
    private final src.Container container;


    public Scania(Color color, double containerVolume) {
        super(Scania.NR_DOORS, color, Scania.ENGINE_POWER, Scania.MODEL_NAME);
        this.container = new Container(containerVolume);
    }

    //Movement methods

    @Override
    protected boolean isMoveable() {
        return rampAngle == 0;
    }

    //Container and ramp methods

    public boolean adjustRamp(double angle) {
        if (this.isRampAdjustable()) {
            rampAngle = clamp(ANGLE_MIN, ANGLE_MAX, angle);
            return true;
        }
        return false;
    }

    public double loadContainer(double volume){
        return this.container.load(volume);
    }

    public double unloadContainer(double volume){
        return this.container.unload(volume);
    }

    private boolean isRampAdjustable() {
        return this.getCurrentSpeed() == 0;
    }

    public double getRampAngle() {
        return rampAngle;
    }
}
