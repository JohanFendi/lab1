
import java.awt.*;

abstract public class Car implements Movable {

    private static final String GAS_BREAK_AMOUNT_ERROR = "Method input must be between 0 and 1.";

    private int nrDoors;
    private double enginePower;
    private Color color;
    private String modelName;

    //Variables for movement and position.
    private double currentSpeed;
    private int xVector = 1;
    private int yVector = 0;
    private double xPos = 0;
    private double yPos = 0;


    public Car (int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        stopEngine();
    }

    @Override
    public void move(){
        this.xPos += this.currentSpeed * this.xVector;
        this.yPos += this.currentSpeed * this.yVector;
    }

    @Override
    public void turnRight(){
        int temp = this.yVector;
        this.yVector = -this.xVector;
        this.xVector = temp;
    }

    @Override
    public void turnLeft(){
        int temp = this.yVector;
        this.yVector = this.xVector;
        this.xVector = -temp;
    }

    public String getModelName(){
        return this.modelName;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    abstract protected double speedFactor();

    protected void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    protected void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    private void clampSpeed(){
        if (currentSpeed < 0) currentSpeed = 0;
        if (currentSpeed > this.enginePower) currentSpeed = this.enginePower;
    }

    public void gas(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException(Car.GAS_BREAK_AMOUNT_ERROR);
        }

        incrementSpeed(amount);
        clampSpeed();
    }

    public void brake(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException(Car.GAS_BREAK_AMOUNT_ERROR);
        }
        decrementSpeed(amount);
        clampSpeed();
    }

    public int getxVector(){
        return this.xVector;
    }

    public int getyVector(){
        return this.yVector;
    }

    public double getXPos() {
        return this.xPos;
    }

    public double getYPos(){
        return this.yPos;
    }
}




