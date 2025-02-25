package src.model;

import java.awt.*;

public class Scania extends ContainerTruck {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 50;
    private static final String MODEL_NAME = "Scania";
    private static final double ANGLE_MAX = 70;
    private static final double ANGLE_MIN = 0;

    private static final double rampAngle = 0.0;


    public Scania( Color color,Container container) {
        super(Scania.NR_DOORS,
                color,
                Scania.ENGINE_POWER,
                Scania.MODEL_NAME,
                container,
                Scania.ANGLE_MAX,
                Scania.ANGLE_MIN,
                Scania.rampAngle); // Call to the superclass constructor

    }
}