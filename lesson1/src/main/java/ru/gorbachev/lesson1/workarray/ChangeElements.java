package ru.gorbachev.lesson1.workarray;

import java.io.Serializable;

public class ChangeElements<T extends Serializable> {

    int element1;
    int element2;
    T[] arr;

    //В конструктор передаются ндексы элементо, которые неободимо поменять
    public ChangeElements(T[] arr, int element1, int element2) {
        this.arr = arr;
        int count = arr.length;
        if (element1 > count) {
            element1 = count;
            System.out.print("Индекс 1 выходит за переделы массива. Присвоено значение крайнего элемента");
        }
        if (element2 > count) {
            element2 = count;
            System.out.print("Индекс 2 выходит за переделы массива. Присвоено значение крайнего элемента");
        }
        this.element1 = element1;
        this.element2 = element2;
    }

    public T[] change(T[] arr) {
        T tmpElement = arr[element1];
        arr[element1]= arr[element2];
        arr[element2] = tmpElement;
        return arr;
    }
}
