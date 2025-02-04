
import java.awt.*;

abstract public class Truck extends Vehicle {

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    abstract protected double rampOpen();
    abstract protected double rampClose();
    abstract protected double loadTruck();
    abstract protected T unLoadTruck<T>;

}