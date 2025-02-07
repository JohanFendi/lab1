import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class FlatBedTruck extends Vehicle implements Truck {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 75;
    private static final String MODEL_NAME = "FlatBedTruck";
    private static final int FLATBED_MAXCAPACITY = 10;

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
        double radius = 1;
        double carX = car.getXPos();
        double carY = car.getYPos();
        double truckX = this.getXPos();
        double truckY = this.getYPos();

        double distance = Math.hypot(carX - truckX, carY - truckY);

        return distance <= radius;
    }




    protected void LoadTruck(Car car) {
        if(currentFlatBedAngle == maxFlatBedAngle && isWithinRadius(car) && flatbed.size() < FLATBED_MAXCAPACITY) {
            car.setXpos(this.getXPos());
            car.setYpos(this.getYPos());
            flatbed.push(car);
        }
    }


    protected Car unLoadTruck() {
        double truckX = this.getXPos();
        double truckY = this.getYPos();

        if(currentFlatBedAngle != maxFlatBedAngle) {
            throw new IllegalArgumentException("Cannot unload car while ramp is up");
        }
        Car unloadedCar = flatbed.pop();
        unloadedCar.setXpos(truckX + 1);
        unloadedCar.setYpos(truckY);

        return unloadedCar;
    }



    // TODO: implementera att alla bilars position som är lastade på transporten uppdateras med lastbilens position


}
