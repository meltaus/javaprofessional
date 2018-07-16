package ru.gorbachev.lesson3.controller;

import java.io.*;
import java.util.ArrayList;
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

    //Читаем указанный файл на требуемый размер и пишем в один файл последовательно
    //Предполагает, что файл имеет кодировку utf-8
    public byte[] concatinationFile(final int leight, final String pathFile) {
        CreateRandomFile createRandomFile = new CreateRandomFile();
        final List<String> filePathList = createRandomFile.createFile(5, leight);
        List<String> lines = new ArrayList<String>();

        File file = new File(pathFile);
        if (file.exists()) {
            file.delete();
        }

        try {
            for (String path : filePathList) {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();

                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }
                reader.close();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile, true));
            for (int i = 0; i < lines.size(); i++) {
                String lineSeparator = System.getProperty("line.separator");
                writer.write(lines.get(i) + lineSeparator);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
