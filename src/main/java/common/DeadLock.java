package common;

/**
 * @author: zhangth
 * @CreateDate: 2021/9/12
 */
public class DeadLock {
    public static void main(String[] args) {
        Object lcok1 = new Object();
        Object lock2 = new Object();
        new Thread(() -> {
            synchronized (lcok1) {
                System.out.println(Thread.currentThread() + "get lock1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread() + "get lock2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread() + "get lock2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lcok1) {
                    System.out.println(Thread.currentThread() + "get lock1");
                }
            }
        }).start();
    }
}


