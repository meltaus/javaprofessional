package ru.gorabchev.lesson2;

import ru.gorabchev.lesson2.essence.Product;
import ru.gorabchev.lesson2.sqlitedb.DbHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class MainLesson2 {
    private static CopyOnWriteArraySet<Integer> prodIdSet;
    private static List<Product> productList;
    private static DbHandler dbHandler;

    public static void main(String[] args) {
        prodIdSet = new CopyOnWriteArraySet<>();
        productList = new ArrayList<>();

        try {
            dbHandler = DbHandler.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Thread genProdId1 = new Thread(() -> generateProdId());
        Thread genProdId2 = new Thread(() -> generateProdId());
        genProdId1.start();
        genProdId2.start();

        while (true) {
            if (prodIdSet.size() == 10000) {
                break;
            }
        }

        int iter = 0;
        int a = 1; // Начальное значение диапазона - "от"
        int b = 1000; // Конечное значение диапазона - "до"
        for (Integer prodId : prodIdSet) {
            productList.add(new Product(prodId, "товар"+iter, a + (int) (Math.random() * b)));
            iter++;
        }

        dbHandler.insertBigData(productList);
    }

    private static void generateProdId() {
        int a = 1; // Начальное значение диапазона - "от"
        int b = 1000000; // Конечное значение диапазона - "до"
        while (prodIdSet.size() < 10000-1) {
            prodIdSet.add(a + (int) (Math.random() * b));
        }
    }
}
