package src.model;

import src.ModelListener;

import java.util.ArrayList;

public interface Model {

    void gas(int amount);

    void brake(int amount);

    void setTurboOn() ;

    void setTurboOff() ;

    void liftBed();

    void lowerBed();

    void startVehicles();

    void stopVehicles();

    String addCar();

    boolean removeCar();
}
