package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private static final int TWENTY_FIVE = 25;
    private static final int SEVENTY_FIVE = 75;
    private static final int HUNDRED = 100;
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food) {
        boolean success = false;
        int percentExpiry = percentExpiryDate(food);
        if (percentExpiry >= TWENTY_FIVE && percentExpiry <= SEVENTY_FIVE) {
            foods.add(food);
            success = true;
        } else if (percentExpiry > SEVENTY_FIVE && percentExpiry < HUNDRED) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
            foods.add(food);
            success = true;
        }
        return success;
    }
}
