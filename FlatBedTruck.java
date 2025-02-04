import java.awt.*;

public class FlatBedTruck extends Truck {

    private static final int NR_DOORS = 2;
    public static final int ENGINE_POWER = 75;
    public static final String MODEL_NAME = "FlatBedTruck";

    public FlatBedTruck(Color color){
        super(FlatBedTruck.NR_DOORS, color, FlatBedTruck.ENGINE_POWER, FlatBedTruck.MODEL_NAME);
    }


    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }


    @Override
    public double rampOpen() {
        return 0;
    }
    @Override
    public double rampClose() {
        return 0;
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
