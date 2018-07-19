package ru.gorbachev.lesson4.controller;

import java.util.ArrayList;
import java.util.List;

public class PrintChar extends Thread{

    private final Object msg; //Монитор
    private final List<String> printChar;
    private int iteration;
    private boolean locked;

    public PrintChar(Object msg) {
        this.msg = msg;
        this.printChar = new ArrayList<>();
        iteration = 0;
        printChar.add("A");
        printChar.add("B");
        printChar.add("C");
        locked = true;
    }

    public void continuePrint() {
        this.msg.notify();
    }

    public void pause() {
        synchronized (msg) {
            try {
                this.msg.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public void run() {
        synchronized (msg) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(printChar.get(j));
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
