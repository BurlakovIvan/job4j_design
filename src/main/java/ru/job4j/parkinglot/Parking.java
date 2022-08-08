package ru.job4j.parkinglot;

import java.util.List;

/**
 * Класс парковки машин
 */
public class Parking {

    List<CarParking> parkingPlace;

    public Parking(List<CarParking> parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    void parkingCar(List<Car> cars) {
       /*
       Здесь будем парковать машины на паркову
        */
    }

    Car carLeftParking(Car car) {
        /*
        Машина уехала с парковки
         */
        return null;
    }
}
