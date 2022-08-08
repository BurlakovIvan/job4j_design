package ru.job4j.parkinglot;

import java.util.Objects;

/**
 * Легковые машины
 */
public class Auto implements Car {

    private final String name;
    private final int size;

    public Auto(String name) {
        this.size = 1;
        this.name = name;
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
        Auto auto = (Auto) o;
        return size == auto.size && Objects.equals(name, auto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
