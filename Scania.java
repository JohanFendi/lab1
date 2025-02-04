import java.awt.*;

public class Scania extends Truck {


    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 50;
    private static final String MODEL_NAME = "Scania";

    public Scania(Color color){
        super(Scania.NR_DOORS, color, Scania.ENGINE_POWER, Scania.MODEL_NAME);
    }

    private double truckBedAngle = 0.0;
    private double maxAngle = 70;



    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

    @Override
    protected void adjustRamp(double angle) {
        if()
        truckBedAngle = angle;
        clamp(0.0,maxAngle,truckBedAngle);
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
