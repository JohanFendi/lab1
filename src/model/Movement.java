package src.model;

public class Movement {

    private Movement() {

    }





    public static void move(Position pos, Vector v, double speed){
        pos.setX(pos.getX() + speed * v.getX());
        pos.setY(pos.getY() + speed * v.getY());
    }

    public static void turnRight(Vector vector) {
        double temp = vector.getY();
        vector.setY(-vector.getX());
        vector.setX(temp);
    }

    public static void turnLeft(Vector vector){
        double temp = vector.getY();
        vector.setY(vector.getX());
        vector.setX(-temp);
    }






}
