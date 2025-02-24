package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

import src.model.*;

class FlatBedTruckM1Test {

    private FlatBedTruckM1 flatBedTruckM1Obj;
    private Position flatBedPosition;
    private Vector flatBedVector;
    private Saab95 saab95;

    @BeforeEach
    void testObject() {
        flatBedTruckM1Obj = new FlatBedTruckM1(Color.BLACK);
        MovementObj flatBedMovementObj = flatBedTruckM1Obj.getMovementObj();
        flatBedVector = flatBedMovementObj.getVector();
        flatBedPosition = flatBedTruckM1Obj.getMovementObj().getPosition();
        saab95 = new Saab95(Color.black);
    }

    @Test
    void testTurnLeft360degrees(){
        flatBedTruckM1Obj.turnLeft();
        flatBedTruckM1Obj.turnLeft();
        flatBedTruckM1Obj.turnLeft();
        flatBedTruckM1Obj.turnLeft();
        assertTrue(flatBedVector.getY() == 0 && flatBedVector.getX() == 1);
    }

    @Test
    void testTurnLeft270degrees(){
        flatBedTruckM1Obj.turnLeft();
        flatBedTruckM1Obj.turnLeft();
        flatBedTruckM1Obj.turnLeft();
        assertTrue(flatBedVector.getY() == -1 && flatBedVector.getX() == 0);
    }

    @Test
    void testMoveForwardXmToLeftXm(){
        flatBedTruckM1Obj.gas(0.5);
        flatBedTruckM1Obj.move();
        flatBedTruckM1Obj.turnLeft();
        flatBedTruckM1Obj.move();
        Assertions.assertEquals(flatBedPosition.getX(), flatBedPosition.getY());
    }

    /*
    @Test
    void loadFlatBed() {
        saabPosition.setX(5);
        saabPosition.setY(6);
        flatBedPosition.setY(5);
        flatBedPosition.setX(5);
        flatBedTruckObj.gas(0);
        assertTrue(flatBedTruckObj.isFlatBedAdjustable());
        assertTrue(flatBedTruckObj.isWithinRadius(saab95));
    }


    @Test
    void raiseFlatBed(){
        flatBedTruckObj.gas(1);
        assertFalse(flatBedTruckObj.isFlatBedAdjustable());
    }

    @Test
    void CarIsWithinRadius(){
        saabPosition.setX(5);
        saabPosition.setY(2);
        flatBedPosition.setY(10);
        flatBedPosition.setX(12);
        flatBedTruckObj.gas(0);
        assertFalse(flatBedTruckObj.isWithinRadius(saab95));
    }

     */
}