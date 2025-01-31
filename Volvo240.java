import java.awt.*;

public class Volvo240 extends Car{

    private static final int NR_DOORS = 4;
    private static final int ENGINE_POWER = 100;
    private static final String NAME = "Volvo240";
    private static final double TRIM_FACTOR = 1.25;


    public Volvo240(Color color){
        super(Volvo240.NR_DOORS, color, Volvo240.ENGINE_POWER, Volvo240.NAME);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * TRIM_FACTOR;
    }

}
