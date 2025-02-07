import java.util.ArrayList;

public class Flak <Car> {

    private ArrayList<Car> flak;
    private final int FLAK_MAXCAPACITY = 10;
    private boolean isFlakUp = false;

    public Flak() {
        this.flak = new ArrayList<Car>(FLAK_MAXCAPACITY);
    }



    public boolean isFlakUp() {
        return this.isFlakUp;
    }

    public void setFlakUp() {
        this.isFlakUp = true;
    }

    public void setFlakDown() {
        this.isFlakUp = false;
        // TODO biltransporten måste stå stilla för att sänka den
    }



/*
    private boolean isLoadable;

    abstract protected void adjustRamp(double angle);

    abstract protected void LoadTruck(T x);

    abstract protected T unLoadTruck();

 */






}
