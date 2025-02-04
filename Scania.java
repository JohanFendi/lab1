import java.awt.*;

public class Scania extends Vehicle {


    private static final int NR_DOORS = 2;
    public static final int ENGINE_POWER = 300;
    public static final String MODEL_NAME = "Scania";

    public Scania(Color color){
        super(Scania.NR_DOORS, color, Scania.ENGINE_POWER, Scania.MODEL_NAME);
    }

}
