package ru.job4j.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * Парковак легковых и/или грузовых машин
 */
public class AutoParking implements CarParking {

    private int numberAutoParking;

    private List<Car> autoParking;


    public AutoParking(int numberAutoParking) {
        this.numberAutoParking = numberAutoParking;
        this.autoParking = new ArrayList<>(numberAutoParking);
    }

    @Override
    public void park(Car car) {
        /*
        Парковка легковой или грузовой машины
         */
    }

    @Override
    public Car carLeftParking(Car car) {
        /*
        Машина уехала с парковки
         */
        return null;
    }

    @Override
    public int getNumberParkingPlace() {
        return numberAutoParking;
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(autoParking);
    }

    @Override
    public int getFreeParkingPlace() {
        return 0;
    }
}
