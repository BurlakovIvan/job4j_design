package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.Objects;

public class Food {

    protected String name;
    protected LocalDate expiryDate;
    protected LocalDate createDate;
    protected double price;
    protected double discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 && Double.compare(food.discount, discount) == 0 && Objects.equals(name, food.name) && Objects.equals(expiryDate, food.expiryDate) && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }
}
