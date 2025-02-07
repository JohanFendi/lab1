import java.security.PublicKey;
import java.util.ArrayList;

public class Container {

    private ArrayList<Double> container;
    private final int CONTAINER_MAXCAPACITY = 10;
    private double CURRENT_ANGLE = 0;
    private boolean IS_CONTAINER_DOWN = true;

    public Container() {
        this.container = new ArrayList<>(CONTAINER_MAXCAPACITY);
    }



    public boolean isFlakUp() {
        return this.IS_CONTAINER_DOWN;
    }

    public void setFlakUp() {
        this.IS_CONTAINER_DOWN = true;
    }

    public void setFlakDown() {
        this.CURRENT_ANGLE = 0;
        this.IS_CONTAINER_DOWN = false;
        // TODO biltransporten måste stå stilla för att sänka den
    }

    public void adjustRamp(double angle){
        this.CURRENT_ANGLE = angle;
    }





/*
    private boolean isLoadable;

    abstract protected void adjustRamp(double angle);

    abstract protected void LoadTruck(T x);

    abstract protected T unLoadTruck();

 */






}
