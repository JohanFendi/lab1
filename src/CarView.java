package src;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CarView extends JFrame implements ModelListener {
    private static final String WINDOW_TITLE = "CarSim 1.0";
    private static final String PICTURE_NOT_FOUND_EXCEPTION = "PictureNotFoundException: Class name does not have corresponding picture. ";
    private int windowWidth;
    private int windowHeight;

    private DrawPanel drawPanel;
    private final Controller carController;
    private final Observable model;

    private final JPanel controlPanel = new JPanel();
    private final JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner;
    private int gasAmount = 0;
    private final HashMap<String, String> classPictureMap;

    private final JLabel gasLabel = new JLabel("Amount of gas");
    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton addCarButton = new JButton("AddCar");
    private final JButton removeCarButton = new JButton("RemoveCar");
    private final JButton turboOnButton = new JButton("Saab Turbo on");
    private final JButton turboOffButton = new JButton("Saab Turbo off");
    private final JButton liftBedButton = new JButton("Scania Lift Bed");
    private final JButton lowerBedButton = new JButton("Lower Lift Bed");
    private final JButton startButton = new JButton("Start all cars");
    private final JButton stopButton = new JButton("Stop all cars");

    public CarView(CarController carController, ArrayList<String> pictureRoutes, int windowWidth, int windowHeight, Observable model, HashMap<String, String> classPictureMap){
        this.carController = carController;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.model = model;
        this.classPictureMap = classPictureMap;
        initComponents(pictureRoutes);
    }

    @Override
    public void actOnNotification(){
        this.drawPanel.updatePicturePositions(model.getObjectPositions());
        this.drawPanel.repaint();
    }

    private void initComponents(ArrayList<String> pictureRoutes) {
        this.setTitle(CarView.WINDOW_TITLE);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.drawPanel = new DrawPanel(windowWidth, windowHeight - 240, model.getObjectPositions(), pictureRoutes, Color.green);
        this.add(drawPanel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);

        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = carController.addCar();
                String pictureRoute = classPictureMap.get(className);
                drawPanel.addImage(pictureRoute);
                model.update();
            }
        });

        removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carController.removeCar()) {
                    drawPanel.removeLastImage();
                }
            }
        });

        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.gas(gasAmount);}
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.brake(gasAmount);}
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.setTurboOn();
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.setTurboOff();
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.liftBed();
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.lowerBed();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.startVehicles();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.stopVehicles();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,5));
        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton, 6);
        controlPanel.add(removeCarButton, 7);
        controlPanel.setPreferredSize(new Dimension((this.windowWidth/2)+4, 200));
        controlPanel.setBackground(Color.CYAN);
        this.add(controlPanel);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(this.windowWidth/5-15,100));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(this.windowWidth/5-15,100));
        this.add(stopButton);



        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();
        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
