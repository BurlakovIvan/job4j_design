package ru.job4j.ood.isp;

/*
Не все рестораны предлагают доставку еды,
поэтому этот метод придется глушить
 */
public interface Restaurant {

    void cook();
    void delivery();
}
