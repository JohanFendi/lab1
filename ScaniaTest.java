import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
        assertEquals(70, scaniaObj.getRampAngle());
    }

    @Test
    void adjustRampAngleUnder0Degrees() {
        scaniaObj.adjustRamp(-10);
        assertEquals(0, scaniaObj.getRampAngle());
    }


    @Test
    void adjustRampScaniaNotMoving() {
        assertTrue(scaniaObj.adjustRamp(10));
    }


    @Test
    void adjustRampScaniaIsMoving() {
        scaniaObj.gas(0.5);
        assertFalse(scaniaObj.adjustRamp(10));

        // Kollar så att vinkeln på rampen inte har ändrats
        assertEquals(0.0, scaniaObj.getRampAngle(), 1e-10);
    }


    @Test
    void move() {
        scaniaObj.move();
    }

    @Test
    void notMoveWhileRampIsUp() {
        Position beforeMove = scaniaObj.getPosition();
        scaniaObj.adjustRamp(40);
        scaniaObj.move();
        assertEquals(beforeMove, scaniaObj.getPosition());
    }

    @Test
    void cannotGasWhileRampIsUp() {
        scaniaObj.adjustRamp(50);
        assertThrows(IllegalArgumentException.class, () -> scaniaObj.gas(10));
    }


    @Test
    void getRampAngle() {
        scaniaObj.adjustRamp(60);
        assertEquals(60, scaniaObj.getRampAngle());
    }


}