package src.model;

import java.util.List;

public interface FlatBed<T> {
    boolean loadObject(T obj);
    T unLoadObject();
    boolean isFull();
    List<T> getLoadedCars();
    int size();
}
