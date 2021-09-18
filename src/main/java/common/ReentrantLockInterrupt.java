package common;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做成响应
 *
 * @author 小辉哥/小辉GE
 * <p>
 * 2019年8月10日 下午15:30:00
 */
public class ReentrantLockInterrupt {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread1 start......");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println("thread1" + "interrupt......");
            } finally {
                lock.unlock();
                System.out.println("thread1 end......");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            boolean status = false;
            try {
                status = lock.tryLock();
                System.out.println("thread2 start......");
                // 可以对interrupt方法做出响应
                if (!status)
                    lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("thread2" + "interrupt");
            } finally {
                if (status)
                    lock.unlock();
                System.out.println("thread2 end");
            }
        });
        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.interrupt();
    }

}