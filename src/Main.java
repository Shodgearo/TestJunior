// Программа для теста на джуна для AIM Cunsulting
// Нужно реализовать многопоточность при чтении таблиц
public class Main {
    public static void main(String[] args) {
        Child child1 = new Child("Thread one"); // Создаём потоки
        Child child2 = new Child("Thread two");

        try { // Ожидаем завершение потоков
            child1.t.join();
            child2.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
