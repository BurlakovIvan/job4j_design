package ru.job4j.parkinglot;

import java.util.Objects;

/**
 * Легковые машины
 */
public class Auto implements Car {

    private final String name;
    public static final int SIZE = 1;

    public Auto(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        return SIZE;
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
        return Objects.equals(name, auto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
