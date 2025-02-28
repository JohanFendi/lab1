package src;

import src.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    private static final int windowHeight = 800;
    private static final int windowWidth = 800;
    private static final int objectWidth = 100;

    private final int delay = 50;
    private Timer timer = new Timer(delay, new App.TimerListener());

    private ModelFacade modelFacade;
    private Controller carController;
    private View view;

    public static void main(String[] args){

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<String> pictureRoutes = new ArrayList<>();
        VehicleFactory factory = new VolvoFactory();
        HashMap<String, String> classPictureMap = new HashMap<>();

        pictureRoutes.add("pics/VolvoBrand.jpg"); //CarRepairShop

        Volvo240 volvo = new Volvo240(Color.green);
        volvo.setPos(new Position(100, 100));
        vehicles.add(volvo); //Add vehicles and their respective picture links before creating view.
        pictureRoutes.add("pics/Volvo240.jpg");
        classPictureMap.put(volvo.getClass().getName(), "pics/Volvo240.jpg");

        Scania scania = new Scania(Color.green, 1000);
        scania.setPos(new Position(100, 300));
        vehicles.add(scania);
        pictureRoutes.add("pics/Scania.jpg");
        classPictureMap.put(scania.getClass().getName(),"pics/Scania.jpg");


        Saab95 saab = new Saab95(Color.green);
        saab.setPos(new Position(100, 200));
        vehicles.add(saab);
        pictureRoutes.add("pics/Saab95.jpg");
        classPictureMap.put(saab.getClass().getName(),"pics/Saab95.jpg");


        ModelFacade model = new ModelFacade(App.windowHeight, App.windowWidth, App.objectWidth, vehicles, factory);
        CarController carController = new CarController(model);
        CarView carView = new CarView(carController, pictureRoutes, App.windowWidth, App.windowHeight, model, classPictureMap);
        App app = new App(model);

        model.addListener(carView);
        app.timer.start();
    }

    public App(ModelFacade modelFacade){
        this.modelFacade = modelFacade;
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            App.this.modelFacade.update();
        }
    }
}
