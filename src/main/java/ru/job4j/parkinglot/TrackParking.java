package ru.job4j.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * Парковка грузовых машин
 */
public class TrackParking implements CarParking {
    private int numberTrackParking;
    private List<Car> trackParking;

    public TrackParking(int numberTrackParking) {
        this.numberTrackParking = numberTrackParking;
        this.trackParking = new ArrayList<>(numberTrackParking);
    }

    @Override
    public void park(Car car) {
        /*
        Парковка грузовой машины
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
        return numberTrackParking;
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(trackParking);
    }

    @Override
    public int getFreeParkingPlace() {
        return 0;
    }

}
