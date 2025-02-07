import java.awt.*;
                    //TODO satte "Double" tillfälligt
public class Scania extends Vehicle {


    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 50;
    private static final String MODEL_NAME = "Scania";
    private static final double ANGLE_MAX = 70;
    private static final double ANGLE_MIN = 0;

    private double truckBedAngle = 0.0;


    Container container;

    public Scania(Color color, double containerVolume){
        super(Scania.NR_DOORS, color, Scania.ENGINE_POWER, Scania.MODEL_NAME);
        this.container = new Container<>(containerVolume);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

    protected void adjustRamp(double angle) {
    }

    protected void LoadTruck(Double x) {

    }

    protected Double unLoadTruck() {
        return null;
    }


    // TODO implementera att lastbilen inte ska kunna köra med flaket uppfällt

}
