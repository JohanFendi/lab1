package src;

public class MovementObj {

    private Vector vector;
    private Position position;

    public MovementObj(Vector vector, Position position){
        this.vector = vector;
        this.position = position;
    }

    public Vector getVector() {
        return vector;
    }

    public Position getPosition(){
        return  this.position;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void move(double speed){
        Position position = this.getPosition();
        Vector vector = this.getVector();
        position.setX(position.getX() + speed * vector.getX());
        position.setY(position.getY() + speed * vector.getY());
        this.setPosition(position);
    }

    public void turnRight(){
        Vector vector = this.getVector();
        double temp = vector.getY();
        vector.setY(-vector.getX());
        vector.setX(temp);
        this.setVector(vector);
    }

    public void turnLeft(){
        Vector vector = this.getVector();
        double temp = vector.getY();
        vector.setY(vector.getX());
        vector.setX(-temp);
        this.setVector(vector);
    }
}
