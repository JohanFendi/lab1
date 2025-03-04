package src;

import src.model.Position;

import java.util.ArrayList;

public interface Observable {
    void addListener(ModelListener listener);

    ArrayList<Position> getObjectPositions();

    void notifyListeners();

    void update();
}
