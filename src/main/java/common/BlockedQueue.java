package common;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhangth
 * @CreateDate: 2021/9/12
 */
public class BlockedQueue {
    final Object[] item = new Object[100];
    final Lock lock = new ReentrantLock();
    final Condition putCondition = lock.newCondition();
    final Condition takeCondition = lock.newCondition();
    int putIndex, getIndex, size;

    public Object take() throws InterruptedException {
        Object result = null;
        lock.lock();
        try {
            while (size == 0) {
                takeCondition.await();
            }
            result = item[getIndex];
            if (++getIndex == item.length - 1) {
                getIndex = 0;
            }
            size--;
            putCondition.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void put(Object key) throws InterruptedException {
        lock.lock();
        try {
            while (size == item.length) {
                putCondition.await();
            }
            item[putIndex] = key;
            if (++putIndex == item.length - 1) {
                putIndex = 0;
            }
            size++;
            takeCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockedQueue blockedQueue = new BlockedQueue();
        blockedQueue.put("abc");
        Object o = blockedQueue.take();
        System.out.println(o);
        Object o1 = blockedQueue.take();
        System.out.println(o1);

    }

}
