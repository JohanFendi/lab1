import java.awt.*;

public abstract class Truck<T> extends Vehicle {

    private TruckContainer<T> container;

    public Truck(int nrDoors, Color color, double enginePower, String modelName, TruckContainer<T> container) {
        super(nrDoors, color, enginePower, modelName);
        this.container = container;
    }

    public TruckContainer<T> getContainer() {
        return getContainer();
    }


}
