package ru.gorbachev.lesson3.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateRandomFile {

    private final String sourceFile;

    public CreateRandomFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        this.sourceFile = classLoader.getResource("book.txt").getPath();
    }

    //Создает 5 файлов заданного байтового размера и возвращает путь до них
    public List<String> createFile(final int countFile, final int leight) {
        List<String> lines = readResource(leight, 5);
        List<String> filePathList = new ArrayList<>();

        for (int i = 0; i < countFile; i++) {
            try {
                filePathList.add("File" + i + ".txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter("File" + i + ".txt", true));
                String lineSeparator = System.getProperty("line.separator");
                writer.write(lines.get(i) + lineSeparator);
                writer.close();
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл");
            }
        }
        return filePathList;
    }

    private List<String> readResource(final int leight, final int count) {
        byte[] arr = new byte[leight];
        FileInputStream in = null;
        try {
            List<String> lines = new ArrayList<String>();
            in = new FileInputStream(this.sourceFile);
            for (int i = 0; i < count; i++) {
                in.read(arr);
                lines.add(new String(arr, "UTF-8"));
            }
            return lines;
        } catch (Exception e) {
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return Collections.emptyList();
        }
    }
}
