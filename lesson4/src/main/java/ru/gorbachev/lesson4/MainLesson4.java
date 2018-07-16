package ru.gorbachev.lesson4;

import ru.gorbachev.lesson4.controller.PrintChar;

public class MainLesson4 {
    public static void main(String[] args) {
        final Object msg = new Object();
        PrintChar printChar = new PrintChar(msg);
        printChar.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (msg) {
                    for (int i = 0; i < 4; i++) {
                        if (printChar.isAlive());
                        try {
                            while (printChar.isLocked()) {
                                Thread.sleep(500);
                            }
                            msg.notify();
                            System.out.print(i);
                            printChar.setLocked(true);
                            System.out.print(printChar.isLocked());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
