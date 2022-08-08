package ru.job4j.parkinglot;

import java.util.List;

/**
 * Общий интерфейс парковки
 */
public interface CarParking {
    void park(Car car);

    int getNumberParkingPlace();

    List<Car> findAll();

    int getFreeParkingPlace();

    Car carLeftParking(Car car);
}
