package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private static final int HUNDRED = 100;
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food) {
        boolean success = false;
        if (percentExpiryDate(food) >= HUNDRED) {
            foods.add(food);
            success = true;
        }
        return success;
    }
}
