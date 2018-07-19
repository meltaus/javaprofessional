package ru.gorbachev.lesson4;

import ru.gorbachev.lesson4.controller.PrintChar;
import ru.gorbachev.lesson4.controller.PrinterWork;
import ru.gorbachev.lesson4.controller.ScannerWork;
import ru.gorbachev.lesson4.controller.WriteInFile;

import java.util.ArrayList;
import java.util.List;


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

        final Object lockedPrint = new Object();
        final Object lockedScan = new Object();

        List<PrinterWork> printerWorkList = new ArrayList<>();
        List<ScannerWork> scannerWorkList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            printerWorkList.add(new PrinterWork(i, lockedPrint));
            scannerWorkList.add(new ScannerWork(i, lockedScan));
        }

        for (int i = 0; i < 4; i++) {
            printerWorkList.get(i).start();
            scannerWorkList.get(i).start();
        }
    }
}
