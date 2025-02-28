package src;

import src.model.Model;
import src.model.ModelFacade;

public class CarController implements Controller {
    private final Model model;

    public CarController(ModelFacade model){
        this.model = model;
    }

    @Override
    public String addCar(){
        String className = this.model.addCar();
        return className;
    }

    @Override
    public boolean removeCar(){
        return this.model.removeCar();
    }

    @Override
    public void gas(int amount) {
        this.model.gas(amount);
    }

    @Override
    public void brake(int amount) {
        this.model.brake(amount);
    }

    @Override
    public void setTurboOn() {
        this.model.setTurboOn();
    }

    @Override
    public void setTurboOff() {
        this.model.setTurboOff();
    }

    @Override
    public void liftBed() {
        this.model.liftBed();
    }
    @Override
    public void lowerBed() {
        this.model.lowerBed();
    }

    @Override
    public void startVehicles() {
        this.model.startVehicles();
    }

    @Override
    public void stopVehicles() {
        this.model.stopVehicles();
    }
}
