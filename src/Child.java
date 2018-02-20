// Класс, который описывает поведение потока
public class Child implements Runnable {
    private String name;
    Thread t;

    public Child(String name) {
        this.name = name;
        t = new Thread();
        t.start();
    }

    @Override
    public void run() {

    }
}
