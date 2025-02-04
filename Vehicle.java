
import java.awt.*;

abstract public class Vehicle implements Movable {

    private static final String GAS_BREAK_AMOUNT_ERROR = "Method input must be between 0 and 1.";
    //test

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


    public Vehicle (int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        stopEngine();
    }

    protected double clamp(double minValue, double maxValue, double value){
        if (value < minValue) value = minValue;
        if (value > maxValue) value = maxValue;
        return value;
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

    public void gas(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException(Vehicle.GAS_BREAK_AMOUNT_ERROR);
        }

        incrementSpeed(amount);
        clamp(0.0,this.enginePower,currentSpeed);
    }

    public void brake(double amount){
        if (amount < 0 || amount > 1){
            throw new IllegalArgumentException(Vehicle.GAS_BREAK_AMOUNT_ERROR);
        }
        decrementSpeed(amount);
        clamp(0.0,this.enginePower,currentSpeed);
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


    //TODO kanske protected?
    public void setXpos(double x) { this.xPos = x; }

    public void setYpos(double y) { this.yPos = y; }
}




