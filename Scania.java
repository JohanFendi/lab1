import java.awt.*;
                    //TODO satte "Double" tillfälligt
public class Scania extends Truck<Double> {


    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 50;
    private static final String MODEL_NAME = "Scania";



    public Scania(Color color){

        super(Scania.NR_DOORS, color, Scania.ENGINE_POWER, Scania.MODEL_NAME);
    }





    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

    @Override
    protected void adjustRamp(double angle) {
        TruckContainer<Double> container = this.getContainer();
        for (double a : angle) {
            container.raiseContainer();
        }
    }

    @Override
    protected boolean isLoadable() {
        return false;
    }


    @Override
    protected void loadTruck(Double x) {

    }

    @Override
    protected Double unLoadTruck() {
        return null;
    }


    // TODO implementera att lastbilen inte ska kunna köra med flaket uppfällt

}
