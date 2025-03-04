package src.model;

import src.ModelListener;
import src.Observable;

import java.util.ArrayList;

public class ModelFacade implements Model, Observable {
    private final CarRepairShop<Volvo240> volvoRepairShop = new CarRepairShop<>(10);
    private final int workShopPickUpDist = 25; //Manhattan distance
    private final Position repairshopPosition = new Position(300, 100);
    private final ArrayList<Vehicle> vehicles;
    private final VehicleFactory vehicleFactory;

    private final ArrayList<ModelListener> listeners = new ArrayList<>();
    private final int mapWidth;
    private final int mapHeight;
    private final int objectWidth;

    public String addCar() {
        Vehicle car = this.vehicleFactory.createCar();
        car.setPos(new Position(100, 100));
        vehicles.add(car);
        return car.getClass().getName();
    }

    public boolean removeCar() {
        Vehicle vehicle = vehicles.removeLast();

        if (vehicle == null) {
            return false;
        }

        if (vehicle instanceof Volvo240){
            this.volvoRepairShop.takeOutCar((Volvo240) vehicle);
        }

        return true;

    }

    public ModelFacade(int mapHeight, int mapWidth, int objectWidth, ArrayList<Vehicle> vehicles, VehicleFactory factory){
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.objectWidth = objectWidth;
        this.vehicles = vehicles;
        this.vehicleFactory = factory;
    }

    @Override
    public ArrayList<Position> getObjectPositions(){
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(repairshopPosition);
        for (Vehicle vehicle : this.vehicles){
            positions.add(vehicle.getPosition());
        }
        return positions;
    }

    @Override
    public void update() {
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isMoveable()) {
                continue;
            }

            boolean volvoPickedUp = this.pickUpVolvo(vehicle);

            if (volvoPickedUp){
                continue;
            }

            vehicle.move();
            this.keepVehicleInBounds(vehicle);
        }
        this.notifyListeners();
    }

    private boolean pickUpVolvo(Vehicle vehicle){
        if (vehicle instanceof Volvo240) {
            Position volvoPosition = vehicle.getPosition();
            double manhattanDist = Math.abs(volvoPosition.getX() - repairshopPosition.getX()) + Math.abs(volvoPosition.getY() - repairshopPosition.getY());
            if (manhattanDist < workShopPickUpDist) {
                ((Volvo240) vehicle).setIsLoaded(true);
                volvoRepairShop.addCar((Volvo240) vehicle);
                return true;
            }
        }
        return false;
    }

    private void keepVehicleInBounds(Vehicle vehicle){
        int x = (int) Math.round(vehicle.getPosition().getX());
        int y = (int) Math.round(vehicle.getPosition().getY());
        Position position = vehicle.getPosition();
        Vector vector = vehicle.getVector();
        if (!(0 <= position.getX() && position.getX() < this.mapWidth - this.objectWidth)) {
            vector.setX(vector.getX() * -1);
        }
        if (!(0 <= position.getY() && position.getY() < this.mapHeight - this.objectWidth)) {
            vector.setY(vector.getY() * -1);
        }
    }

    @Override
    public void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Vehicle vehicle : this.vehicles) {
            vehicle.gas(gasAmount);
        }
    }

    @Override
    public void brake(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Vehicle vehicle : this.vehicles) {
            vehicle.brake(gasAmount);
        }
    }

    @Override
    public void setTurboOn() {
        for(Vehicle vehicle : this.vehicles) {
            if(vehicle instanceof Turbo) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    @Override
    public void setTurboOff() {
        for(Vehicle vehicle: this.vehicles) {
            if(vehicle instanceof Turbo) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    @Override
    public void liftBed() {
        for(Vehicle vehicle: this.vehicles){
            if(vehicle instanceof ContainerTruck) {
                ((ContainerTruck) vehicle).adjustRamp(70);
            }
        }
    }

    @Override
    public void lowerBed() {
        for(Vehicle vehicle: this.vehicles) {
            if(vehicle instanceof ContainerTruck) {
                ((ContainerTruck) vehicle).adjustRamp(0);
            }
        }
    }

    @Override
    public void startVehicles() {
        for(Vehicle vehicle: this.vehicles){
            vehicle.startEngine();
        }
    }

    @Override
    public void stopVehicles() {
        for(Vehicle vehicle: this.vehicles){
            vehicle.stopEngine();
        }
    }

    @Override
    public void addListener(ModelListener listener){
        this.listeners.add(listener);
    }

    @Override
    public void notifyListeners(){
        for (ModelListener listener : this.listeners){
            listener.actOnNotification();
        }
    }
}
