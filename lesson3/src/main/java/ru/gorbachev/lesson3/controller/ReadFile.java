package ru.gorbachev.lesson3.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ReadFile {

    private final String sourceFile;

    public ReadFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        this.sourceFile = classLoader.getResource("book.txt").getPath();
    }

    //Читаем заданное количество байт и печатаем в консоль
    public void readByteFile(final int leight) {
        byte[] arr = new byte[leight];
        FileInputStream in = null;

        try {
            in = new FileInputStream(this.sourceFile);
            int count = in.read(arr);
            System.out.println("Считанно " + count + " байт");
            for (int i = 0; i < leight; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("\n" + new String(arr, "UTF-8"));
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла");
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                System.out.println("Ошибка закрытия файла");
            }
        }
    }

    //Читаем указанный файл на требуемый размер и возвращаем список прочитанных строк,
    //Предполагает, что файл имеет кодировку utf-8
    private List<String> readFile (final int leight, final String pathfile) {

        return Collections.emptyList();
    }
}
