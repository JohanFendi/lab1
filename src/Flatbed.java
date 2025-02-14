package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Flatbed<T> {

    private final Stack<T> storage = new Stack<>();
    private int maxCapacity;

    public Flatbed(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    public boolean loadObject(T obj){
        if (this.isFull()){
            return false;
        }
        this.storage.push(obj);
        return true;
    }

    public T unLoadObject(){
        if (this.storage.isEmpty()){
            return null;
        }
        return this.storage.pop();
    }

    public boolean isFull(){
        return this.storage.size() >= maxCapacity;
    }

    public List<T> getLoadedCars() {
        return new ArrayList<>(this.storage);
    }

    public int size(){
        return this.storage.size();
    }

}
