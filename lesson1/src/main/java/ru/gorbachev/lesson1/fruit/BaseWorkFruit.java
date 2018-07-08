package ru.gorbachev.lesson1.fruit;

import java.util.List;

public interface BaseWorkFruit<T extends Fruit> {

    //Установка веса фрукта
    void setWeight(float weight);
    //Получить вес всех фруктов
    void getWeight(List<T> fruitOnBox, float weight);
}
