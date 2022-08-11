package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;
/*
Нарушения принципа DIP:
1. В поле autoParking, зависим от релизации, а не от абстракции
2. Зависим от класса Truck, лучше переделать на абстракцию, например на интерфейс Car
3. Напрямую зависим от консольного вывода в методе park
 */

class Truck {

}

public class CarParking {
    private int size;

    private List<Truck> autoParking;

    public CarParking(int size) {
        this.size = size;
        autoParking = new ArrayList<>(size);
    }

    public boolean park(Truck truck) {
        if (size == autoParking.size()) {
            System.out.println("Нет места на парковке");
            return false;
        }
        autoParking.add(truck);
        System.out.println("Машина припарковалась");
        return true;
    }
}
