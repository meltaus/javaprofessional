package ru.gorbachev.lesson1;

import ru.gorbachev.lesson1.fruit.Apple;
import ru.gorbachev.lesson1.fruit.Box;
import ru.gorbachev.lesson1.fruit.Orange;
import ru.gorbachev.lesson1.workarray.ChangeElements;

public class MainLesson1 {

    public static void main(String[] args) {
        String[] integerArr = new String[20];
        for (int i = 0; i < 20; i++) {
            integerArr[i] = "Число " + i;
        }
        ChangeElements<String> changeElements = new ChangeElements(integerArr,4, 7);
        integerArr = changeElements.change();
        for (int i = 0; i < 20; i++) {
             System.out.print(integerArr[i] + "\t");
        }
        System.out.println("");

        Box<Apple> box1 = new Box(1);
        Box<Apple> box2 = new Box(1);
        Box<Orange> box3 = new Box((float) 1.5);
        Box<Orange> box4 = new Box((float) 1.5);

        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());

        box3.addFruit(new Orange());
        box3.addFruit(new Orange());
        box3.addFruit(new Orange());
        box3.addFruit(new Orange());

        box4.addFruit(new Orange());
        box4.addFruit(new Orange());
        box4.addFruit(new Orange());
        box4.addFruit(new Orange());
        box4.addFruit(new Orange());

        System.out.println(box1.getWeight(box1.getFruitOnBox(), box1.getWeightFruit()));

        System.out.println(box3.getWeight(box3.getFruitOnBox(), box3.getWeightFruit()));

        System.out.println(box1.compareBox(box3));

        box3.addBoxtoBox(box4);

        System.out.println(box3.getWeight(box3.getFruitOnBox(), box3.getWeightFruit()));

        System.out.println(box1.compareBox(box3));
    }
}
