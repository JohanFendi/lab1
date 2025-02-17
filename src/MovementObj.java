package src;

public class MovementObj {

    private Vector vector;
    private Position position;
    int reverseIdx = 1;

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
        if (position.getX() >= CarView.X){
            reverseIdx = -1;
        }else if (position.getX() <= 0){
            reverseIdx = 1;
        }
        position.setX(position.getX() + ((speed * vector.getX()) * reverseIdx));
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
