package ru.gorbachev.lesson4;

import ru.gorbachev.lesson4.controller.PrintChar;
import ru.gorbachev.lesson4.controller.WriteInFile;


public class MainLesson4 {
    public static void main(String[] args) {
        final Object msg = new Object();
        PrintChar printChar = new PrintChar(msg);
        printChar.start();
        try {
            Thread.sleep(1000);
            printChar.continuePrint();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WriteInFile writeInFile = new WriteInFile();
    }
}
