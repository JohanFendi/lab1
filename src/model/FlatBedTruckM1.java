package src.model;

import java.awt.*;

public class FlatBedTruckM1 extends FlatBedTruck {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 75;
    private static final String MODEL_NAME = "FlatBedTruck";
    private static final int FLATBED_MAXCAPACITY = 10;
    private static final double MAX_LOADING_RADIUS = 1;
    private static final double UNLOAD_OFFSET = 1;


    private boolean isFlatBedUp = true;
    private final FlatbedM1<Car> flatBed;

    public FlatBedTruckM1(Color color, FlatBed flatBed) {
        super(FlatBedTruckM1.NR_DOORS,
                color,
                FlatBedTruckM1.ENGINE_POWER,
                FlatBedTruckM1.MODEL_NAME,
                FlatBedTruckM1.MAX_LOADING_RADIUS,
                FlatBedTruckM1.UNLOAD_OFFSET);
        this.flatBed = new FlatbedM1<Car>(FLATBED_MAXCAPACITY);
    }
}
