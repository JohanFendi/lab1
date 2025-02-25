package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

import src.model.Car;
import src.model.FlatbedM1;
import src.model.Volvo240;


import static org.junit.jupiter.api.Assertions.*;

class FlatbedM1Test {

    private static final int FLATBED_MAX_CAPACITY = 10;
    private static final int FLATBED_IS_EMPTY = 0;
    private FlatbedM1<Volvo240> flatBed;
    private Volvo240 testVolvo;


    @BeforeEach
    void testObject() {
        this.flatBed = new FlatbedM1<>(FLATBED_MAX_CAPACITY);
        this.testVolvo = new Volvo240(Color.BLUE);
    }


    @Test
    public void flatBedLoadsObject() {
        Assertions.assertTrue(this.flatBed.loadObject(testVolvo));
    }


    @Test
    public void flatBedCannotLoadObjectWhileFull() {
        for (int i = 0; i < 10; i++) {
            this.flatBed.loadObject(testVolvo);
        }
        Assertions.assertEquals(this.flatBed.size(), FLATBED_MAX_CAPACITY);
        Assertions.assertFalse(this.flatBed.loadObject(testVolvo));
    }


    @Test
    public void flatBedUnloadsObject() {
        Assertions.assertEquals(this.flatBed.size(), FLATBED_IS_EMPTY);

        this.flatBed.loadObject(testVolvo);
        Assertions.assertEquals(this.flatBed.size(), 1);

        Car unloadedVolvo = this.flatBed.unLoadObject();
        Assertions.assertEquals(this.flatBed.size(), FLATBED_IS_EMPTY);

        assertSame(unloadedVolvo, testVolvo);
    }


    @Test
    void flatBedisFull() {
        for (int i = 0; i < 10; i++) {
            this.flatBed.loadObject(testVolvo);
        }
        assertTrue(this.flatBed.size() >= FLATBED_MAX_CAPACITY);
    }

    @Test
    void FlatBedStackCopyToIterableList() {
        for (int i = 0; i < 4; i++) {
            this.flatBed.loadObject(testVolvo);
        }
        Assertions.assertEquals(this.flatBed.size(), 4);

        List<Volvo240> carList = new ArrayList<>();
        assertEquals(0, carList.size());

        carList = this.flatBed.getLoadedCars();
        Assertions.assertEquals(this.flatBed.size(), carList.size());
    }

}