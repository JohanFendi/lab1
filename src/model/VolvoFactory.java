package src.model;

import java.awt.*;

public class VolvoFactory implements VehicleFactory {

    public VolvoFactory() {
    }

    @Override
    public Car createCar() {
        return new Volvo240(Color.BLACK);
    }

    @Override
    public Vehicle createRandomVehicle() {
        return null;
    }





}
