package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food, int percentExpiryDate) {
        boolean success = false;
        if (percentExpiryDate >= 25 && percentExpiryDate <= 75) {
            foods.add(food);
            success = true;
        } else if (percentExpiryDate > 75 && percentExpiryDate < 100) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
            foods.add(food);
            success = true;
        }
        return success;
    }
}
