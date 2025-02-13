
import java.awt.*;

abstract public class Car extends Vehicle implements Movable {

    public Car (int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    @Override
    public void move(){
        this.getMovementObj().move(this.getCurrentSpeed());
    }

    @Override
    public void turnRight(){
        this.getMovementObj().turnRight();
    }

    @Override
    public void turnLeft(){
        this.getMovementObj().turnLeft();
    }


}