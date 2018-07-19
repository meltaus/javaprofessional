package ru.gorbachev.lesson4.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteInFile {

    private BufferedWriter writer;
    private String lineSeparator;
    private boolean trigger;

    public WriteInFile() {
        try {
            writer = new BufferedWriter(new FileWriter("file.txt", true));
            lineSeparator = System.getProperty("line.separator");
            trigger = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread1 = new Thread(() -> writeFile());
        Thread thread2 = new Thread(() -> writeFile());
        Thread thread3 = new Thread(() -> writeFile());
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(500);
            trigger = false;
            Thread.sleep(1000);
            writer.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        while (trigger) {
            synchronized (writer) {
                for (int i = 0; i < 10; i++) {
                    try {
                        writer.write(i + lineSeparator);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
