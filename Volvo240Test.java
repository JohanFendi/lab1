import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    private Volvo240 volvo240;
    private MovementObj volvoMovmentObj;
    private Vector volvoVector;
    private Position volvoPosition;

    @BeforeEach
    void betweenTests(){
        this.volvo240 = new Volvo240(Color.black);
        MovementObj volvoMovmentObj = volvo240.getMovementObj();
        Vector volvoVector = volvoMovmentObj.getVector();
        Position volvoPosition = volvo240.getMovementObj().getPosition();


    }

    @Test
    void testStartVector(){
        assertEquals(1, volvoVector.getX());
        assertEquals(0, volvoVector.getY());
    }

    @Test
    void testTurnLeft90degrees(){
        volvo240.turnLeft();
        assertEquals(0.0, volvoVector.getX(), 1e-10);
        assertEquals(1, volvoVector.getY());
    }

    @Test
    void testTurnLeft180degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertEquals(-1, volvoVector.getX());
        assertEquals(0.0, volvoVector.getY(), 1e-10);
    }

    @Test
    void testTurnLeft270degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertEquals(0, volvoVector.getX());
        assertEquals(-1, volvoVector.getY());
    }

    @Test
    void testTurnLeft360degrees(){
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.turnLeft();
        assertEquals(1, volvoVector.getX());
        assertEquals(0, volvoVector.getY());
    }

    @Test
    void testTurnRight90degrees(){
        volvo240.turnRight();
        assertEquals(0, volvoVector.getX());
        assertEquals(-1, volvoVector.getY());
    }

    @Test
    void testTurnRight180degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        assertEquals(-1, volvoVector.getX());
        assertEquals(0.0, volvoVector.getY(), 1e-10);
    }

    @Test
    void testTurnRight270degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        assertEquals(0.0, volvoVector.getX(),1e-10);
        assertEquals(1, volvoVector.getY());
    }

    @Test
    void testTurnRight360degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnRight();
        assertEquals(1, volvoVector.getX());
        assertEquals(0, volvoVector.getY());
    }

    @Test
    void testTurnRight180degreesLeft90degrees(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.turnLeft();
        assertEquals(0, volvoVector.getX());
        assertEquals(-1, volvoVector.getY());
    }


    @Test
    void testMoveForwardXmToLeftXm(){
        volvo240.gas(0.5);
        volvo240.move();
        volvo240.turnLeft();
        volvo240.move();
        assertEquals(volvoPosition.getX(), volvoPosition.getY());
    }

    @Test
    void testMoveBackwardsXmToLeftXm(){
        volvo240.turnRight();
        volvo240.turnRight();
        volvo240.gas(0.5);
        volvo240.move();
        volvo240.turnLeft();
        volvo240.move();
        assertEquals(volvoPosition.getX(), volvoPosition.getY());
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
        assertEquals(volvoPosition.getX(), 0);
    }

    @Test
    void getYPos() {
        assertEquals(volvoPosition.getY(), 0);
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

