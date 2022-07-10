package ru.job4j.serialization.java;

import java.util.Arrays;

public class User {

    private final boolean access;
    private final String username;
    private final int age;
    private final Address address;
    private final String[] information;

    public User(String name, boolean access, int age, Address address, String[] information) {
        this.username = name;
        this.access = access;
        this.age = age;
        this.address = address;
        this.information = information;
    }

    @Override
    public String toString() {
        return "User{"
                + "access=" + access
                + ", username='" + username + '\''
                + ", age=" + age
                + ", address=" + address
                + ", information=" + Arrays.toString(information)
                + '}';
    }
}
