package ru.job4j.productstorage;

import java.util.List;

public class ControlQuality {

    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void redistribution(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.add(food)) {
                    break;
                }
            }
        }
    }
}
