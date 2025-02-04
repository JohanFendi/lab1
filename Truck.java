
import java.awt.*;

abstract public class Truck extends Vehicle {

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    private boolean isLoadable;

    abstract protected double rampOpen();
    abstract protected double rampClose();
    abstract protected <T> T LoadTruck();
    abstract protected <T> T unLoadTruck();

}