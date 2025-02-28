package src.model;

import src.ModelListener;

import java.util.ArrayList;

public interface Model {
    ArrayList<Position> getObjectPositions();

    void addListener(ModelListener listener);

    void notifyListeners();

    void gas(int amount);

    void brake(int amount);

    void setTurboOn() ;

    void setTurboOff() ;

    void liftBed();

    void lowerBed();

    void startVehicles();

    void stopVehicles();
}
