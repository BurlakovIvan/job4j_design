package ru.job4j.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс парковки машин
 */
public class Parking implements CarParking {

    private int autoParkingSize;
    private int truckParkingSize;

    private List<Car> autoParking;
    private List<Car> truckParking;

    public Parking(int autoParkingSize, int truckParkingSize) {
        this.autoParkingSize = autoParkingSize;
        this.truckParkingSize = truckParkingSize;
        autoParking = new ArrayList<>(autoParkingSize);
        truckParking = new ArrayList<>(truckParkingSize);
    }

    @Override
    public boolean park(Car car) {
        /*
        метод парковки
         */
        return false;
    }

    @Override
    public List<Car> getParkedAutos() {
        return new ArrayList<>(autoParking);
    }

    @Override
    public List<Car> getParkedTrucks() {
        return new ArrayList<>(truckParking);
    }

    @Override
    public Car carLeavingParking(Car car) {
        return null;
    }
}
