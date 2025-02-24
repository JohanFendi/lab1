package src.model;

import java.awt.*;

public class ContainerTruck extends Vehicle {

    private Container container;
    private double RAMP_ANGLE;
    private double ANGLE_MIN;
    private double ANGLE_MAX;

    public ContainerTruck (int nrDoors, Color color, double enginePower, String modelName,Container container, double maxAngle, double minAngle, double rampAngle) {
        super(nrDoors, color, enginePower, modelName);
        this.RAMP_ANGLE = rampAngle;
        this.ANGLE_MIN = minAngle;
        this.ANGLE_MAX = maxAngle;
        this.container = container;
    }

    //Movement methods

    @Override
    public boolean isMoveable() {
        return RAMP_ANGLE == 0;
    }

    public void setContainer(Container newContainer) {
        this.container = newContainer;
    }

    public Container getContainer() {
        return this.container;
    }

    //Container and ramp methods

    public boolean adjustRamp(double angle) {
        if (this.isRampAdjustable()) {
            RAMP_ANGLE = clamp(ANGLE_MIN, ANGLE_MAX, angle);
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
        return RAMP_ANGLE;
    }
}
