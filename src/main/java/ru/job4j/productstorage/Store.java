package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.List;

public interface Store {

    int TWENTY_FIVE = 25;
    int SEVENTY_FIVE = 75;
    int HUNDRED = 100;

    List<Food> findAll();

    boolean add(Food food);

    default int percentExpiryDate(Food food) {
        long differentCurrentDate = LocalDate.now().toEpochDay() - food.getCreateDate().toEpochDay();
        long differentFull = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
        return differentFull == 0 ? 100 : Math.round(differentCurrentDate * 100 / differentFull);
    }
}
