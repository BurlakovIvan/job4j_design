package ru.job4j.productstorage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenFoodFromTrash() {
        Food milk = new Milk("milk", LocalDate.now(),
                LocalDate.now().minusDays(10), 150, 10);
        Food meat = new Meat("meat", LocalDate.now().minusDays(8),
                LocalDate.now().minusMonths(8), 500, 15);
        Food bread = new Bread("bread", LocalDate.now().minusDays(4),
                LocalDate.now().minusDays(12), 40, 5);
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
        Food milk = new Milk("milk", LocalDate.now().plusDays(40),
                LocalDate.now().minusDays(2), 150, 10);
        Food meat = new Meat("meat", LocalDate.now().minusDays(8),
                LocalDate.now().minusMonths(8), 500, 15);
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
        Food milk = new Milk("milk", LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(11), 150, 10);
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
        Food milk = new Milk("milk", LocalDate.now().plusDays(12),
                LocalDate.now().minusDays(12), 150, 10);
        Food meat = new Meat("meat", LocalDate.now().minusDays(8),
                LocalDate.now().minusMonths(8), 500, 15);
        Food bread = new Bread("bread", LocalDate.now().minusDays(4),
                LocalDate.now().minusDays(10), 40, 5);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        List<Food> actual = List.of(milk, meat, bread);
        control.redistribution(actual);
        List<Food> expected = List.of(milk);
        assertThat(shop.findAll()).isEqualTo(expected);
    }

    @Test
    public void whenFoodDifferentStore() {
        Food milk = new Milk("milk", LocalDate.now().plusDays(12),
                LocalDate.now().minusDays(12), 150, 10);
        Food meat = new Meat("meat", LocalDate.now().minusDays(8),
                LocalDate.now().minusMonths(8), 500, 15);
        Food bread = new Bread("bread", LocalDate.now().plusDays(8),
                LocalDate.now().minusDays(1), 40, 5);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(warehouse, shop, trash));
        List<Food> actual = List.of(milk, meat, bread);
        control.redistribution(actual);
        assertThat(shop.findAll()).isEqualTo(List.of(milk));
        assertThat(trash.findAll()).isEqualTo(List.of(meat));
        assertThat(warehouse.findAll()).isEqualTo(List.of(bread));
    }
}