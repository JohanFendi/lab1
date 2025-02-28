package src;

public interface Controller {

    void brake(int amount);

    void gas(int amount);

    void setTurboOn();

    void setTurboOff();

    void liftBed();

    void lowerBed();

    void startVehicles();

    void stopVehicles();

    String addCar();

    boolean removeCar();
}
