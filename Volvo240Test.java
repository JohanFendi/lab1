import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    private Volvo240 volvo240;
    private Saab95 saab95;

    @BeforeEach
    void betweenTests(){
        volvo240 = new Volvo240(Color.black);
        saab95 = new Saab95(Color.BLUE);

    }

    @Test
    void testStartVector(){
        assertEquals(1, volvo240.getVector().getX());
        assertEquals(0, volvo240.getVector().getY());
    }

    @Test
    void testTurnLeft90degrees(){
        volvo240.turnLeft();
        assertEquals(0.0, volvo240.getVector().getX(), 1e-10);
        assertEquals(1, volvo240.getVector().getY());
    }

    @Test
    void testTurnLeft180degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertEquals(-1, volvo240.getVector().getX());
        assertEquals(0.0, volvo240.getVector().getY(), 1e-10);
    }

    @Test
    void testTurnLeft270degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertEquals(0, volvo240.getVector().getX());
        assertEquals(-1, volvo240.getVector().getY());
    }

    @Test
    void testTurnLeft360degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertEquals(1, volvo240.getVector().getX());
        assertEquals(0, volvo240.getVector().getY());
    }

    @Test
    void testTurnRight90degrees(){
        volvo240.turnRight();
        assertEquals(0, volvo240.getVector().getX());
        assertEquals(-1, volvo240.getVector().getY());
    }

    @Test
    void testTurnRight180degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        assertEquals(-1, volvo240.getVector().getX());
        assertEquals(0.0, volvo240.getVector().getY(), 1e-10);
    }

    @Test
    void testTurnRight270degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        assertEquals(0.0, volvo240.getVector().getX(),1e-10);
        assertEquals(1, volvo240.getVector().getY());
    }

    @Test
    void testTurnRight360degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        assertEquals(1, volvo240.getVector().getX());
        assertEquals(0, volvo240.getVector().getY());
    }

    @Test
    void testTurnRight180degreesLeft90degrees(){
        saab95.turnRight();
        saab95.turnRight();
        saab95.turnLeft();
        assertEquals(0, saab95.getVector().getX());
        assertEquals(-1, saab95.getVector().getY());
    }


    @Test
    void testMoveForwardXmToLeftXm(){
        saab95.gas(0.5);
        saab95.move();
        saab95.turnLeft();
        saab95.move();
        assertEquals(saab95.getPosition().getX(), saab95.getPosition().getY());
    }

    @Test
    void testMoveBackwardsXmToLeftXm(){
        saab95.turnRight();
        saab95.turnRight();
        saab95.gas(0.5);
        saab95.move();
        saab95.turnLeft();
        saab95.move();
        assertEquals(saab95.getPosition().getX(), saab95.getPosition().getY());
    }

    @Test
    void incrementSpeed10Procent(){
        volvo240.incrementSpeed(0.1);
        assertEquals(0.125, volvo240.getCurrentSpeed());
    }

    @Test
    void decrementSpeed10Procent(){
        volvo240.decrementSpeed(0.1);
        assertEquals(-0.125, volvo240.getCurrentSpeed());
    }


    @Test
    void testCurrentSpeedValidWhenGasing(){
        double amount = 0.99;

        for (int i = 0; i < 200 ; i++){
            volvo240.gas(amount);
        }
        assertTrue(0 <= volvo240.getCurrentSpeed() && volvo240.getCurrentSpeed() <= volvo240.getEnginePower());
    }

    @Test
    void testCurrentSpeedValidWhenBraking(){
        double amount = 0.99;

        for (int i = 0; i < 200 ; i++){
            volvo240.brake(amount);
        }
        assertTrue(0 <= volvo240.getCurrentSpeed() && volvo240.getCurrentSpeed() <= volvo240.getEnginePower());
    }

    @Test
    void getXPos() {
        assertEquals(volvo240.getPosition().getX(), 0);
    }

    @Test
    void getYPos() {
        assertEquals(volvo240.getPosition().getY(), 0);
    }

    @Test
    void getModelName() {
        assertEquals(volvo240.getModelName(), "Volvo240");
    }

    @Test
    void getNrDoors() {
        assertEquals(volvo240.getNrDoors(), 4);
    }

    @Test
    void getEnginePower() {
        assertEquals(volvo240.getEnginePower(), 100);
    }

    @Test
    void getCurrentSpeed() {
        assertEquals(volvo240.getCurrentSpeed(), 0);
    }

    @Test
    void getColor() {
        assertSame(volvo240.getColor(), Color.black);
    }

    @Test
    void setColor() {
        volvo240.setColor(Color.blue);
        assertSame(volvo240.getColor(), Color.blue);
    }

    @Test
    void startEngine() {
        volvo240.startEngine();
        assertEquals(volvo240.getCurrentSpeed(), 0.1);
    }

}

