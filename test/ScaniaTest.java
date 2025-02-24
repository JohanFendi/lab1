package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

import src.model.Position;
import src.model.Scania;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {


    private Scania scaniaObj;


    @BeforeEach
    void testObject() {
        scaniaObj = new Scania(Color.BLACK, 10);
    }


    @Test
    void adjustRampAngleOver70Degrees() {
        scaniaObj.adjustRamp(100);
        Assertions.assertEquals(70, scaniaObj.getRampAngle());
    }

    @Test
    void adjustRampAngleUnder0Degrees() {
        scaniaObj.adjustRamp(-10);
        Assertions.assertEquals(0, scaniaObj.getRampAngle());
    }


    @Test
    void adjustRampScaniaNotMoving() {
        Assertions.assertTrue(scaniaObj.adjustRamp(10));
    }


    @Test
    void adjustRampScaniaIsMoving() {
        scaniaObj.gas(0.5);
        Assertions.assertFalse(scaniaObj.adjustRamp(10));

        // Kollar så att vinkeln på rampen inte har ändrats
        Assertions.assertEquals(0.0, scaniaObj.getRampAngle(), 1e-10);
    }


    @Test
    void move() {
        scaniaObj.move();
    }

    @Test
    void notMoveWhileRampIsUp() {
        Position beforeMove = scaniaObj.getMovementObj().getPosition();
        scaniaObj.adjustRamp(40);
        scaniaObj.move();
        Assertions.assertEquals(beforeMove, scaniaObj.getMovementObj().getPosition());
    }

    @Test
    void cannotGasWhileRampIsUp() {
        scaniaObj.adjustRamp(50);
        assertThrows(IllegalArgumentException.class, () -> scaniaObj.gas(10));
    }


    @Test
    void getRampAngle() {
        scaniaObj.adjustRamp(60);
        Assertions.assertEquals(60, scaniaObj.getRampAngle());
    }


}