package src.model;

import java.awt.*;

public abstract class ContainerTruck extends Vehicle {

    private Container container;
    private double ramp_angle;
    private final double ANGLE_MIN;
    private final double ANGLE_MAX;

    public ContainerTruck (int nrDoors, Color color, double enginePower, String modelName,
                           Container container, double maxAngle, double minAngle, double rampAngle) {

        super(nrDoors, color, enginePower, modelName);
        this.ramp_angle = rampAngle;
        this.ANGLE_MIN = minAngle;
        this.ANGLE_MAX = maxAngle;
        this.container = container;
    }

    //Movement methods

    @Override
    public boolean isMoveable() {
        return ramp_angle == 0;
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
            ramp_angle = clamp(ANGLE_MIN, ANGLE_MAX, angle);
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
        return ramp_angle;
    }
}
