package ru.gorabchev.lesson2;

import ru.gorabchev.lesson2.essence.Product;
import ru.gorabchev.lesson2.sqlitedb.DbHandler;

import java.sql.SQLException;
import java.util.*;
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

        createProduct();

        consoleMenu();
    }

    //Консольное меню приложения
    private static void consoleMenu() {
        int numberMenu = -1;
        while (numberMenu != 0) {
            System.out.println("Введите комманду: ");
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            if (str.contains("/Выход")) {
                numberMenu = 0;
            } else if (str.contains("/цена")) {
                numberMenu = 1;
            } else if (str.contains("/сменитьцену")) {
                numberMenu = 2;
            } else if (str.contains("/товарыпоцене ")) {
                numberMenu = 3;
            }

            switch (numberMenu) {
                case 1:
                    str = str.replace("/цена ","");
                    if (!"".equals(str)) {
                        Map<String, String> where = new HashMap<>();
                        where.put("title", str);
                        List<Product> productList = dbHandler.selectWithWhere(where, "and", "=");
                        System.out.println("Цена запрошенного товара: " + productList.get(0).getCost());
                    }
                    break;

                case 2:
                    str = str.replace("/сменитьцену ","");
                    if (!"".equals(str)) {
                        Map<String, String> where = new HashMap<>();
                        List<Product> productList;
                        String[] elements = str.split(" ");
                        where.put("title", elements[0]);
                        System.out.print("Старое значение:\t");
                        productList = dbHandler.selectWithWhere(where, "and", "=");
                        System.out.println(productList.get(0).toString());
                        dbHandler.updateWithWhere("cost", where, "and", elements[1]);
                        System.out.print("Новое значение:\t");
                        productList = dbHandler.selectWithWhere(where, "and", "=");
                        System.out.println(productList.get(0).toString());
                    }
                    break;

                case 3:
                    str = str.replace("/товарыпоцене ","");
                    if (!"".equals(str)) {
                        Map<String, String> where = new HashMap<>();
                        where.put("cost", str);
                        List<Product> productList = dbHandler.selectWithWhere(where, "and", "=");
                        System.out.println("Товары, которые соответствуют указанной цене: ");
                        for (Product product : productList) {
                            System.out.println(product.toString());
                        }
                    }
                    break;
            }
        }
    }

    //Создаем в БД требуемое количество записей
    private static void createProduct() {
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
