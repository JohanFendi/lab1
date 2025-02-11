import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class FlatBedTruck extends Vehicle implements Movable {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 75;
    private static final String MODEL_NAME = "FlatBedTruck";
    private static final int FLATBED_MAXCAPACITY = 10;
    private static final double MAX_LOADING_RADIUS = 1;
    private static final double UNLOAD_OFFSET = 1;


    private boolean isRampUp = true;
    private final Flatbed<Car> flatBed;


    public FlatBedTruck(Color color) {
        super(FlatBedTruck.NR_DOORS, color, FlatBedTruck.ENGINE_POWER, FlatBedTruck.MODEL_NAME);
        this.flatBed = new Flatbed<Car>(FLATBED_MAXCAPACITY);
    }

    @Override
    public void move() {
        if(isRampUp) {
            this.getMovementObj().move(this.getCurrentSpeed());
            this.updateLoadedCarPositions();
        }
    }

    @Override
    public void turnLeft(){
        this.getMovementObj().turnLeft();
    }

    @Override
    public void turnRight(){
        this.getMovementObj().turnRight();
    }

    public void loadFlatBed(Car car) {
        if (isFlatBedLoadable(car) && isWithinRadius(car)) {
            this.flatBed.loadObject(car);
        }
        car.setMovementObj(this.getMovementObj());
    }

    public Car unLoadCar() {
        if (isRampUp) {
            return null;
        }

        Car unloadedCar = this.flatBed.unLoadObject();

        if (unloadedCar == null) {
            return null;
        }

        Position truckPos = this.getMovementObj().getPosition();
        Vector carUnloadedVector = this.getMovementObj().getVector();
        Position carUnloadedPosition = new Position(truckPos.getX() + UNLOAD_OFFSET,
                                        truckPos.getY() + UNLOAD_OFFSET);
        unloadedCar.setMovementObj(new MovementObj(carUnloadedVector, carUnloadedPosition));

        return unloadedCar;
    }

    public void lowerRamp() {
        if (isRampAdjustable() && this.isRampUp) {
            this.isRampUp = false;
        }
    }

    public void raiseRamp() {
        if (isRampAdjustable() && !this.isRampUp) {
            this.isRampUp = true;
        }
    }

    public boolean getRampIsUp(){
        return this.isRampUp;
    }

    private boolean isWithinRadius(Car car) {
        Position carPosition = car.getMovementObj().getPosition();
        Position truckPosition = this.getMovementObj().getPosition();

        double distance = Math.hypot(carPosition.getX() - truckPosition.getX(), carPosition.getY() - truckPosition.getY());
        return distance <= FlatBedTruck.MAX_LOADING_RADIUS;
    }

    private void updateLoadedCarPositions() {
        Position truckPos = this.getMovementObj().getPosition();
        Vector truckVector = this.getMovementObj().getVector();

        for (Car car : this.flatBed.getLoadedCars()) {
            car.setMovementObj(new MovementObj(truckVector, truckPos));
        }
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    private boolean isRampAdjustable() {
        return this.getCurrentSpeed() == 0;
    }

    private boolean isFlatBedLoadable(Car car) {
        return !this.isRampUp;
    }
}
