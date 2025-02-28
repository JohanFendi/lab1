package src.model;

import java.awt.*;

abstract public class Vehicle implements Movable {
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
    private double currentSpeed;
    private Position position;
    private Vector vector;

    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;

        this.position = new Position(START_XPOS,START_YPOS);
        this.vector = new Vector(START_XVECTOR, START_YVECTOR);

        stopEngine();
    }

    //Methods for movement

    abstract public boolean isMoveable();

    @Override
    public void turnLeft(){
        if(this.isMoveable()) Movement.turnLeft(this.vector);
    }

    @Override
    public void turnRight(){
        if(this.isMoveable()) Movement.turnRight(this.vector);
    }

    @Override
    public void move() {
        if (this.isMoveable()) Movement.move(this.position, this.vector, this.getCurrentSpeed());
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException(Vehicle.GAS_BREAK_AMOUNT_ERROR);
        }

        if (isMoveable()){
            incrementSpeed(amount);
            this.currentSpeed = clamp(0.0, this.enginePower, currentSpeed);
        }
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException(Vehicle.GAS_BREAK_AMOUNT_ERROR);
        }

        decrementSpeed(amount);
        this.currentSpeed = clamp(0.0, this.enginePower, currentSpeed);
    }

    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    // Getters and setters

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



    public Position getPosition() {
        return this.position;
    }

    public Vector getVector() {
        return this.vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }
    public void setPos(Position position) {
        this.position = position;
    }







    // General methods

    protected static double clamp(double minValue, double maxValue, double value) {
        if (value < minValue) value = minValue;
        if (value > maxValue) value = maxValue;
        return value;
    }

}




