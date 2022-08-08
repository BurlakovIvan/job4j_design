package ru.job4j.productstorage;

import java.util.List;

public interface Store {

    List<Food> findAll();

    boolean add(Food food, int percentExpiryDate);
}
