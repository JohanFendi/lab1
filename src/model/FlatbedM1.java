package src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FlatbedM1<T> implements FlatBed<T>{

    private final Stack<T> storage = new Stack<>();
    private int maxCapacity;

    public FlatbedM1(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean loadObject(T obj){
        if (this.isFull()){
            return false;
        }
        this.storage.push(obj);
        return true;
    }

    @Override
    public T unLoadObject(){
        if (this.storage.isEmpty()){
            return null;
        }
        return this.storage.pop();
    }
    @Override
    public boolean isFull(){
        return this.storage.size() >= maxCapacity;
    }

    @Override
    public List<T> getLoadedCars() {
        return new ArrayList<>(this.storage);
    }

    @Override
    public int size(){
        return this.storage.size();
    }

}
