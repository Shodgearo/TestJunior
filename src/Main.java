// Программа для теста на джуна для AIM Cunsulting
// Нужно реализовать многопоточность при чтении таблиц

import java.io.*;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        // Создаём или записываем в файл
        for(;;) {
            System.out.println("Enter file name(enter \"exit\" for quit): ");

            String line = "";
            String fileName = addFileName();
            File file = new File(fileName + ".csv");

            if(fileName.equals("exit")) break;

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
        Child child1 = new Child("Thread one");
        Child child2 = new Child("Thread two");

        try { // Ожидаем завершение потоков
            child1.t.join();
            child2.t.join();
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
