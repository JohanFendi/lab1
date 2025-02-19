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


    public void invertDirection() {
        double currDirectionX = this.getVector().getX();
        double currDirectionY = this.getVector().getY();

        if(currDirectionX == 1 && currDirectionY == 0) {
            Vector pointLeft = new Vector(-1, 0);
            this.setVector(pointLeft);
        }
        else if(currDirectionX == 0 && currDirectionY == 1) {
            Vector pointDown = new Vector(0, -1);
            this.setVector(pointDown);
        }
        else if(currDirectionX == -1 && currDirectionY == 0) {
            Vector pointRight = new Vector(1, 0);
            this.setVector(pointRight);
        }
        else if(currDirectionX == 0 && currDirectionY == -1) {
            Vector pointUp = new Vector(0, 1);
            this.setVector(pointUp);
        }
    }


    public boolean carHitsWall() {
        if(this.getPosition().getX() < 0 || this.getPosition().getX() > 800
                || this.getPosition().getY() < 0 || this.getPosition().getY() > 560) {
            return true;
        }
        return false;
    }




}
