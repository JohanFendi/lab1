import java.awt.*;
import java.util.Stack;

public class FlatBedTruck extends Truck {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 75;
    private static final String MODEL_NAME = "FlatBedTruck";
    private final Stack<Car> theOGFLatBed;

    public FlatBedTruck(Color color){
        super(FlatBedTruck.NR_DOORS, color, FlatBedTruck.ENGINE_POWER, FlatBedTruck.MODEL_NAME);
        this.theOGFLatBed = new Stack<Car>();
    }




    public void openRamp() {
        adjustRamp(90);
    }
    public void closeRamp() {
        adjustRamp(0);
    }


    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

    @Override
    protected void adjustRamp(double angle) {
        if(getCurrentSpeed() == 0) {

        }
    }

    @Override
    protected <T> T LoadTruck() {
        return null;
    }

    @Override
    protected <T> T unLoadTruck() {
        return null;
    }
}
