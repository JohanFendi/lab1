package src;

import java.awt.*;

public class Scania extends Vehicle implements Movable{

    private static final String ILLEGAL_GAS_ERROR = "Gas-amount must be between 0 and 1 and ramp cannot be lowered";
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

    @Override
    public void move() {
        if (isMovable()) {
            this.getMovementObj().move(this.getCurrentSpeed());
        }
    }

    @Override
    public void turnLeft(){
        if (isMovable()) {
            this.getMovementObj().turnLeft();
        }
    }

    @Override
    public void turnRight(){
        if (isMovable()) {
            this.getMovementObj().turnRight();
        }
    }

    @Override
    public void gas(double amount) {
        if (amount < 0 || amount > 1 || !isMovable()) {
            throw new IllegalArgumentException(ILLEGAL_GAS_ERROR);
        }
        incrementSpeed(amount);
        setCurrentSpeed(clamp(0.0, this.getEnginePower(), this.getCurrentSpeed()));
    }

    public double loadContainer(double volume){
        double overflow = this.container.load(volume);
        return overflow;
    }

    public double unloadContainer(double volume){

        return this.container.unload(volume);
    }

    public double getRampAngle() {
        return rampAngle;
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    private boolean isRampAdjustable() {
        return this.getCurrentSpeed() == 0;
    }

    private boolean isMovable() {
        return rampAngle == 0;
    }

    public boolean adjustRamp(double angle) {
        if (this.isRampAdjustable()) {
            rampAngle = clamp(ANGLE_MIN, ANGLE_MAX, angle);
            return true;
        }
        return false;
    }
}
