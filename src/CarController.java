package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */


// Cars and their picture links are added in the main method.
public class CarController {
    private static final String WINDOW_TITLE = "CarSim 1.0";
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    // The frame that represents this instance View of the MVC pattern
    private CarView frame;
    private CarRepairShop<Volvo240> volvoRepairShop = new CarRepairShop<Volvo240>(10);

    private int workShopPickUpDist = 25; //Manhattan distance
    private Position repairshopPosition = new Position(300, 100);

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();  // Listan med alla fordon
    private final ArrayList<String> pictureRoutes = new ArrayList<>(); // Listan med alla bild-vägar

    public static void main(String[] args) {
        CarController carController = new CarController();

        Volvo240 volvo = new Volvo240(Color.green);
        volvo.getMovementObj().setPosition(new Position(100, 100));
        carController.vehicles.add(volvo); //Add vehicles and their respective picture links before creating view.
        carController.pictureRoutes.add("pics/Volvo240.jpg");

        Scania scania = new Scania(Color.green, 1000);
        scania.getMovementObj().setPosition(new Position(100, 300));
        carController.vehicles.add(scania);
        carController.pictureRoutes.add("pics/Scania.jpg");

        Saab95 saab = new Saab95(Color.green);
        saab.getMovementObj().setPosition(new Position(100, 200));
        carController.vehicles.add(saab);
        carController.pictureRoutes.add("pics/Saab95.jpg");

        carController.pictureRoutes.add("pics/VolvoBrand.jpg");

        // Skapar applikationsfönstret, får en referens till carController,
        // för att knyta användarens interaktioner (knapptryck) med de metoder i kontrollern som hanterar logiken.
        carController.frame = new CarView(WINDOW_TITLE, carController);
        carController.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < CarController.this.vehicles.size(); i++) {
                Vehicle vehicle = CarController.this.vehicles.get(i);
                if (!vehicle.isMoveable()){
                    continue;
                }
                if (vehicle  instanceof  Volvo240){
                    Position volvoPosition = vehicle.getMovementObj().getPosition();
                    double manhattanDist = Math.abs(volvoPosition.getX() - repairshopPosition.getX()) + Math.abs(volvoPosition.getY() - repairshopPosition.getY());
                    if (manhattanDist < CarController.this.workShopPickUpDist){
                        ((Volvo240) vehicle).setIsLoaded(true);
                        CarController.this.volvoRepairShop.addCar((Volvo240) vehicle);
                    }
                }


                String pictureRoute = CarController.this.pictureRoutes.get(i);
                vehicle.move();
                int x = (int) Math.round(vehicle.getMovementObj().getPosition().getX());
                int y = (int) Math.round(vehicle.getMovementObj().getPosition().getY());
                Position position = vehicle.getMovementObj().getPosition();
                Vector vector = vehicle.getMovementObj().getVector();
                if (!(0 <= position.getX() && position.getX() < CarController.this.frame.getScreenWidth()-100)){
                    vector.setX(vector.getX() * -1);
                }
                if (!(0 <= position.getY() && position.getY() < CarController.this.frame.getScreenHeight()-100)){
                    vector.setY(vector.getY() * -1);
                }

                //Bryter mot Seperation of Concern, CarController måste känna till den inre strukturen i CarView, att den har en drawPanel.
                // Istället, hade CarController inte längre behövt känna till att CarView har en drawPanel eller hur den fungerar.
                // Den ansvarar enbart för att säga “uppdatera vyn” och låter CarView hantera detaljerna för att rita om fordonen.
                CarController.this.frame.drawPanel.moveit(x, y, pictureRoute);
                CarController.this.frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Vehicle vehicle : this.vehicles) {
            vehicle.gas(gasAmount);
        }
    }

    void brake(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Vehicle vehicle : this.vehicles) {
            vehicle.brake(gasAmount);
        }
    }

    void setTurboOn() {
        for(Vehicle vehicle : this.vehicles) {
            if(vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
                //måste fixa, inte bra med såna casts
            }
        }
    }
    void setTurboOff() {
        for(Vehicle vehicle: this.vehicles) {
            if(vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOff();
                //måste fixa, inte bra med såna casts
            }
        }
    }


    void liftBedButton() {
        for(Vehicle vehicle: this.vehicles){
            if(vehicle instanceof Scania) {
                ((Scania) vehicle).adjustRamp(70);
                //måste fixa, inte bra med såna casts
            }
        }
    }
    void lowerBedButton() {
        for(Vehicle vehicle: this.vehicles) {
            if(vehicle instanceof Scania) {
                ((Scania) vehicle).adjustRamp(0);
                //måste fixa, inte bra med såna casts
            }
        }
    }

    void startButton() {
        for(Vehicle vehicle: this.vehicles){
            vehicle.startEngine();
        }
    }
    void stopButton() {
        for(Vehicle vehicle: this.vehicles){
            vehicle.stopEngine();
        }
    }

    protected ArrayList<Vehicle> getVehicles(){
        return this.vehicles;
    }

    protected ArrayList<String> getPictureRoutes(){
        return this.pictureRoutes;
    }



}
