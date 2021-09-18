package common;

/**
 * @author: zhangth
 * @CreateDate: 2021/9/12
 */
public class CpuUsageTest {
    public static void main(String[] args) throws InterruptedException {

        Runnable run = () -> {
            int a = 0;
            while (true) {
                a++;
            }
        };

        Thread thread1 = new Thread(run);
        Thread thread2 = new Thread(run);
        Thread thread3 = new Thread(run);
        Thread thread4 = new Thread(run);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }

}
