import java.util.HashMap;


public class CarRepairShop<T extends Car> {

    private int maxNumCars;
    private int currentNumCars = 0;
    private int latestCarId = 0; //Used to for creating id's for cars in the carMap
    private HashMap<Integer, T> carMap = new HashMap<>();

    public CarRepairShop(int maxNumCars){
        this.maxNumCars = maxNumCars;
    }

    //-1 is returned if adding was not added
    // Else the id of the car is returned
    public Integer addCar(T car){
        if (this.carMap.containsValue(car) || this.isFull()){
            return -1;
        }
        this.carMap.put(latestCarId, car);
        latestCarId ++;
        currentNumCars++;
        return latestCarId - 1;
    }

    //Returns null if the id is not in carMap
    //Else the car that the id points to in carMap is returned
    public T takeOutCar(Integer carId){
        if (!this.carMap.containsKey(carId)){
            return null;
        }

        T removedCar = this.carMap.get(carId);
        this.carMap.remove(carId);
        currentNumCars--;
        return removedCar;
    }

    private boolean isFull(){
        return this.currentNumCars >= this.maxNumCars;
    }

}
