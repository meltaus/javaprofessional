package ru.gorbachev.lesson3;

import ru.gorbachev.lesson3.controller.ReadFile;
import ru.gorbachev.lesson3.controller.Reader;

import java.util.Scanner;

public class MainLesson3 {

    static ReadFile readFile;

    public static void main(String[] args) {
        readFile = new ReadFile();

        consoleMenu();
    }

    private static void consoleMenu() {
        int numberMenu = -1;
        while (numberMenu != 0) {
            System.out.println("Введите комманду: ");
            System.out.println("Прочитать 50 байт из файла: /readbyte\n" +
                    "Создать 5 файлов и соединить их: /concatinationfile\n" +
                    "Открыть книгу: /openbook\n" +
                    "Выход: /exit");
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            if (str.contains("/exit")) {
                numberMenu = 0;
            } else if (str.contains("/readbyte")) {
                numberMenu = 1;
            } else if (str.contains("/concatinationfile")) {
                numberMenu = 2;
            } else if (str.contains("/openbook")) {
                numberMenu = 3;
            }

            switch (numberMenu) {
                case 1:
                    readFile.readByteFile(50);
                    break;

                case 2:
                    readFile.concatinationFile(100, "result.txt");
                    break;

                case 3:
                    bookReaderMenu();
                    break;
            }
        }
    }

    private static void bookReaderMenu() {
        int numberMenu = -1;
        System.out.println("Введите комманду: ");
        System.out.println("Следующая страница: /next\n" +
                "Открыть книгу: /open\n" +
                "Выход: /exit");
        final Object msg = new Object();
        Reader reader = new Reader(msg);
        while (numberMenu != 0) {
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            if (str.contains("/exit")) {
                numberMenu = 0;
            } else if (str.contains("/next")) {
                numberMenu = 1;
            } else if (str.contains("/open")) {
                numberMenu = 2;
            }

            synchronized (msg) {
                switch (numberMenu) {
                    case 1:
                        if (reader.isAlive()) {
                            System.out.println("Поток существует");
                            reader.continueRead();
                        }
                        break;

                    case 2:
                        if (!reader.isAlive()) {
                            reader.start();
                        }
                        break;
                }
            }
        }
    }
}
