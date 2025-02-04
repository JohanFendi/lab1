import java.awt.*;

public class FlatBedTruck extends Truck {

    private static final int NR_DOORS = 2;
    public static final int ENGINE_POWER = 250;
    public static final String MODEL_NAME = "FlatBedTruck";

    public FlatBedTruck(Color color){
        super(FlatBedTruck.NR_DOORS, color, FlatBedTruck.ENGINE_POWER, FlatBedTruck.MODEL_NAME);
    }


}
