package ru.job4j.parkinglot;

import java.util.Objects;

/**
 * Грузовые машины
 */
public class Truck implements Car {
    private final String name;
    private final int size;

    public Truck(String name, int size) {
        if (size <= Auto.SIZE) {
            throw new IllegalArgumentException("Это не грузовая машина");
        }
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size && Objects.equals(name, truck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
