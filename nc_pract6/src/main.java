/**
 * Created by goga on 23.11.15.
 */

public class main {
    static int a = 1;
    static Object lock =  new Object();

    public static void main(String[] args) throws InterruptedException {
        // создаю 2 потока
        Thread t1 = new Thread(new runnable()); // для создания использую класс Runnable class
        Thread t2 = new Thread(new runnable());
        // сзапускаю их
        t1.start();
        t2.start();
        // пишу join-ы для того, чтобы System.out.println(a); не выполнилось раньше чем завершат работу мои потоки
        t1.join();
        t2.join();
        System.out.println(a);
    }

    public static class runnable implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                a += 1;
            }
        }
    }
}

