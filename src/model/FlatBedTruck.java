package src.model;

import java.awt.*;

public class FlatBedTruck extends Vehicle {

    private int FLATBED_MAXCAPACITY;
    private static double MAX_LOADING_RADIUS;
    private double UNLOAD_OFFSET;


    private boolean isFlatBedUp = true;
    private final FlatBed<Car> flatBed;

    public FlatBedTruck(int nrDoors,Color color , double enginePower, String modelName, double maxLoadingRadius, double unloadOffset) {
        super(nrDoors, color, enginePower, modelName);
        this.MAX_LOADING_RADIUS = maxLoadingRadius;
        this.UNLOAD_OFFSET = unloadOffset;
        this.flatBed = new FlatbedM1<Car>(FLATBED_MAXCAPACITY);
    }

    //Methods for loading, unloading and adjusting flatbed.

    public void loadFlatBed(Car car) {
        if (car.isLoaded()) {
            return;
        }
        //Position truckPos = this.getMovementObj().getPosition();
        if (isFlatBedLoadable() && isWithinRadius(car) && !flatBed.isFull()) {
            car.setIsLoaded(true);
            this.flatBed.loadObject(car);
            car.setPos(this.getPosition());
            car.setVector(this.getVector());
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
        Position truckPos = this.getPosition();
        Vector carUnloadedVector = this.getVector();
        Position carUnloadedPosition = new Position(truckPos.getX() + UNLOAD_OFFSET,
                truckPos.getY() + UNLOAD_OFFSET);

        unloadedCar.setPos(carUnloadedPosition);
        unloadedCar.setVector(carUnloadedVector);
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
        double distance = Math.hypot(
                car.getPosition().getX() - this.getPosition().getX(),
                car.getPosition().getY() - this.getPosition().getY());
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
/*
    @Override
    public void move() {
        if (this.isMoveable()) {
            this.getMovementObj().move(this.getCurrentSpeed());
            this.updateLoadedCarPositions();
        }
    }

 */


    @Override
    public void move() {
        if (this.isMoveable()) {
            Movement.move(getPosition(), this.getVector(), this.getCurrentSpeed());
            this.updateLoadedCarPositions();
        }
    }

    private void updateLoadedCarPositions() {
        Position truckPos = this.getPosition();
        Vector truckVector = this.getVector();

        for (Car car : this.flatBed.getLoadedCars()) {
            car.setPos(truckPos);
            car.setVector(truckVector);

        }
    }
}
