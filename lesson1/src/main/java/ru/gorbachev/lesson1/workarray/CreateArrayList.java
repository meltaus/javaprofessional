package ru.gorbachev.lesson1.workarray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateArrayList<T extends Serializable> {

    List<T> resultList;

    public CreateArrayList(T[] arr) {
        this.resultList = new ArrayList<T>(Arrays.asList(arr));
    }

    public List<T> getResultList() {
        return resultList;
    }
}
