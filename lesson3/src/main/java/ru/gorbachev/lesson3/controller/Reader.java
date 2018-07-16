package ru.gorbachev.lesson3.controller;

import sun.plugin2.message.Message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader extends Thread {

    private final String sourceFile;

    private final Object msg; //Монитор

    public Reader(Object msg) {
        ClassLoader classLoader = getClass().getClassLoader();
        this.sourceFile = classLoader.getResource("book.txt").getPath();
        this.msg = msg;
    }

    public void continueRead() {
        msg.notifyAll();
    }

    //Выводим на печать одну страницу и останавливаем поток. Ждем запуска
    public void run() {
        int countChar = 0;
        List<String> page = new ArrayList<>();
        BufferedReader reader = null;
        synchronized (msg) {
            try {
                reader = new BufferedReader(new FileReader(this.sourceFile));
                String line = "";
                while (line != null) {
                    line = reader.readLine();
                    countChar += line.length();
                    if (countChar < 1800) {
                        page.add(line);
                    } else {
                        for (String str : page) {
                            System.out.println(str);
                        }
                        page.removeAll(page);
                        page.add(line);
                        countChar = line.length();
                        msg.wait();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("Ошибка выполнения потока");
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
