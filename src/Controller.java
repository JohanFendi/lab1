package src;

import src.model.ModelFacade;

public class Controller {
    private final ModelFacade modelFacade;

    public Controller(ModelFacade modelFacade){
        this.modelFacade = modelFacade;
    }

    void gas(int amount) {
        this.modelFacade.gas(amount);
    }

    void brake(int amount) {
        this.modelFacade.brake(amount);
    }

    void setTurboOn() {
        this.modelFacade.setTurboOn();
    }

    void setTurboOff() {
        this.modelFacade.setTurboOff();
    }

    void liftBed() {
        this.modelFacade.liftBed();
    }

    void lowerBed() {
        this.modelFacade.lowerBed();
    }

    void startVehicles() {
        this.modelFacade.startVehicles();
    }
    void stopVehicles() {
        this.modelFacade.stopVehicles();
    }
}
