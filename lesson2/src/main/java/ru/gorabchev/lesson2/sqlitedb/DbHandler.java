package ru.gorabchev.lesson2.sqlitedb;

import org.sqlite.JDBC;
import ru.gorabchev.lesson2.essence.Product;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DbHandler {

    //Имя файла БД
    private static final String FILE_DB_NAME = "product.db";
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:"+FILE_DB_NAME;

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }


    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DbHandler() throws SQLException {
        if (new File(FILE_DB_NAME).isFile()) {
            new File(FILE_DB_NAME).delete();
        }
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
        try (Statement statement = this.connection.createStatement()) {
            String query = "CREATE TABLE `product` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    "`prodid` INTEGER NOT NULL UNIQUE, `title` TEXT, `cost` NUMERIC )";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void insertProduct(Product product) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try(PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO product (`prodid`, `title`, `cost`) " +
                        "VALUES (?, ?, ?)")) {
            statement.setObject(1, product.getProdid());
            statement.setObject(2, product.getTitle());
            statement.setObject(3, product.getCost());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получение строк
    public List<Product> selectWithWhere(final Map<String, String> where,
                                        final String concatenation, final String operator) {
        Long currentTime = System.currentTimeMillis();
        //Формируем запрос
        String query = "SELECT * FROM product WHERE ";
        for (Map.Entry entry : where.entrySet()) {
            query += "" + entry.getKey() + " " + operator + " '" + entry.getValue() + "' " + concatenation + " ";
        }
        query = removeChar(query);
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt("id"),
                        resultSet.getInt("prodid"),
                        resultSet.getString("title"),
                        resultSet.getFloat("cost")));
            }

            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private String removeChar(final String query) {
        if (query.contains(" or ")) {
            return query.substring(0,query.length()-3);
        } else if (query.contains(" and ")) {
            return query.substring(0,query.length()-4);
        }
        if (query.contains(".db")) {
            return query.substring(0,query.length()-3);
        }
        return query;
    }

    public void updateWithWhere(final String columnName, final Map<String, String> where,
                                final String concatenation, String value){
        Long currentTime = System.currentTimeMillis();
        //Формируем запрос
        try (Statement statement = this.connection.createStatement()) {
            String query = "UPDATE product SET " + columnName + " = " + value + " WHERE ";
            for (Map.Entry entry : where.entrySet()) {
                query += "" + entry.getKey() + " = '" + entry.getValue() + "' " + concatenation + " ";
            }
            query = removeChar(query);
//            System.out.println(query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBigData(List<Product> products) {
        System.out.println("Начало вставки");
        try (PreparedStatement statement = this.connection.prepareStatement("BEGIN TRANSACTION")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Product product : products) {
            insertProduct(product);
        }

        try (PreparedStatement statement = this.connection.prepareStatement("COMMIT")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Конец вставки");
    }
}
