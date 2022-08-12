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

    private boolean validation(Car car) {
        if (car.getSize() == Auto.SIZE)  {
            return autoParkingSize > 0;
        }
        return truckParkingSize > 0 || autoParkingSize >= car.getSize();
    }

    @Override
    public boolean park(Car car) {
        if (!validation(car)) {
            return false;
        }
        if (car.getSize() == Auto.SIZE) {
            autoParking.add(car);
            autoParkingSize--;
            return true;
        }
        if (truckParkingSize > 0) {
            truckParking.add(car);
            truckParkingSize--;
            return true;
        }
        for (int i = 0; i < car.getSize(); i++) {
            autoParking.add(car);
        }
        autoParkingSize -= car.getSize();
        return true;
    }

    @Override
    public List<Car> getParkedAutos() {
        return new ArrayList<>(autoParking);
    }

    @Override
    public List<Car> getParkedTrucks() {
        return new ArrayList<>(truckParking);
    }

}
