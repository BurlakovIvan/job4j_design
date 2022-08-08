package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food, int percentExpiryDate) {
        boolean success = false;
        if (percentExpiryDate < 25) {
            foods.add(food);
            success = true;
        }
        return success;
    }
}
