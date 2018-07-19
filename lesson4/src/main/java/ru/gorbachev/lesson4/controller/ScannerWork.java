package ru.gorbachev.lesson4.controller;

public class ScannerWork extends Thread {

    final int working;
    final Object locked;

    public ScannerWork(final int working, final  Object locked) {
        this.working = working;
        this.locked = locked;
    }

    @Override
    public void run() {
        synchronized (locked) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Сканирование страницы " + i + ", Задание №" + working);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
