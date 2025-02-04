import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class Saab95Test {

    private Saab95 saab;

    @BeforeEach
    void betweenTests(){
        this.saab = new Saab95(Color.black);
    }


    @Test
    void testStartVector(){
        assertTrue(saab.getyVector() == 0 && saab.getxVector() == 1);
    }

    @Test
    void testTurnLeft90degrees(){
        saab.turnLeft();
        assertTrue(saab.getyVector() == 1 && saab.getxVector() == 0);
    }

    @Test
    void testTurnLeft180degrees(){
        saab.turnLeft();
        saab.turnLeft();
        assertTrue(saab.getyVector() == 0 && saab.getxVector() == -1);
    }

    @Test
    void testTurnLeft270degrees(){
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        assertTrue(saab.getyVector() == -1 && saab.getxVector() == 0);
    }

    @Test
    void testTurnLeft360degrees(){
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        assertTrue(saab.getyVector() == 0 && saab.getxVector() == 1);
    }

    @Test
    void testTurnRight90degrees(){
        saab.turnRight();
        assertTrue(saab.getyVector() == -1 && saab.getxVector() == 0);
    }

    @Test
    void testTurnRight180degrees(){
        saab.turnRight();
        saab.turnRight();
        assertTrue(saab.getyVector() == 0 && saab.getxVector() == -1);
    }

    @Test
    void testTurnRight270degrees(){
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        assertTrue(saab.getyVector() == 1 && saab.getxVector() == 0);
    }

    @Test
    void testTurnRight360degrees(){
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        assertTrue(saab.getyVector() == 0 && saab.getxVector() == 1);
    }

    @Test
    void testTurnRight180degreesLeft90degrees(){
        Saab95 saab = new Saab95(Color.black);
        saab.turnRight();
        saab.turnRight();
        saab.turnLeft();
        assertTrue(saab.getyVector() == -1 && saab.getxVector() == 0);
    }

    @Test
    void testMoveForwardXmToLeftXm(){
        Saab95 saab = new Saab95(Color.black);
        saab.gas(0.5);
        saab.move();
        saab.turnLeft();
        saab.move();
        assertEquals(saab.getXPos(), saab.getYPos());
    }

    @Test
    void testMoveBackwardsXmToLeftXm(){
        Saab95 saab = new Saab95(Color.black);
        saab.turnRight();
        saab.turnRight();
        saab.gas(0.5);
        saab.move();
        saab.turnLeft();
        saab.move();
        assertEquals(saab.getXPos(), saab.getYPos());
    }

    @Test
    void incrementSpeed10Procent(){
        saab.incrementSpeed(0.1);
        assertEquals(0.125, saab.getCurrentSpeed());
    }

    @Test
    void decrementSpeed10Procent(){
        saab.decrementSpeed(0.1);
        assertEquals(-0.125, saab.getCurrentSpeed());
    }

    @Test
    void incrementSpeed10ProcentWithTurbo(){
        saab.setTurboOn();
        saab.incrementSpeed(0.1);
        assertEquals(0.1625, saab.getCurrentSpeed());
    }

    @Test
    void testCurrentSpeedValidWhenGasing(){
        double amount = 0.99;

        for (int i = 0; i < 200 ; i++){
            saab.gas(amount);
        }
        assertTrue(0 <= saab.getCurrentSpeed() && saab.getCurrentSpeed() <= saab.getEnginePower());
    }

    @Test
    void testCurrentSpeedValidWhenBraking(){
        double amount = 0.99;

        for (int i = 0; i < 200 ; i++){
            saab.brake(amount);
        }
        assertTrue(0 <= saab.getCurrentSpeed() && saab.getCurrentSpeed() <= saab.getEnginePower());
    }

    @Test
    void getXPos() {
        assertEquals(saab.getXPos(), 0);
    }

    @Test
    void getYPos() {
        assertEquals(saab.getYPos(), 0);
    }

    @Test
    void getModelName() {
        assertEquals(saab.getModelName(), "Saab95");
    }

    @Test
    void getNrDoors() {
        assertEquals(saab.getNrDoors(), 2);
    }

    @Test
    void getEnginePower() {
        assertEquals(saab.getEnginePower(), 125);
    }

    @Test
    void getCurrentSpeed() {
        assertEquals(saab.getCurrentSpeed(), 0);
    }

    @Test
    void getColor() {
        assertSame(saab.getColor(), Color.black);
    }

    @Test
    void setColor() {
        saab.setColor(Color.blue);
        assertSame(saab.getColor(), Color.blue);
    }

    @Test
    void startEngine() {
        saab.startEngine();
        assertEquals(saab.getCurrentSpeed(), 0.1);
    }
}