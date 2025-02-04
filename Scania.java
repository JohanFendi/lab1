import java.awt.*;
                    //TODO satte "Double" tillfälligt
public class Scania extends Truck<Double> {


    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 50;
    private static final String MODEL_NAME = "Scania";

    private double truckBedAngle = 0.0;
    private double maxAngle = 70;

    public Scania(Color color){
        super(Scania.NR_DOORS, color, Scania.ENGINE_POWER, Scania.MODEL_NAME);
    }





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
    protected void LoadTruck(Double x) {

    }

    @Override
    protected Double unLoadTruck() {
        return null;
    }



}
