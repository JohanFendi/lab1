
import java.awt.*;

abstract public class Truck<T> extends Vehicle {

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    private boolean isLoadable;

    abstract protected void adjustRamp(double angle);
    abstract protected void LoadTruck(T x);
    abstract protected T unLoadTruck();

}