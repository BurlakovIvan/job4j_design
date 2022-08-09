package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.stream.Collectors;

/*
В обьявление переменной, в параметрах метода goodsSorted и в возвращаемых методах
присутсвует реализация, а не абстракция
 */

public class Shop {
    private ArrayList<Food> goods;

    public Food buy(int indexGoods) {
        return goods.get(indexGoods);
    }

    public ArrayList<Food> goodsSorted(ArrayList<Food> foods) {
         return foods
                 .stream()
                 .sorted()
                 .collect(Collectors.toCollection(ArrayList::new));
    }
}
