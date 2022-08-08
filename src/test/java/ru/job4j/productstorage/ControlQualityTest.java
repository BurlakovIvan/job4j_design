package ru.job4j.productstorage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenFoodFromTrash() {
        Food milk = new Milk("milk", LocalDate.of(2022, 8, 8),
                LocalDate.of(2022, 7, 28), 150, 10);
        Food meat = new Meat("meat", LocalDate.of(2022, 8, 1),
                LocalDate.of(2022, 1, 1), 500, 15);
        Food bread = new Bread("bread", LocalDate.of(2022, 8, 4),
                LocalDate.of(2022, 7, 30), 40, 5);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        List<Food> actual = List.of(milk, meat, bread);
        control.redistribution(actual);
        List<Food> expected = List.of(milk, meat, bread);
        assertThat(trash.findAll()).isEqualTo(expected);
    }

    @Test
    public void whenFoodFromWarehouse() {
        Food milk = new Milk("milk", LocalDate.of(2022, 9, 30),
                LocalDate.of(2022, 8, 6), 150, 10);
        Food meat = new Meat("meat", LocalDate.of(2022, 8, 1),
                LocalDate.of(2022, 1, 1), 500, 15);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        List<Food> actual = List.of(milk, meat);
        control.redistribution(actual);
        List<Food> expected = List.of(milk);
        assertThat(warehouse.findAll()).isEqualTo(expected);
    }

    @Test
    public void whenFoodFromShopMore75() {
        Food milk = new Milk("milk", LocalDate.of(2022, 8, 10),
                LocalDate.of(2022, 7, 28), 150, 10);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        List<Food> actual = List.of(milk);
        control.redistribution(actual);
        assertThat(shop.findAll().get(0).getPrice()).isEqualTo(135);
    }

    @Test
    public void whenFoodFromShop() {
        Food milk = new Milk("milk", LocalDate.of(2022, 8, 20),
                LocalDate.of(2022, 7, 28), 150, 10);
        Food meat = new Meat("meat", LocalDate.of(2022, 8, 1),
                LocalDate.of(2022, 1, 1), 500, 15);
        Food bread = new Bread("bread", LocalDate.of(2022, 8, 4),
                LocalDate.of(2022, 7, 30), 40, 5);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        List<Food> actual = List.of(milk, meat, bread);
        control.redistribution(actual);
        List<Food> expected = List.of(milk);
        assertThat(shop.findAll()).isEqualTo(expected);
    }
}