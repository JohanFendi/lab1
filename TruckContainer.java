import java.util.ArrayList;

public abstract class TruckContainer<T> {

    private final int maxRaise;

    private final int minRaise;

    private int level;

    private final ArrayList<T> amount = new ArrayList<T>();

    public TruckContainer(int minRaise, int maxRaise, int level) {
        this.minRaise = minRaise;
        this.maxRaise = maxRaise;
        this.level = level;
    }

    boolean isLoadable() {
        return (level == minRaise);
    }

    void raiseContainer() {
        if (level < maxRaise) {
            level += 1;
        }
    }

    void lowerContainer() {
        if (level > minRaise) {
            level -= 1;
        }
    }

    void loadTruck(T x) {
        amount.add(x);
    }

    T unLoadTruck() {
        return amount.removeLast();
    };

}