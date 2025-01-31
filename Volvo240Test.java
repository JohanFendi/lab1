import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    private Volvo240 volvo240;

    @BeforeEach
    void betweenTests(){
        this.volvo240 = new Volvo240(Color.black);
    }

    @Test
    void testStartVector(){
        assertTrue(volvo240.getyVector() == 0 && volvo240.getxVector() == 1);
    }

    @Test
    void testTurnLeft90degrees(){
        volvo240.turnLeft();
        assertTrue(volvo240.getyVector() == 1 && volvo240.getxVector() == 0);
    }

    @Test
    void testTurnLeft180degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertTrue(volvo240.getyVector() == 0 && volvo240.getxVector() == -1);
    }

    @Test
    void testTurnLeft270degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertTrue(volvo240.getyVector() == -1 && volvo240.getxVector() == 0);
    }

    @Test
    void testTurnLeft360degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertTrue(volvo240.getyVector() == 0 && volvo240.getxVector() == 1);
    }

    @Test
    void testTurnRight90degrees(){
        volvo240.turnRight();
        assertTrue(volvo240.getyVector() == -1 && volvo240.getxVector() == 0);
    }

    @Test
    void testTurnRight180degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        assertTrue(volvo240.getyVector() == 0 && volvo240.getxVector() == -1);
    }

    @Test
    void testTurnRight270degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        assertTrue(volvo240.getyVector() == 1 && volvo240.getxVector() == 0);
    }

    @Test
    void testTurnRight360degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        assertTrue(volvo240.getyVector() == 0 && volvo240.getxVector() == 1);
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
        assertEquals(volvo240.getXPos(), 0);
    }

    @Test
    void getYPos() {
        assertEquals(volvo240.getYPos(), 0);
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

