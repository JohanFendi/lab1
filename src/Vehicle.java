package src;

import java.awt.*;

abstract public class Vehicle {

    private static final String GAS_BREAK_AMOUNT_ERROR = "Method input must be between 0 and 1.";
    private static final int START_XVECTOR = 1;
    private static final int START_YVECTOR = 0;
    private static final double START_YPOS = 0;
    private static final double START_XPOS = 0;

    private final int nrDoors;
    private final double enginePower;
    private Color color;
    private final String modelName;

    //Movement variables
    private MovementObj movementObj;
    private double currentSpeed;

    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.movementObj = new MovementObj(new Vector(START_XVECTOR, START_YVECTOR),
                                            new Position(START_XPOS, START_YPOS));
        stopEngine();
    }

    protected static double clamp(double minValue, double maxValue, double value) {
        if (value < minValue) value = minValue;
        if (value > maxValue) value = maxValue;
        return value;
    }

    public String getModelName() {
        return this.modelName;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    protected void setCurrentSpeed(double speed) {
        this.currentSpeed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    abstract protected double speedFactor();

    protected void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    protected void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    public MovementObj getMovementObj(){
        return this.movementObj;
    }

    public void setMovementObj(MovementObj movementObj){
        this.movementObj = movementObj;
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException(Vehicle.GAS_BREAK_AMOUNT_ERROR);
        }
        incrementSpeed(amount);
        this.currentSpeed = clamp(0.0, this.enginePower, currentSpeed);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException(Vehicle.GAS_BREAK_AMOUNT_ERROR);
        }
        decrementSpeed(amount);
        this.currentSpeed = clamp(0.0, this.enginePower, currentSpeed);
    }


}




