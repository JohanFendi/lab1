package src.model;

import java.util.ArrayList;

public class ModelFacade {
    private CarRepairShop<Volvo240> volvoRepairShop = new CarRepairShop<Volvo240>(10);
    private int workShopPickUpDist = 25; //Manhattan distance
    private Position repairshopPosition = new Position(300, 100);
    private final ArrayList<Vehicle> vehicles;
    private int mapWidth;
    private int mapHeight;
    private int objectWidth;


    public ModelFacade(int mapHeight, int mapWidth, int objectWidth, ArrayList<Vehicle> vehicles){
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.objectWidth = objectWidth;
        this.vehicles = vehicles;
    }

    public ArrayList<Position> getPositions(){
        ArrayList<Position> positions = new ArrayList<>();
        for (Vehicle vehicle : this.vehicles){
            positions.add(vehicle.getMovementObj().getPosition());
        }
        positions.add(repairshopPosition);
        return positions;
    }


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
    }

    private boolean pickUpVolvo(Vehicle vehicle){
        if (vehicle instanceof Volvo240) {
            Position volvoPosition = vehicle.getMovementObj().getPosition();
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
        int x = (int) Math.round(vehicle.getMovementObj().getPosition().getX());
        int y = (int) Math.round(vehicle.getMovementObj().getPosition().getY());
        Position position = vehicle.getMovementObj().getPosition();
        Vector vector = vehicle.getMovementObj().getVector();
        if (!(0 <= position.getX() && position.getX() < this.mapWidth - this.objectWidth)) {
            vector.setX(vector.getX() * -1);
        }
        if (!(0 <= position.getY() && position.getY() < this.mapHeight - this.objectWidth)) {
            vector.setY(vector.getY() * -1);
        }
    }

    public void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Vehicle vehicle : this.vehicles) {
            vehicle.gas(gasAmount);
        }
    }

    public void brake(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Vehicle vehicle : this.vehicles) {
            vehicle.brake(gasAmount);
        }
    }

    public void setTurboOn() {
        for(Vehicle vehicle : this.vehicles) {
            if(vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    public void setTurboOff() {
        for(Vehicle vehicle: this.vehicles) {
            if(vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for(Vehicle vehicle: this.vehicles){
            if(vehicle instanceof Scania) {
                ((Scania) vehicle).adjustRamp(70);
            }
        }
    }
    public void lowerBed() {
        for(Vehicle vehicle: this.vehicles) {
            if(vehicle instanceof Scania) {
                ((Scania) vehicle).adjustRamp(0);
            }
        }
    }

    public void startVehicles() {
        for(Vehicle vehicle: this.vehicles){
            vehicle.startEngine();
        }
    }
    public void stopVehicles() {
        for(Vehicle vehicle: this.vehicles){
            vehicle.stopEngine();
        }
    }
}
