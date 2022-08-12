package ru.job4j.parkinglot;

import java.util.List;

/**
 * Общий интерфейс парковки
 */
public interface CarParking {
    boolean park(Car car);

    List<Car> getParkedAutos();

    List<Car> getParkedTrucks();
}
