package src;

import src.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App {

    private static final int windowHeight = 800;
    private static final int windowWidth = 800;
    private static final int objectWidth = 100;

    private final int delay = 50;
    private Timer timer = new Timer(delay, new App.TimerListener());

    private ModelFacade modelFacade;
    private CarController carController;
    private CarView view;

    public static void main(String[] args){

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<String> pictureRoutes = new ArrayList<>();

        Volvo240 volvo = new Volvo240(Color.green);
        volvo.getMovementObj().setPosition(new Position(100, 100));
        vehicles.add(volvo); //Add vehicles and their respective picture links before creating view.
        pictureRoutes.add("pics/Volvo240.jpg");


        Scania scania = new Scania(Color.green, 1000);
        scania.getMovementObj().setPosition(new Position(100, 300));
        vehicles.add(scania);
        pictureRoutes.add("pics/Scania.jpg");

        Saab95 saab = new Saab95(Color.green);
        saab.getMovementObj().setPosition(new Position(100, 200));
        vehicles.add(saab);
        pictureRoutes.add("pics/Saab95.jpg");


        pictureRoutes.add("pics/VolvoBrand.jpg");

        ModelFacade model = new ModelFacade(App.windowHeight, App.windowWidth, App.objectWidth, vehicles);
        CarController carController = new CarController(model);
        CarView carView = new CarView(carController, pictureRoutes, model.getPositions(), App.windowWidth, App.windowHeight);
        //View carView = new View(carController, new ArrayList<>(), new ArrayList<>(), App.windowWidth, App.windowHeight);
        App app = new App(model, carController, carView);
        app.timer.start();
        System.out.println();
    }

    public App(ModelFacade modelFacade, CarController carController, CarView view){
        this.modelFacade = modelFacade;
        this.carController = carController;
        this.view = view;
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            App.this.modelFacade.update();
            App.this.view.update(App.this.modelFacade.getPositions()); //Här skippar vi ju carcontroller
        }
    }
}
