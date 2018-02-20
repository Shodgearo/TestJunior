// Класс, который описывает поведение потока
public class Child implements Runnable {
    private String name;
    Thread t;

    public Child(String name) {
        this.name = name; // Сохранаяем ссылку на строку в которой хранится имя файла
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

    }
}
