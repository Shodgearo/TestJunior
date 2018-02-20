import java.io.*;

// Программа для теста на джуна для AIM Cunsulting
// Нужно реализовать многопоточность при чтении таблиц
public class Main {
    public static void main(String[] args) {
        // Создаём или записываем в файл
        String fileName = "";

        File file = new File("1.csv");
        String line = "";

        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("File not create.");
        }

        System.out.println("Enter: ");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                line = br.readLine();

                if (line != null) bw.write(line);

                bw.flush();
            } while (!line.equals("q"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
