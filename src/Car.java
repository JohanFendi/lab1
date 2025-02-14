package src;

import java.awt.*;

abstract public class Car extends Vehicle {
    private boolean isLoaded = false;

    public Car (int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public boolean isLoaded(){
        return this.isLoaded;
    }

    public void setIsLoaded(boolean state){
        this.isLoaded = state;
    }

    @Override
    protected boolean isMoveable(){
        return !this.isLoaded;
    }
}