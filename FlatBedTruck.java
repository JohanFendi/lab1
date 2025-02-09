import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class FlatBedTruck extends Vehicle {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 75;
    private static final String MODEL_NAME = "FlatBedTruck";
    private static final int FLATBED_MAXCAPACITY = 10;
    private static final double MAX_LOADING_RADIUS = 1;


    private boolean isFlatBedUp = true;
    private Flatbed<Car> flatBed;


    public FlatBedTruck(Color color) {
        super(FlatBedTruck.NR_DOORS, color, FlatBedTruck.ENGINE_POWER, FlatBedTruck.MODEL_NAME);
        this.flatBed = new Flatbed<Car>(FLATBED_MAXCAPACITY);
    }








    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    //Kodduplicering, finns samma i Scania
    private boolean isFlatBedAdjustable() {
        return this.getCurrentSpeed() == 0;
    }

    private void lowerFlatBed() {
        if (isFlatBedAdjustable() && this.isFlatBedUp) {
            this.isFlatBedUp = false;
        }
    }

    private void raiseFlatBed() {
        if (isFlatBedAdjustable() && !this.isFlatBedUp) {
            this.isFlatBedUp = true;
        }
    }

    private boolean isFlatBedLoadable(Car car) {
        if (!this.isFlatBedUp && isWithinRadius(car)) {
            return true;
        }
        return false;
    }

    /*
    private boolean isFlatBedUnloadable() {
        return !this.isFlatBedUp;
    }
     */

    public void loadFlatBed(Car car) {
        Position truckPos = this.getPosition();
        if (isFlatBedLoadable(car)) {
            this.flatBed.loadObject(car);
        }
        //uppdaterar bilens position med lastbilens position
        car.setPosition(new Position(truckPos.getX(), truckPos.getY()));
    }


    public Car unLoadFlatBed() {
        if (isFlatBedUp) {
            return null;
        }
        Car unloadedCar = this.flatBed.unLoadObject();

        if (unloadedCar == null) {
            return null;
        }
        Position truckPos = this.getPosition();
        Position carPosition = new Position(truckPos.getX() + 1, truckPos.getY());
        unloadedCar.setPosition(carPosition);

        return unloadedCar;
    }


    private void updatingLoadedCarPos() {
        Position truckPos = this.getPosition();

        for (Car car : this.flatBed.getLoadedCars()) {
            car.setPosition(new Position(truckPos.getX(), truckPos.getY()));
        }
    }


    public void move() {
        if(isFlatBedUp) {
            Position position = this.getPosition();
            Vector vector = this.getVector();
            position.setX(position.getX() + this.getCurrentSpeed() * vector.getX());
            position.setY(position.getY() + this.getCurrentSpeed() * vector.getY());

            this.setPosition(position);
            this.updatingLoadedCarPos();
        }

    }






    private boolean isWithinRadius(Car car) {
        Position carPosition = car.getPosition();
        Position truckPosition = this.getPosition();

        double distance = Math.hypot(carPosition.getX() - truckPosition.getX(), carPosition.getY() - truckPosition.getY());
        return distance <= FlatBedTruck.MAX_LOADING_RADIUS;
    }
}
