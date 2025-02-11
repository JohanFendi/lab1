import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container c;
    private static final double MAX_VOLUME = 10;

    @BeforeEach
    void createNewContainer(){
        this.c = new Container(MAX_VOLUME);
    }

    @Test
    void availableUnloadedVolumeEqualsMaxVolume(){
        assertEquals(10.0, this.c.getAvailableVolume());
    }

    @Test
    void loading7unitsResultsIN3Available(){
        this.c.load(3);
        this.c.load(4);
        assertEquals(3, this.c.getAvailableVolume());
    }

    @Test
    void loading2MoreThanMaxGivesOverflow2(){
        this.c.load(7);
        double overflow = this.c.load(5);
        assertEquals(2, overflow);
    }

    @Test
    void loadingLessThanOverflowGives0Overflow(){
        double overflow = this.c.load(5);
        assertEquals(0, overflow);
    }

    @Test
    void unloadingWhileEmptyGives0Unloaded(){
        double unloaded = this.c.unload(100);
        assertEquals(0, unloaded);
    }

    @Test
    void unloadingMoreThanAvailableGivesCurrentVolume(){
        this.c.load(7);
        double unloaded = this.c.unload(1000);
        assertEquals(7, unloaded);
    }

    @Test
    void unloadingLessThanAvailableGivesCorrectAvailableVolume(){
        this.c.load(7);
        this.c.unload(3);
        assertEquals(4, this.c.getAvailableVolume());
    }
}