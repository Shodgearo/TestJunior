// Программа для теста на джуна для AIM Consulting
// Нужно реализовать многопоточность при чтении таблиц

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        List<String> nameFiles = new ArrayList<>();
        List<Child> children = new ArrayList<>();
        // Создаём или записываем в файл
        for(;;) {
            System.out.println("Enter file name(enter \"exit\" for quit): ");

            String line = "";
            String fileName = addFileName();
            File file = new File(fileName + ".csv");

            if(fileName.equals("exit")) break;

            nameFiles.add(fileName);

            if (!file.exists()) try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Enter(enter \"close\" for ending): ");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for(;!line.equals("close");) {
                    line = br.readLine();

                    if(line.equals("close")) break;

                    String[] temp = line.split(";");

                    bw.write(line + "\n\r");
                    bw.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        clean(); // Закрываем потоки ввода

        // Создаём потоки для чтения
        for (String nameFile : nameFiles) {
            children.add(new Child(nameFile));
        }

        try { // Ожидаем завершение потоков
            for (Child child : children) {
                child.t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void clean() {
        try {
            reader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String addFileName() {
        String name = "";

        try {
            name = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }
}
