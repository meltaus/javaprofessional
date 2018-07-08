package ru.gorabchev.lesson2.sqlitedb;

import org.sqlite.JDBC;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
}
