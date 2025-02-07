
import java.awt.*;

abstract public class Car extends Vehicle implements Movable {

    public Car (int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);

    }

    @Override
    public void move(){
        Position position = this.getPosition();
        Vector vector = this.getVector();
        position.setX(position.getX() + this.getCurrentSpeed() * vector.getX());
        position.setY(position.getY() + this.getCurrentSpeed() * vector.getY());
        this.setPosition(position);
    }

    @Override
    public void turnRight(){
        Vector vector = this.getVector();
        double temp = vector.getY();
        vector.setY(-vector.getX());
        vector.setX(temp);
        this.setVector(vector);
    }

    @Override
    public void turnLeft(){
        Vector vector = this.getVector();
        double temp = vector.getY();
        vector.setY(vector.getX());
        vector.setX(-temp);
        this.setVector(vector);
    }


}