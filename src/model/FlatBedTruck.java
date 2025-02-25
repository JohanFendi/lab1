package src.model;

import java.awt.*;

public class FlatBedTruck extends Vehicle implements Movable {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 75;
    private static final String MODEL_NAME = "FlatBedTruck";
    private static final int FLATBED_MAXCAPACITY = 10;
    private static final double MAX_LOADING_RADIUS = 1;
    private static final double UNLOAD_OFFSET = 1;


    private boolean isFlatBedUp = true;
    private final Flatbed<Car> flatBed;

    public FlatBedTruck(Color color) {
        super(FlatBedTruck.NR_DOORS, color, FlatBedTruck.ENGINE_POWER, FlatBedTruck.MODEL_NAME);
        this.flatBed = new Flatbed<Car>(FLATBED_MAXCAPACITY);
    }

    //Methods for loading, unloading and adjusting flatbed.

    public void loadFlatBed(Car car) {
        if (car.isLoaded()) {
            return;
        }
        Position truckPos = this.getMovementObj().getPosition();
        if (isFlatBedLoadable() && isWithinRadius(car) && !flatBed.isFull()) {
            car.setIsLoaded(true);
            this.flatBed.loadObject(car);
            car.setMovementObj(this.getMovementObj());
        }
    }

    public Car unLoadCar() {
        if (isFlatBedUp) {
            return null;
        }
        Car unloadedCar = flatBed.unLoadObject();
        if (unloadedCar == null) {
            return null;
        }
        unloadedCar.setIsLoaded(false);
        Position truckPos = this.getMovementObj().getPosition();
        Vector carUnloadedVector = this.getMovementObj().getVector();
        Position carUnloadedPosition = new Position(truckPos.getX() + UNLOAD_OFFSET,
                truckPos.getY() + UNLOAD_OFFSET);
        unloadedCar.setMovementObj(new MovementObj(carUnloadedVector, carUnloadedPosition));
        return unloadedCar;
    }

    public void lowerFlatBed() {
        if (isFlatBedAdjustable() && this.isFlatBedUp) {
            this.isFlatBedUp = false;
        }
    }

    public void raiseFlatBed() {
        if (isFlatBedAdjustable() && !this.isFlatBedUp) {
            this.isFlatBedUp = true;
        }
    }

    private boolean isFlatBedLoadable() {
        return !this.isFlatBedUp;
    }

    private boolean isWithinRadius(Car car) {
        Position carPosition = car.getMovementObj().getPosition();
        Position truckPosition = this.getMovementObj().getPosition();

        double distance = Math.hypot(carPosition.getX() - truckPosition.getX(), carPosition.getY() - truckPosition.getY());
        return distance <= FlatBedTruck.MAX_LOADING_RADIUS;
    }

    private boolean isFlatBedAdjustable() {
        return this.getCurrentSpeed() == 0;
    }

    //Movement methods

    @Override
    public boolean isMoveable() {
        return this.isFlatBedUp;
    }

    @Override
    public void move() {
        if (this.isMoveable()) {
            this.getMovementObj().move(this.getCurrentSpeed());
            this.updateLoadedCarPositions();
        }
    }

    private void updateLoadedCarPositions() {
        Position truckPos = this.getMovementObj().getPosition();
        Vector truckVector = this.getMovementObj().getVector();

        for (Car car : this.flatBed.getLoadedCars()) {
            car.setMovementObj(new MovementObj(truckVector, truckPos));
        }
    }
}
