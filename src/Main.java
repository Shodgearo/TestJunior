// ��������� ��� ����� �� ����� ��� AIM Cunsulting
// ����� ����������� ��������������� ��� ������ ������
public class Main {
    public static void main(String[] args) {
        Child child1 = new Child("Thread one"); // ������ ������
        Child child2 = new Child("Thread two");

        try { // ������� ���������� �������
            child1.t.join();
            child2.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
