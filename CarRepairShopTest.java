import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class CarRepairShopTest {


    @Test
    void addingCarTwiceReturnsMinusOne(){
        CarRepairShop<Car> shop = new CarRepairShop<>(10);
        Car car = new Volvo240(Color.black);
        int id = shop.addCar(car);
        id = shop.addCar(car);
        assertEquals(id, -1);
    }

    @Test
    void adding2CarsTwoRepairShopWithMaxOneCarReturnsMinusOne(){
        CarRepairShop<Car> shop = new CarRepairShop<>(1);
        Car car1 = new Volvo240(Color.black);
        Car car2 = new Volvo240(Color.black);
        int id1 = shop.addCar(car1);
        int id2 = shop.addCar(car2);
        assertEquals(id2, -1);
    }

    @Test
    void adding1CarAndTakingOutCarWorks(){
        CarRepairShop<Car> shop = new CarRepairShop<>(4);
        Car preAddingToShopCar = new Volvo240(Color.black);
        int id = shop.addCar(preAddingToShopCar);
        Car postTakingOutFromShopCar = shop.takeOutCar(id);
        assertEquals(preAddingToShopCar, postTakingOutFromShopCar);
    }

    @Test //Keep in mind that id starts at 0.
    void adding4CarsRemoving1AddingOneMoreGivesId4(){
        CarRepairShop<Car> shop = new CarRepairShop<>(100);
        Car car1 = new Volvo240(Color.black);
        Car car2 = new Volvo240(Color.black);
        Car car3 = new Volvo240(Color.black);
        Car car4 = new Volvo240(Color.black);
        Car car5 = new Volvo240(Color.black);
        shop.addCar(car1);
        shop.addCar(car2);
        shop.addCar(car3);
        int id = shop.addCar(car4);
        shop.takeOutCar(id);
        int idCar5 = shop.addCar(car5);
        assertEquals(4, idCar5);
    }








}