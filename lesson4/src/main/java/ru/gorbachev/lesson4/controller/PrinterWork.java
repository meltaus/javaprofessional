package ru.gorbachev.lesson4.controller;

public class PrinterWork extends Thread {

    final int working;
    final Object locked;

    public PrinterWork(final int working, final  Object locked) {
        this.working = working;
        this.locked = locked;
    }

    @Override
    public void run() {
        synchronized (locked) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Печать страницы " + i + ", Задание №" + working);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
