package ru.job4j.ood.isp;

/*
Не все птицы могут летать, плавать или бегать, соответсвенно некоторые методы у них не будут использоваться
 */
public interface Bird {

    void fly();
    void swim();
    void run();
}
