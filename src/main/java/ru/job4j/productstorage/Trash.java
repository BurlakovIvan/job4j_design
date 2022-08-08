package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food, int percentExpiryDate) {
        boolean success = false;
        if (percentExpiryDate >= 100) {
            foods.add(food);
            success = true;
        }
        return success;
    }
}
