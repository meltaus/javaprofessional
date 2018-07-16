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
        msg.notify();
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    public void run() {
        synchronized (msg) {
            for (int i = 0; i < 15; i++) {
                    if (this.iteration == 3) {
                        this.iteration = 0;
                        try {
                            locked = false;
                            msg.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(printChar.get(this.iteration));
                    this.iteration++;
            }
        }
    }
}
