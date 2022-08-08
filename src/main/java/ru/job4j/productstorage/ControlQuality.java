package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    private List<Store> stores;
    LocalDate now;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
        this.now = LocalDate.now();
    }

    public void redistribution(List<Food> foods) {
        for (Food food : foods) {
            long differentCurrentDate = now.toEpochDay() - food.getCreateDate().toEpochDay();
            long differentFull = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
            int percentExpiryDate = differentFull == 0 ? 100 : Math.round(differentCurrentDate * 100 / differentFull);
            for (Store store : stores) {
                if (store.add(food, percentExpiryDate)) {
                    break;
                }
            }
        }
    }
}
