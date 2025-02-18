package src.UI;

import src.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import src.*;
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    private static final String WINDOW_TITLE = "CarSim 1.0";
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    // The frame that represents this instance View of the MVC pattern
    private CarView frame;


    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final ArrayList<String> pictureRoutes = new ArrayList<>();
    public static void main(String[] args) {
        CarController carController = new CarController();
        carController.frame = new CarView(WINDOW_TITLE, carController);
        carController.timer.start();

        //Add vehicles and their respective vehicle links.
        carController.vehicles.add(new Volvo240(Color.green));
        carController.pictureRoutes.add("pics/Volvo240.jpg");
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < CarController.this.vehicles.size(); i++) {
                Vehicle vehicle = CarController.this.vehicles.get(i);
                String pictureRoute = CarController.this.pictureRoutes.get(i);
                vehicle.move();
                int x = (int) Math.round(vehicle.getMovementObj().getPosition().getX());
                int y = (int) Math.round(vehicle.getMovementObj().getPosition().getY());
                CarController.this.frame.drawPanel.moveit(x, y, pictureRoute);
                CarController.this.frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Vehicle vehicle : CarController.this.vehicles) {
            vehicle.gas(gasAmount);
        }
    }
}
