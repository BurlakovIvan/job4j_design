package ru.job4j.parkinglot;

import java.util.Objects;

/**
 * Грузовые машины
 */
public class Track implements Car {
    private final String name;
    private final int size;

    public Track(String name, int size) {
        if (size < 2) {
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
        Track track = (Track) o;
        return size == track.size && Objects.equals(name, track.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
