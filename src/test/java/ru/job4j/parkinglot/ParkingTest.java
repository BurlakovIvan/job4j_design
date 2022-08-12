package ru.job4j.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {

    @Test
    public void whenTwoCarAndOneTruck() {
        Parking parking = new Parking(2, 1);
        Car lada = new Auto("LADA");
        Car toyota = new Auto("TOYOTA");
        Car kamaz = new Truck("KAMAZ", 4);
        parking.park(lada);
        parking.park(toyota);
        parking.park(kamaz);
        assertThat(parking.getParkedAutos()).isEqualTo(List.of(lada, toyota));
        assertThat(parking.getParkedTrucks()).isEqualTo(List.of(kamaz));
    }

    @Test
    public void whenNotPlace() {
        Parking parking = new Parking(2, 1);
        Car lada = new Auto("LADA");
        Car toyota = new Auto("TOYOTA");
        Car kamaz = new Truck("KAMAZ", 4);
        Car bmv = new Auto("BMV");
        parking.park(lada);
        parking.park(toyota);
        parking.park(kamaz);
        assertFalse(parking.park(bmv));
    }

    @Test
    public void whenTwoTrucks() {
        Parking parking = new Parking(2, 1);
        Car kamaz = new Truck("KAMAZ", 4);
        Car maz = new Truck("MAZ", 2);
        assertTrue(parking.park(kamaz));
        assertTrue(parking.park(maz));
        assertThat(parking.getParkedAutos()).isEqualTo(List.of(maz, maz));
    }

    @Test
    public void whenNotPlaceTruck() {
        Parking parking = new Parking(2, 1);
        Car kamaz = new Truck("KAMAZ", 4);
        Car maz = new Truck("MAZ", 3);
        assertTrue(parking.park(kamaz));
        assertFalse(parking.park(maz));
    }

    @Test
    public void whenTruckOnAutoPlaceAndAuto() {
        Parking parking = new Parking(4, 1);
        Car kamaz = new Truck("KAMAZ", 4);
        Car maz = new Truck("MAZ", 3);
        Car lada = new Auto("LADA");
        parking.park(kamaz);
        parking.park(maz);
        parking.park(lada);
        assertThat(parking.getParkedAutos()).isEqualTo(List.of(maz, maz, maz, lada));
    }

    @Test
    public void whenNotPlaceForAuto() {
        Parking parking = new Parking(2, 1);
        Car toyota = new Auto("TOYOTA");
        Car bmv = new Auto("BMV");
        Car lada = new Auto("LADA");
        assertTrue(parking.park(toyota));
        assertTrue(parking.park(bmv));
        assertFalse(parking.park(lada));
    }
}