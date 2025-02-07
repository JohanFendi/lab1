import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class FlatBedTruck extends Vehicle{

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 75;
    private static final String MODEL_NAME = "FlatBedTruck";
    private static final int FLATBED_MAXCAPACITY = 10;
    private static final double MAX_LOADING_RADIUS = 1;

    private final Deque<Car> flatbed;
    private double currentFlatBedAngle = 0.0;
    private double maxFlatBedAngle = 70;
    // TODO kanske private double minFlatBedAngle = 0;?


    public FlatBedTruck(Color color){
        super(FlatBedTruck.NR_DOORS, color, FlatBedTruck.ENGINE_POWER, FlatBedTruck.MODEL_NAME);
        this.flatbed = new ArrayDeque<>(FLATBED_MAXCAPACITY);

    }



    public void openRamp() {
        if (getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Can't lower flatbed while truck is moving");
        }
        adjustRamp(maxFlatBedAngle);
    }

    public void closeRamp() {
        adjustRamp(0);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

    protected void adjustRamp(double angle) {
        this.currentFlatBedAngle = clamp(0, maxFlatBedAngle, angle);
    }


    private boolean isWithinRadius (Car car) {
        Vector carVector = car.getVector();
        Position carPosition = car.getPosition();
        Vector truckVector = this.getVector();
        Position truckPosition = this.getPosition();
        double distance = Math.hypot(carPosition.getX()- truckPosition.getX(),
                                    carPosition.getY() - truckPosition.getY());
        return distance <= FlatBedTruck.MAX_LOADING_RADIUS;
    }
}
