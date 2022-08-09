package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food {

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
}
