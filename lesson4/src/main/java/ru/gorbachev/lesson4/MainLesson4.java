package ru.gorbachev.lesson4;

import ru.gorbachev.lesson4.controller.PrintChar;

import java.util.Scanner;

public class MainLesson4 {
    public static void main(String[] args) {
        final Object msg = new Object();
        PrintChar printChar = new PrintChar(msg);
        printChar.start();
        try {
            Thread.sleep(200);
            printChar.pause();
            Thread.sleep(300);
            printChar.continuePrint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
