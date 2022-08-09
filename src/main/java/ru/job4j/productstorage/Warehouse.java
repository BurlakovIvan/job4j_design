package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private static final int TWENTY_FIVE = 25;
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food) {
        boolean success = false;
        if (percentExpiryDate(food) < TWENTY_FIVE) {
            foods.add(food);
            success = true;
        }
        return success;
    }
}
