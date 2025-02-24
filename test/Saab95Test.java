package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

import src.model.MovementObj;
import src.model.Position;
import src.model.Saab95;
import src.model.Vector;

class Saab95Test {

    private Saab95 saab;
    private Vector saabVector;
    private Position saabPosition;

    @BeforeEach
    void betweenTests(){
        this.saab = new Saab95(Color.black);
        MovementObj saabMovementObj = saab.getMovementObj();
        saabVector = saabMovementObj.getVector();
        saabPosition = saab.getMovementObj().getPosition();

    }
    @Test
    void testStartVector(){
        assertTrue(saabVector.getY() == 0 && saabVector.getX() == 1);
    }

    @Test
    void testTurnLeft90degrees(){
        saab.turnLeft();
        assertTrue(saabVector.getY() == 1 && saabVector.getX() == 0);
    }

    @Test
    void testTurnLeft180degrees(){
        saab.turnLeft();
        saab.turnLeft();
        assertTrue(saabVector.getY()== 0 && saabVector.getX() == -1);
    }

    @Test
    void testTurnLeft270degrees(){
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        assertTrue(saabVector.getY() == -1 && saabVector.getX() == 0);
    }

    @Test
    void testTurnLeft360degrees(){
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        assertTrue(saabVector.getY() == 0 && saabVector.getX() == 1);
    }

    @Test
    void testTurnRight90degrees(){
        saab.turnRight();
        assertTrue(saabVector.getY() == -1 && saabVector.getX() == 0);
    }

    @Test
    void testTurnRight180degrees(){
        saab.turnRight();
        saab.turnRight();
        assertTrue(saabVector.getY() == 0 && saabVector.getX() == -1);
    }

    @Test
    void testTurnRight270degrees(){
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        assertTrue(saabVector.getY() == 1 && saabVector.getX() == 0);
    }

    @Test
    void testTurnRight360degrees(){
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        assertTrue(saabVector.getY() == 0 && saabVector.getX() == 1);
    }

    @Test
    void testTurnRight180degreesLeft90degrees(){
        saab.turnRight();
        saab.turnRight();
        saab.turnLeft();
        assertTrue(saabVector.getY() == -1 && saabVector.getX() == 0);

    }

    @Test
    void testMoveForwardXmToLeftXm(){

        saab.gas(0.5);
        saab.move();
        saab.turnLeft();
        saab.move();
        Assertions.assertEquals(saabPosition.getX(), saabPosition.getY());
    }

    @Test
    void testMoveBackwardsXmToLeftXm(){

        saab.turnRight();
        saab.turnRight();
        saab.gas(0.5);
        saab.move();
        saab.turnLeft();
        saab.move();
        Assertions.assertEquals(saabPosition.getX(), saabPosition.getY());
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
        Assertions.assertEquals(saabPosition.getX(), 0);
    }

    @Test
    void getYPos() {
        Assertions.assertEquals(saabPosition.getX(), 0);
    }

    @Test
    void getModelName() {
        Assertions.assertEquals(saab.getModelName(), "Saab95");
    }

    @Test
    void getNrDoors() {
        Assertions.assertEquals(saab.getNrDoors(), 2);
    }

    @Test
    void getEnginePower() {
        Assertions.assertEquals(saab.getEnginePower(), 125);
    }

    @Test
    void getCurrentSpeed() {
        Assertions.assertEquals(saab.getCurrentSpeed(), 0);
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
        Assertions.assertEquals(saab.getCurrentSpeed(), 0.1);
    }
}