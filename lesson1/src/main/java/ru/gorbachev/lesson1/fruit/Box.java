package ru.gorbachev.lesson1.fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private float weightFruit;
    private List<T> fruitOnBox;

    public Box(float weightFruit) {
        this.weightFruit = weightFruit;
        this.fruitOnBox = new ArrayList<>();
    }

    public void addFruit(T fruit) {

        if (this.fruitOnBox.size() == 0) {
            this.fruitOnBox.add(fruit);
        } else if(this.fruitOnBox.get(0).getClass() == fruit.getClass()) {
            this.fruitOnBox.add(fruit);
        } else {
            System.out.println("Нельзя смешивать рукты");
        }
    }

    public List<T> getFruitOnBox() {
        return fruitOnBox;
    }

    public float getWeightFruit() {
        return weightFruit;
    }

    public float getWeight(List<T> fruitOnBox, float weight) {
        return fruitOnBox.size() * weight;
    }

    //Сравнивает вес коробок
    public boolean compareBox(Box box) {
        if (getWeight(fruitOnBox, weightFruit) != box.getWeight(box.getFruitOnBox(), box.getWeightFruit())) {
            return false;
        } else {
            return true;
        }
    }

    //Пересыпает фрукты из одной коробки в другую
    public Box addBoxtoBox(Box box) {
        if (this.fruitOnBox.get(0).getClass() == box.getFruitOnBox().get(0).getClass()) {
            this.fruitOnBox.addAll(box.getFruitOnBox());
            box.getFruitOnBox().removeAll(box.getFruitOnBox());
            return box;
        } else {
            System.out.println("Типа не совпадают");
            return box;
        }
    }
}
